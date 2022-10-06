package br.com.bank.customer.usecase.impl;

import br.com.bank.customer.entity.PersonEntity;
import br.com.bank.customer.gateway.CustomerGateway;
import br.com.bank.customer.gateway.repository.model.PeopleModel;
import br.com.bank.customer.usecase.CreatePersonUseCase;
import br.com.bank.customer.usecase.converter.PersonUseCaseConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreatePersonUseCaseImpl implements CreatePersonUseCase {

    private final CustomerGateway customerGateway;
    private final PersonUseCaseConverter personUseCaseConverter;

    public CreatePersonUseCaseImpl(CustomerGateway customerGateway, PersonUseCaseConverter personUseCaseConverter) {
        this.customerGateway = customerGateway;
        this.personUseCaseConverter = personUseCaseConverter;
    }

    @Override
    public PeopleModel execute(PersonEntity personEntity){
        var peopleModel = new PeopleModel();
        peopleModel.setCreatedAt(LocalDateTime.now());
        customerGateway.savePerson(personUseCaseConverter.toPeopleModel(peopleModel, personEntity));
        return peopleModel;
    }
}
