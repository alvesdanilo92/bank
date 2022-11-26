package br.com.bank.customer.controller;

import br.com.bank.authorization.config.properties.LibAuthorizationProperties;
import br.com.bank.authorization.gateway.AuthenticationGateway;
import br.com.bank.authorization.gateway.data.response.UserAccountResponse;
import br.com.bank.customer.config.properties.IntegrationsProperties;
import br.com.bank.customer.gateway.AuthenticatorGateway;
import br.com.bank.customer.gateway.repository.AccountsRepository;
import br.com.bank.customer.gateway.repository.PeopleRepository;
import br.com.bank.customer.utils.FactoryUtils;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

@ActiveProfiles({"test","logbook"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
class ApiCustomerControllerTest {

    private static final String CREATE_AUTHENTICATION_RESPONSE_BODY_JSON_PATH = "src/test/resources/data/json/CreateAuthenticationResponse.json";
    private static final String VALIDATE_ACCOUNT_REQUEST_BODY_JSON_PATH = "src/test/resources/data/json/ValidateAccountRequestBody.json";
    private static final String OPEN_ACCOUNT_REQUEST_BODY_JSON_PATH = "src/test/resources/data/json/OpenAccountRequestBody.json";

    @LocalServerPort
    private Integer port;

    @Autowired
    private LibAuthorizationProperties libAuthorizationProperties;

    @Autowired
    private AuthenticationGateway authenticationGateway;

    @Autowired
    private AuthenticatorGateway authenticatorGateway;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    private static RestTemplate restTemplate;
    private static MockWebServer mockWebServer;
    private static IntegrationsProperties integrationsProperties;

    @BeforeAll
    static void setup() throws IOException {
        restTemplate = Mockito.mock(RestTemplate.class);
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());

        integrationsProperties = Mockito.mock(IntegrationsProperties.class);
        Mockito.when(integrationsProperties.getCreateUserServiceUrl()).thenReturn(baseUrl);
        Mockito.when(integrationsProperties.getDeactivateUserServiceUrl()).thenReturn(baseUrl);
    }

    @BeforeEach
    void initialize() throws IllegalAccessException {
        var webClient = WebClient.create();
        FieldUtils.writeDeclaredField(authenticationGateway, "restTemplate", restTemplate, true);
        FieldUtils.writeDeclaredField(authenticatorGateway, "webClient", webClient, true);
        FieldUtils.writeDeclaredField(authenticatorGateway, "integrationsProperties", integrationsProperties, true);

        var userAccountResponse = new UserAccountResponse();
        userAccountResponse.setResults(FactoryUtils.createUserAccount());
        var response = new ResponseEntity<>(userAccountResponse, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(
                        Mockito.eq(libAuthorizationProperties.getUrlValidate()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.any(),
                        Mockito.eq(UserAccountResponse.class)))
                .thenReturn(response);
    }

    @Test
    void getAccounts_whenAccountsExist_shouldReturnAccounts(){
        deleteCustomer();
        insertCustomer();

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", FactoryUtils.AUTHORIZATION_TOKEN)
                .when()
                .get( "/customer/accounts")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results.name", Matchers.equalTo(FactoryUtils.NAME),
                        "results.accounts[0].number", Matchers.equalTo(FactoryUtils.NUMBER),
                        "results.accounts[0].digit", Matchers.equalTo(FactoryUtils.DIGIT),
                        "results.accounts[0].agency", Matchers.equalTo(FactoryUtils.AGENCY),
                        "results.accounts[0].status", Matchers.equalTo("ACT"));
    }

    @Test
    void validateAccount_whenAccountExists_shouldReturnSuccessButNoContent() throws IOException {

        final String json = Files.readString(Path.of(VALIDATE_ACCOUNT_REQUEST_BODY_JSON_PATH));

        deleteCustomer();
        insertCustomer();

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post( "customer/account/validate")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void validateAccount_whenTheAccountDoesNotExist_shouldReturnNotFound() throws IOException {
        final String json = Files
                .readString(Path.of(VALIDATE_ACCOUNT_REQUEST_BODY_JSON_PATH))
                .replace("900000001", "99999");

        deleteCustomer();
        insertCustomer();

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post( "customer/account/validate")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void openAccount_whenThereIsNoOpenAccount_shouldOpenAnAccountAndReturnAccountData() throws IOException {
        final String jsonResponse = Files.readString(Path.of(CREATE_AUTHENTICATION_RESPONSE_BODY_JSON_PATH));
        final String json = Files.readString(Path.of(OPEN_ACCOUNT_REQUEST_BODY_JSON_PATH));

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse)
                .setResponseCode(HttpStatus.CREATED.value()));

        deleteCustomer();

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post( "customer/account/open")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("results.agency", Matchers.equalTo(FactoryUtils.AGENCY),
                        "results.status", Matchers.equalTo("ACT"));
    }

    @Test
    void openAccount_whenThereIsAnOpenAccount_shouldReturnUnprocessableEntity() throws IOException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.CREATED.value()));
        final String json = Files.readString(Path.of(OPEN_ACCOUNT_REQUEST_BODY_JSON_PATH));
        deleteCustomer();
        insertCustomer();

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post( "customer/account/open")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("results.message", Matchers.equalTo("Customer already has an open account"));
    }

    @Test
    void closeAccount_whenAccountIsOpen_shouldCloseTheAccountAndReturnSuccessButNoContent(){
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.NO_CONTENT.value()));

        deleteCustomer();
        insertCustomer();

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", "token")
                .when()
                .put( "customer/account/close")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void deleteCustomer(){
        var p = peopleRepository.findByDocument(FactoryUtils.DOCUMENT);
        if(p != null){
            peopleRepository.delete(p);
        }
    }

    private void insertCustomer(){
        var peopleModel = FactoryUtils.createPeopleModel();
        peopleRepository.save(peopleModel);

        var accountModel = FactoryUtils.createAccountModel(peopleModel.getId());
        accountsRepository.save(accountModel);
    }
}