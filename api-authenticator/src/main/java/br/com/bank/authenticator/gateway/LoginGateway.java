package br.com.bank.authenticator.gateway;

import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.gateway.repository.model.UserModel;

public interface LoginGateway {
    UserModel getUser(String username);
    UserModel createUser(NewUserEntity newUserEntity);
    void deactivateUser(String username);
}
