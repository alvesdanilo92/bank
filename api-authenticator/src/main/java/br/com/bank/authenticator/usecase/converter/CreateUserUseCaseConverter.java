package br.com.bank.authenticator.usecase.converter;

import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.gateway.data.request.CheckIntegrityGatewayRequest;

public interface CreateUserUseCaseConverter {
    CheckIntegrityGatewayRequest toCheckIntegrityGatewayRequest(NewUserEntity newUserEntity);
}
