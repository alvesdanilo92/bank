package br.com.bank.customer.gateway;

import br.com.bank.customer.gateway.data.request.CreateUserGatewayRequest;

public interface AuthenticatorGateway {
    void createUser(CreateUserGatewayRequest request, String document, String password);
    void deactivateUser(String authorization);
}
