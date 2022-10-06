package br.com.bank.authenticator.usecase;

import br.com.bank.authenticator.entity.NewUserEntity;

public interface CreateUserUseCase {
    void execute(NewUserEntity userModel);
}
