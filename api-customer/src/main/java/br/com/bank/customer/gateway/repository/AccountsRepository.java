package br.com.bank.customer.gateway.repository;

import br.com.bank.customer.gateway.repository.model.AccountModel;
import org.springframework.data.repository.CrudRepository;


public interface AccountsRepository extends CrudRepository<AccountModel, Integer> {

	AccountModel findByCusPersonId(int cusPersonId);
}