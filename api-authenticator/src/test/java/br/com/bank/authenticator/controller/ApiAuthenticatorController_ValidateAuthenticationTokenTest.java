package br.com.bank.authenticator.controller;

import br.com.bank.authenticator.config.JwtRequestFilter;
import br.com.bank.authenticator.config.JwtTokenUtil;
import br.com.bank.authenticator.service.JwtUserDetailsService;
import br.com.bank.authenticator.utils.FactoryUtils;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@ActiveProfiles({"test","logbook"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
class ApiAuthenticatorController_ValidateAuthenticationTokenTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static JwtTokenUtil jwtTokenUtil;
    private static JwtUserDetailsService jwtUserDetailsService;

    @BeforeAll
    static void setup() throws IOException {
        jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);
        jwtUserDetailsService = Mockito.mock(JwtUserDetailsService.class);
    }

    @BeforeEach
    void initialize() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(jwtRequestFilter, "jwtTokenUtil", jwtTokenUtil, true);
        FieldUtils.writeDeclaredField(jwtRequestFilter, "jwtUserDetailsService", jwtUserDetailsService, true);
    }

    @Test
    void createAuthenticationToken_whenEverythingIsRight_shouldReturnClaims() {
        var newUserEntity = FactoryUtils.createNewUserEntity(900000001,1,"62945272016");
        FactoryUtils.mockJwt(jwtTokenUtil, jwtUserDetailsService, newUserEntity, Boolean.TRUE);

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", FactoryUtils.AUTHORIZATION_TOKEN)
                .when()
                .get( "/authenticator/validate")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results.number", Matchers.equalTo(String.valueOf(newUserEntity.getNumber())),
                        "results.digit", Matchers.equalTo(String.valueOf(newUserEntity.getDigit())),
                        "results.agency", Matchers.equalTo(newUserEntity.getAgency()),
                        "results.document", Matchers.equalTo(newUserEntity.getDocument()));
    }

    @Test
    void createAuthenticationToken_whenTokenIsInvalid_shouldReturnUnauthorized() {
        var newUserEntity = FactoryUtils.createNewUserEntity(900000001,1,"62945272016");
        FactoryUtils.mockJwt(jwtTokenUtil, jwtUserDetailsService, newUserEntity, Boolean.FALSE);

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .header("Authorization", FactoryUtils.AUTHORIZATION_TOKEN)
                .when()
                .get( "/authenticator/validate")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

}
