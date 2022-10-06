package br.com.bank.authenticator.usecase.impl;

import br.com.bank.authenticator.gateway.LoginGateway;
import br.com.bank.authenticator.usecase.DeactivateAccountUseCase;
import org.springframework.stereotype.Component;

@Component
public class DeactivateAccountUseCaseImpl implements DeactivateAccountUseCase {

    private final LoginGateway loginGateway;

    public DeactivateAccountUseCaseImpl(LoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }

    @Override
    public void execute(String document) {
        loginGateway.deactivateUser(document);
    }
}
