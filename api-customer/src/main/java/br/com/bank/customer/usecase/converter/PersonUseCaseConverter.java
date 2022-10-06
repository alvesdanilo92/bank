package br.com.bank.customer.usecase.converter;

import br.com.bank.customer.entity.PersonEntity;
import br.com.bank.customer.gateway.repository.model.PeopleModel;

public interface PersonUseCaseConverter {

    PeopleModel toPeopleModel(PeopleModel peopleModel, PersonEntity personEntity);
}
