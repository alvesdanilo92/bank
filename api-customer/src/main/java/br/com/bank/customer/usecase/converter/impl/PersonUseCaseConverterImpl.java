package br.com.bank.customer.usecase.converter.impl;

import br.com.bank.customer.entity.PersonEntity;
import br.com.bank.customer.gateway.repository.model.PeopleModel;
import br.com.bank.customer.usecase.converter.PersonUseCaseConverter;
import org.springframework.stereotype.Component;

@Component
public class PersonUseCaseConverterImpl implements PersonUseCaseConverter {

    @Override
    public PeopleModel toPeopleModel(PeopleModel peopleModel, PersonEntity personEntity) {
        peopleModel.setBirth(personEntity.getBirth());
        peopleModel.setDocument(personEntity.getDocument());
        peopleModel.setGender(personEntity.getGender());
        peopleModel.setName(personEntity.getName());
        return peopleModel;
    }
}
