package br.com.bank.customer.usecase.converter.impl;

import br.com.bank.customer.controller.data.request.OpenAccountRequest;
import br.com.bank.customer.controller.data.response.AccountResponse;
import br.com.bank.customer.entity.PersonEntity;
import br.com.bank.customer.gateway.repository.model.AccountModel;
import br.com.bank.customer.usecase.converter.OpenAccountUseCaseConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OpenAccountUseCaseConverterImpl implements OpenAccountUseCaseConverter {

    @Override
    public AccountResponse toResponse(AccountModel account) {
        var accountResponse = new AccountResponse();
        BeanUtils.copyProperties(account, accountResponse);
        return accountResponse;
    }

    @Override
    public PersonEntity toPersonEntity(OpenAccountRequest openAccountRequest) {
        var personEntity = new PersonEntity();
        BeanUtils.copyProperties(openAccountRequest, personEntity);
        return personEntity;
    }
}
