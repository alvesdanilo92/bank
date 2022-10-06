package br.com.bank.customer.usecase.impl;

import br.com.bank.customer.entity.PersonEntity;
import br.com.bank.customer.gateway.CustomerGateway;
import br.com.bank.customer.gateway.repository.model.PeopleModel;
import br.com.bank.customer.usecase.UpdatePersonUseCase;
import br.com.bank.customer.usecase.converter.PersonUseCaseConverter;
import org.springframework.stereotype.Component;

@Component
public class UpdatePersonUseCaseImpl implements UpdatePersonUseCase {

    private final CustomerGateway customerGateway;
    private final PersonUseCaseConverter personUseCaseConverter;


    public UpdatePersonUseCaseImpl(CustomerGateway customerGateway, PersonUseCaseConverter personUseCaseConverter) {
        this.customerGateway = customerGateway;
        this.personUseCaseConverter = personUseCaseConverter;
    }

    @Override
    public PeopleModel execute(PersonEntity personEntity){
        var peopleModel = customerGateway.getPersonAndAccounts(personEntity.getDocument());
        customerGateway.savePerson(personUseCaseConverter.toPeopleModel(peopleModel, personEntity));
        return peopleModel;
    }
}
