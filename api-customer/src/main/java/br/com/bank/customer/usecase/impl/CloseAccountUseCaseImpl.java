package br.com.bank.customer.usecase.impl;

import br.com.bank.customer.gateway.AuthenticatorGateway;
import br.com.bank.customer.gateway.CustomerGateway;
import br.com.bank.customer.usecase.CloseAccountUseCase;
import org.springframework.stereotype.Component;

@Component
public class CloseAccountUseCaseImpl implements CloseAccountUseCase {

    private final CustomerGateway customerGateway;
    private final AuthenticatorGateway authenticatorGateway;

    public CloseAccountUseCaseImpl(CustomerGateway customerGateway, AuthenticatorGateway authenticatorGateway) {
        this.customerGateway = customerGateway;
        this.authenticatorGateway = authenticatorGateway;
    }

    @Override
    public void execute(String authorization, String document) {
        var account = customerGateway.getOpenAccount(document);
        customerGateway.closeAccount(account);
        authenticatorGateway.deactivateUser(authorization);
    }
}
