package br.com.bank.customer.gateway.converter;

import br.com.bank.customer.gateway.repository.model.AccountModel;

public interface CustomerGatewayConverter {

    AccountModel toOpenAccountModel(int personID, String agency);

    void addAccountNumberAndAccountDigit(AccountModel account);
}
