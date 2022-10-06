package br.com.bank.authenticator.usecase.impl;

import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.gateway.AccountGateway;
import br.com.bank.authenticator.gateway.CustomerGateway;
import br.com.bank.authenticator.gateway.LoginGateway;
import br.com.bank.authenticator.usecase.CreateUserUseCase;
import br.com.bank.authenticator.usecase.converter.CreateUserUseCaseConverter;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final LoginGateway loginGateway;
    private final AccountGateway accountGateway;
    private final CustomerGateway customerGateway;
    private final CreateUserUseCaseConverter createUserUseCaseConverter;

    public CreateUserUseCaseImpl(LoginGateway loginGateway, AccountGateway accountGateway, CustomerGateway customerGateway, CreateUserUseCaseConverter createUserUseCaseConverter) {
        this.loginGateway = loginGateway;
        this.accountGateway = accountGateway;
        this.customerGateway = customerGateway;
        this.createUserUseCaseConverter = createUserUseCaseConverter;
    }

    @Override
    public void execute(NewUserEntity newUserEntity) {
        customerGateway.checkIntegrity(createUserUseCaseConverter.toCheckIntegrityGatewayRequest(newUserEntity));
        loginGateway.createUser(newUserEntity);
        accountGateway.addAccount(newUserEntity);


    }
}
