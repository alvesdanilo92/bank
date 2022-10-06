package br.com.bank.authenticator.gateway.impl;

import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.gateway.AccountGateway;
import br.com.bank.authenticator.gateway.repository.AccountsRepository;
import br.com.bank.authenticator.gateway.repository.model.AccountModel;
import org.springframework.stereotype.Component;

@Component
public class AccountGatewayImpl implements AccountGateway {
    private final AccountsRepository repository;

    public AccountGatewayImpl(AccountsRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountModel getAccount(String document) {
        return repository.findByDocument(document);
    }

    @Override
    public AccountModel addAccount(NewUserEntity newUserEntity) {
        var accountModel = getAccount(newUserEntity.getDocument());

        if(accountModel == null){
            accountModel = new AccountModel();
        }

        accountModel.setDocument(newUserEntity.getDocument());
        accountModel.setAgency(newUserEntity.getAgency());
        accountModel.setNumber(newUserEntity.getNumber());
        accountModel.setDigit(newUserEntity.getDigit());


        repository.save(accountModel);

        return accountModel;
    }
}
