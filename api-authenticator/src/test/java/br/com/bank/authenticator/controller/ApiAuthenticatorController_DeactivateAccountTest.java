package br.com.bank.authenticator.controller;

import br.com.bank.authenticator.config.JwtRequestFilter;
import br.com.bank.authenticator.config.JwtTokenUtil;
import br.com.bank.authenticator.gateway.AccountGateway;
import br.com.bank.authenticator.gateway.LoginGateway;
import br.com.bank.authenticator.service.JwtUserDetailsService;
import br.com.bank.authenticator.utils.FactoryUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
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

import java.io.IOException;

import static io.restassured.RestAssured.given;

@ActiveProfiles({"test","logbook"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
class ApiAuthenticatorController_DeactivateAccountTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private LoginGateway loginGateway;

    @Autowired
    private AccountGateway accountGateway;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static JwtTokenUtil jwtTokenUtil;
    private static JwtUserDetailsService jwtUserDetailsService;
    @BeforeAll
    static void setUp() throws IOException {
        jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);
        jwtUserDetailsService = Mockito.mock(JwtUserDetailsService.class);
    }

    @BeforeEach
    void initialize() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(jwtRequestFilter, "jwtTokenUtil", jwtTokenUtil, true);
        FieldUtils.writeDeclaredField(jwtRequestFilter, "jwtUserDetailsService", jwtUserDetailsService, true);
    }

    @Test
    void createAuthenticationToken_whenEverythingIsRight_shouldReturnNoContent() {
        var newUserEntity = FactoryUtils.createNewUserEntity(900000001,1,"44422000098");
        FactoryUtils.mockJwt(jwtTokenUtil, jwtUserDetailsService,newUserEntity, Boolean.TRUE);
        loginGateway.createUser(newUserEntity);
        accountGateway.addAccount(newUserEntity);

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", FactoryUtils.AUTHORIZATION_TOKEN)
                .when()
                .put( "/authenticator/deactivate")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void createAuthenticationToken_whenUserNotFound_shouldReturnNotFound() {
        var newUserEntity = FactoryUtils.createNewUserEntity(900000001,1,"62945272016");
        FactoryUtils.mockJwt(jwtTokenUtil, jwtUserDetailsService, newUserEntity, Boolean.TRUE);

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", FactoryUtils.AUTHORIZATION_TOKEN)
                .when()
                .put( "/authenticator/deactivate")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
