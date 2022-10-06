package br.com.bank.customer.usecase;

import br.com.bank.customer.entity.PersonEntity;
import br.com.bank.customer.gateway.repository.model.PeopleModel;

public interface UpdatePersonUseCase {
    PeopleModel execute(PersonEntity personEntity);
}
