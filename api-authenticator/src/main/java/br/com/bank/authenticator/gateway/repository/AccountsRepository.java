package br.com.bank.authenticator.gateway.repository;

import br.com.bank.authenticator.gateway.repository.model.AccountModel;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<AccountModel, Integer> {

	AccountModel findByDocument(String document);
}
