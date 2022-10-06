package br.com.bank.authenticator.controller.converter.impl;

import br.com.bank.authenticator.controller.converter.CreateAuthenticationConverter;
import br.com.bank.authenticator.controller.data.request.AccountRequest;
import br.com.bank.authenticator.entity.NewUserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthenticationConverterImpl implements CreateAuthenticationConverter {

    private final PasswordEncoder passwordEncoder;

    public CreateAuthenticationConverterImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public NewUserEntity toNewUserEntity(AccountRequest accountRequest, String document, String password) {
        return new NewUserEntity()
                .setAgency(accountRequest.getAgency())
                .setDigit(accountRequest.getDigit())
                .setNumber(accountRequest.getNumber())
                .setPassword(passwordEncoder.encode(password))
                .setDocument(document);
    }
}
