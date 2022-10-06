package br.com.bank.customer.usecase.impl;

import br.com.bank.customer.controller.data.request.OpenAccountRequest;
import br.com.bank.customer.controller.data.response.AccountResponse;
import br.com.bank.customer.entity.enumerator.AccountStatus;
import br.com.bank.customer.exceptions.CustomerAlreadyHasAnOpenAccountException;
import br.com.bank.customer.gateway.AuthenticatorGateway;
import br.com.bank.customer.gateway.CustomerGateway;
import br.com.bank.customer.gateway.data.request.CreateUserGatewayRequest;
import br.com.bank.customer.gateway.repository.model.AccountModel;
import br.com.bank.customer.usecase.CreatePersonUseCase;
import br.com.bank.customer.usecase.OpenAccountUseCase;
import br.com.bank.customer.usecase.UpdatePersonUseCase;
import br.com.bank.customer.usecase.converter.OpenAccountUseCaseConverter;
import org.springframework.stereotype.Component;

@Component
public class OpenAccountUseCaseImpl implements OpenAccountUseCase {

    private final CustomerGateway customerGateway;
    private final AuthenticatorGateway authenticatorGateway;
    private final CreatePersonUseCase createPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final OpenAccountUseCaseConverter openAccountUseCaseConverter;

    public OpenAccountUseCaseImpl(CustomerGateway customerGateway, AuthenticatorGateway authenticatorGateway, CreatePersonUseCase createPersonUseCase, UpdatePersonUseCase updatePersonUseCase, OpenAccountUseCaseConverter openAccountUseCaseConverter) {
        this.customerGateway = customerGateway;
        this.authenticatorGateway = authenticatorGateway;
        this.createPersonUseCase = createPersonUseCase;
        this.updatePersonUseCase = updatePersonUseCase;
        this.openAccountUseCaseConverter = openAccountUseCaseConverter;
    }

    @Override
    public AccountResponse execute(OpenAccountRequest openAccountRequest){
        var peopleModel = customerGateway.getPersonAndAccounts(openAccountRequest.getDocument());
        var personEntity = openAccountUseCaseConverter.toPersonEntity(openAccountRequest);

        if(peopleModel == null){
            peopleModel = createPersonUseCase.execute(personEntity);
        }else{
            peopleModel
                    .getAccounts()
                    .stream()
                    .filter( a -> a.getStatus().equals(AccountStatus.ACTIVE.getStatus()))
                    .findAny()
                    .ifPresent(s -> {throw new CustomerAlreadyHasAnOpenAccountException();});

            peopleModel = updatePersonUseCase.execute(personEntity);
        }

        var account = customerGateway.openAccount(peopleModel);
        createUser(account, openAccountRequest);
        return openAccountUseCaseConverter.toResponse(account);
    }

    private void createUser(AccountModel account, OpenAccountRequest openAccountRequest){
        authenticatorGateway.createUser(new CreateUserGatewayRequest(account.getNumber(), account.getDigit(), account.getAgency()),openAccountRequest.getDocument(), openAccountRequest.getPassword());
    }
}
