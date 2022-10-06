package br.com.bank.customer.usecase.converter;

import br.com.bank.customer.controller.data.response.AccountsResponse;
import br.com.bank.customer.gateway.repository.model.PeopleModel;

public interface AccountsUseCaseConverter {

	AccountsResponse toResponse(PeopleModel people);
}
