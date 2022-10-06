package br.com.bank.customer.usecase;

import br.com.bank.customer.controller.data.request.ValidateAccountRequest;

public interface ValidateAccountUseCase {
    void execute(ValidateAccountRequest validateAccountRequest);
}
