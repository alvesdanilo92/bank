package br.com.bank.authenticator.controller.converter;

import br.com.bank.authenticator.controller.data.request.AccountRequest;
import br.com.bank.authenticator.entity.NewUserEntity;

public interface CreateAuthenticationConverter {
    NewUserEntity toNewUserEntity(AccountRequest accountRequest, String document, String password);
}
