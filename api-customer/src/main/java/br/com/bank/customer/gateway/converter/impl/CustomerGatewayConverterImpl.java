package br.com.bank.customer.gateway.converter.impl;

import br.com.bank.customer.gateway.converter.CustomerGatewayConverter;
import br.com.bank.customer.gateway.repository.model.AccountModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static br.com.bank.customer.entity.enumerator.AccountStatus.ACTIVE;

@Component
public class CustomerGatewayConverterImpl implements CustomerGatewayConverter {

    @Override
    public AccountModel toOpenAccountModel(int personID, String agency) {
        var account = new AccountModel();
        account.setCusPersonId(personID);
        account.setAgency(agency);
        account.setStatus(ACTIVE.getStatus());
        account.setOpenedAt(LocalDateTime.now());

        return account;
    }

    @Override
    public void addAccountNumberAndAccountDigit(AccountModel account) {
        var accountNumber = generateAccountNumber(account.getId());
        account.setNumber(accountNumber);
        account.setDigit(calcDigit(accountNumber));
    }

    private int generateAccountNumber(int id){
        return Integer.parseInt("9" + StringUtils.leftPad(String.valueOf(id), 8, "0"));
    }

    private int calcDigit(int accountNumber){
        var account = String.valueOf(accountNumber);
        long digit = 0l;

        for (int i = 0, seq = account.length(); i < account.length(); i++, seq--) {
            digit += Integer.parseInt(account.substring(i, i+1)) * seq;
        }

        digit = digit % 10;
        digit = digit == 10 ? 0 : digit;
        return (int) digit;
    }
}
