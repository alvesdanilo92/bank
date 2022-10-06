package br.com.bank.authenticator.controller;

import br.com.bank.authenticator.gateway.AccountGateway;
import br.com.bank.authenticator.gateway.LoginGateway;
import br.com.bank.authenticator.utils.FactoryUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

@ActiveProfiles({"test","logbook"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
class ApiAuthenticatorController_CreateAuthenticationTokenTest {

    private static final String AUTHENTICATE_REQUEST_BODY_JSON_PATH = "src/test/resources/data/json/AuthenticateRequestBody.json";
    private static final String AUTHENTICATE_UNAUTHORIZED_REQUEST_BODY_JSON_PATH = "src/test/resources/data/json/AuthenticateUnauthorizedRequestBody.json";
    private static final String AUTHENTICATE_USER_INACTIVE_REQUEST_BODY_JSON_PATH = "src/test/resources/data/json/AuthenticateUserInactiveRequestBody.json";

    @LocalServerPort
    private Integer port;

    @Autowired
    private LoginGateway loginGateway;

    @Autowired
    private AccountGateway accountGateway;

    @Test
    void createAuthenticationToken_whenEverythingIsRight_shouldReturnToken() throws IOException {
        var  newUserEntity = FactoryUtils.createNewUserEntity(900000001,1,"05341416090");
        loginGateway.createUser(newUserEntity);
        accountGateway.addAccount(newUserEntity);

        final String json = Files.readString(Path.of(AUTHENTICATE_REQUEST_BODY_JSON_PATH));
        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post( "/authenticator/authenticate")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results.token", Matchers.startsWith("eyJhbGciOiJIUzUxMiJ9"));
    }

    @Test
    void createAuthenticationToken_whenAuthenticationIsInvalid_shouldReturnUnauthorized() throws IOException {
        final String json = Files.readString(Path.of(AUTHENTICATE_UNAUTHORIZED_REQUEST_BODY_JSON_PATH));
        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post( "/authenticator/authenticate")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void createAuthenticationToken_whenUserIsInactive_shouldReturnUnauthorized() throws IOException {
        var  newUserEntity = FactoryUtils.createNewUserEntity(900000001,1,"80365497002");
        loginGateway.createUser(newUserEntity);
        accountGateway.addAccount(newUserEntity);
        loginGateway.deactivateUser(newUserEntity.getDocument());

        final String json = Files.readString(Path.of(AUTHENTICATE_USER_INACTIVE_REQUEST_BODY_JSON_PATH));
        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(json)
                .when()
                .post( "/authenticator/authenticate")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

}
