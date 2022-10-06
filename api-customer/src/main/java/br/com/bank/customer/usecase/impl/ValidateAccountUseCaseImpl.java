package br.com.bank.customer.usecase.impl;

import br.com.bank.customer.controller.data.request.ValidateAccountRequest;
import br.com.bank.customer.exceptions.InvalidAccountException;
import br.com.bank.customer.gateway.CustomerGateway;
import br.com.bank.customer.usecase.ValidateAccountUseCase;
import org.springframework.stereotype.Component;

@Component
public class ValidateAccountUseCaseImpl implements ValidateAccountUseCase {

    private final CustomerGateway customerGateway;

    public ValidateAccountUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public void execute(ValidateAccountRequest validateAccountRequest) {
        var account = customerGateway.getOpenAccount(validateAccountRequest.getDocument());

        if(account != null &&
                validateAccountRequest.getDigit() == account.getDigit() &&
                validateAccountRequest.getNumber() == account.getNumber() &&
                validateAccountRequest.getAgency().equals(account.getAgency())){
            return;
        }

        throw new InvalidAccountException();
    }
}
