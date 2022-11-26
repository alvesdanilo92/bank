package br.com.bank.authenticator.usecase;

import br.com.bank.authenticator.controller.data.response.CreateAuthenticationResponse;
import br.com.bank.authenticator.entity.NewUserEntity;

public interface CreateUserUseCase {
    CreateAuthenticationResponse execute(NewUserEntity userModel);
}
