package br.com.bank.customer.usecase.converter;

import br.com.bank.customer.controller.data.request.OpenAccountRequest;
import br.com.bank.customer.controller.data.response.AccountResponse;
import br.com.bank.customer.entity.PersonEntity;
import br.com.bank.customer.gateway.repository.model.AccountModel;

public interface OpenAccountUseCaseConverter {
    AccountResponse toResponse(AccountModel account);
    PersonEntity toPersonEntity(OpenAccountRequest openAccountRequest);
}
