package br.com.bank.authenticator.usecase.converter.impl;

import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.gateway.data.request.CheckIntegrityGatewayRequest;
import br.com.bank.authenticator.usecase.converter.CreateUserUseCaseConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCaseConverterImpl implements CreateUserUseCaseConverter {
    @Override
    public CheckIntegrityGatewayRequest toCheckIntegrityGatewayRequest(NewUserEntity newUserEntity) {
        var request = new CheckIntegrityGatewayRequest();
        BeanUtils.copyProperties(newUserEntity, request);
        return request;
    }
}
