package br.com.bank.authenticator.usecase.converter.impl;

import br.com.bank.authenticator.controller.data.response.AccountResponse;
import br.com.bank.authenticator.gateway.repository.model.AccountModel;
import br.com.bank.authenticator.usecase.converter.AuthenticateUseCaseConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUseCaseConverterImpl implements AuthenticateUseCaseConverter {

    @Override
    public AccountResponse toAccountResponse(AccountModel account) {
        return new AccountResponse()
                .setAgency(account.getAgency())
                .setNumber(account.getNumber())
                .setDigit(account.getDigit());
    }
}
