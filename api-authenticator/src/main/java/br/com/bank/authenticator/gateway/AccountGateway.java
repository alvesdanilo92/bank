package br.com.bank.authenticator.gateway;

import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.gateway.repository.model.AccountModel;

public interface AccountGateway {
    AccountModel getAccount(String document);
    AccountModel addAccount(NewUserEntity newUserEntity);
}
