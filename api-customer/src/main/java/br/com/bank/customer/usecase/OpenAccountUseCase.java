package br.com.bank.customer.usecase;

import br.com.bank.customer.controller.data.request.OpenAccountRequest;
import br.com.bank.customer.controller.data.response.AccountResponse;

public interface OpenAccountUseCase {

    AccountResponse execute(OpenAccountRequest personEntity);
}
