package br.com.bank.customer.usecase;

import br.com.bank.customer.controller.data.response.AccountsResponse;

public interface AccountsUseCase {

	AccountsResponse execute(String document);
}
