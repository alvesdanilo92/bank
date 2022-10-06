package br.com.bank.customer.gateway.repository;

import br.com.bank.customer.gateway.repository.model.PeopleModel;
import org.springframework.data.repository.CrudRepository;


public interface PeopleRepository extends CrudRepository<PeopleModel, Integer> {

	PeopleModel findByDocument(String document);
}