package br.com.bank.authenticator.controller;

import br.com.bank.authenticator.config.properties.IntegrationsProperties;
import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.gateway.AccountGateway;
import br.com.bank.authenticator.gateway.CustomerGateway;
import br.com.bank.authenticator.gateway.LoginGateway;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

@ActiveProfiles({"test","logbook"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
class ApiAuthenticatorController_CreateAuthenticationTest{

    private static final String CREATE_AUTHENTICATION_REQUEST_BODY_JSON_PATH = "src/test/resources/data/json/CreateAuthenticationRequestBody.json";
    private static final String PASSWORD = "123456";

    @LocalServerPort
    private Integer port;

    @Autowired
    private CustomerGateway customerGateway;

    @Autowired
    private LoginGateway loginGateway;

    @Autowired
    private AccountGateway accountGateway;

    private static IntegrationsProperties integrationsProperties;
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());

        integrationsProperties = Mockito.mock(IntegrationsProperties.class);
        Mockito.when(integrationsProperties.getCheckIntegrityServiceUrl())
                .thenReturn(baseUrl);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() throws IllegalAccessException {
        var webClient = WebClient.create();
        FieldUtils.writeDeclaredField(customerGateway, "webClient", webClient, true);
        FieldUtils.writeDeclaredField(customerGateway, "integrationsProperties", integrationsProperties, true);
    }

    @Test
    void createAuthentication_whenEverythingIsRight_shouldCreateUser() throws IOException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.NO_CONTENT.value()));
        final String json = Files.readString(Path.of(CREATE_AUTHENTICATION_REQUEST_BODY_JSON_PATH));
        final String document = "98449020026";

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("document", document)
                .header("password", PASSWORD)
                .body(json)
                .when()
                .post( "/authenticator/create")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void createAuthentication_whenAccountNotFound_shouldReturnUnprocessableEntityError() throws IOException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.NOT_FOUND.value()));
        final String json = Files.readString(Path.of(CREATE_AUTHENTICATION_REQUEST_BODY_JSON_PATH));
        final String document = "93059373060";
        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("document", document)
                .header("password", PASSWORD)
                .body(json)
                .when()
                .post( "/authenticator/create")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("results.error.message", Matchers.equalTo("Invalid Parameters"));
    }

    @Test
    void createAuthentication_whenAccountAlreadyExists_shouldReturnUnprocessableEntityError() throws IOException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.NO_CONTENT.value()));
        final String json = Files.readString(Path.of(CREATE_AUTHENTICATION_REQUEST_BODY_JSON_PATH));
        final String document = "46610311056";

        var  newUserEntity = new NewUserEntity()
                .setAgency("0001")
                .setNumber(900000012)
                .setDigit(5)
                .setDocument(document)
                .setPassword("$2a$10$Hy9KoO4Jhim4lh1ldPwUKehelSeHh6te8oUYchd9VlPfrLwMA1QOy");

        loginGateway.createUser(newUserEntity);
        accountGateway.addAccount(newUserEntity);

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("document", document)
                .header("password", PASSWORD)
                .body(json)
                .when()
                .post( "/authenticator/create")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("results.error.message", Matchers.equalTo("User already exists"));
    }

    @Test
    void createAuthentication_whenIntegrationServiceReturnsError5xx_shouldReturnInternalServerError() throws IOException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.SERVICE_UNAVAILABLE.value()));
        final String json = Files.readString(Path.of(CREATE_AUTHENTICATION_REQUEST_BODY_JSON_PATH));

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("document", "05469109046")
                .header("password", PASSWORD)
                .body(json)
                .when()
                .post( "/authenticator/create")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}