package br.com.bank.authenticator.gateway;

import br.com.bank.authenticator.gateway.data.request.CheckIntegrityGatewayRequest;

public interface CustomerGateway {
    void checkIntegrity(CheckIntegrityGatewayRequest checkIntegrityGatewayRequest);
}
