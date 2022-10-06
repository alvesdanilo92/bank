package br.com.bank.authenticator.usecase.converter;

import br.com.bank.authenticator.controller.data.response.AccountResponse;
import br.com.bank.authenticator.gateway.repository.model.AccountModel;

public interface AuthenticateUseCaseConverter {

    AccountResponse toAccountResponse(AccountModel account);
}
