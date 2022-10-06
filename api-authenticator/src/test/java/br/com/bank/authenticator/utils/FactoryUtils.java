package br.com.bank.authenticator.utils;

import br.com.bank.authenticator.config.JwtTokenUtil;
import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.service.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class FactoryUtils {
    private FactoryUtils(){}

    public static String AUTHORIZATION_TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhY2NvdW50Ijp7Im51bWJlciI6OTAwMDAwMDIxLCJkaWdpdCI6NiwiYWdlbmN5IjoiMDAwMSJ9LCJzdWIiOiI5ODQ0OTAyMDAyNiIsImlhdCI6MTY2NDg5MzU2MSwiZXhwIjoxNjY0ODk0NzYxfQ.ivCO-F3R--2D6jer4oR4rOe51mHn3mDELBjLXpUh9kVTaMZaRec75ahSctjVFvbF7_YmHt-Euipy5EkJfLd3sg";

    public static NewUserEntity createNewUserEntity(int number, int digit, String document){
        return new NewUserEntity()
                .setAgency("0001")
                .setNumber(number)
                .setDigit(digit)
                .setDocument(document)
                .setPassword("$2a$10$Hy9KoO4Jhim4lh1ldPwUKehelSeHh6te8oUYchd9VlPfrLwMA1QOy");
    }

    public static void mockJwt(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService,
                               NewUserEntity newUserEntity, boolean isValid){

        var claims = Mockito.mock(Claims.class);
        Mockito.when(jwtTokenUtil.getAllClaimsFromToken(anyString()))
                .thenReturn(claims);

        Mockito.when(jwtTokenUtil.getUsernameFromToken(anyString())).thenReturn(newUserEntity.getDocument());
        Mockito.when(jwtTokenUtil.validateToken(anyString(), any())).thenReturn(isValid);

        var userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(jwtUserDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);

        var map = new HashMap<String,Object>();
        map.put("number", newUserEntity.getNumber());
        map.put("digit", newUserEntity.getDigit());
        map.put("agency", newUserEntity.getAgency());
        Mockito.when(claims.get("account")).thenReturn(map);
        Mockito.when(claims.get("sub")).thenReturn(newUserEntity.getDocument());

    }
}
