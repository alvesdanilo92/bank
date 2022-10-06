package br.com.bank.customer.gateway;

import br.com.bank.customer.gateway.repository.model.AccountModel;
import br.com.bank.customer.gateway.repository.model.PeopleModel;

public interface CustomerGateway {

	PeopleModel getPersonAndAccounts(String document);
	PeopleModel savePerson(PeopleModel person);
	AccountModel openAccount(PeopleModel person);
	AccountModel getOpenAccount(String document);
	void closeAccount(AccountModel account);
}
