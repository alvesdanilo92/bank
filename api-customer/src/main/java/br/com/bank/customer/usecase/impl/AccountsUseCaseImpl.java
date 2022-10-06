package br.com.bank.customer.usecase.impl;

import br.com.bank.customer.controller.data.response.AccountsResponse;
import br.com.bank.customer.gateway.CustomerGateway;
import br.com.bank.customer.usecase.AccountsUseCase;
import br.com.bank.customer.usecase.converter.AccountsUseCaseConverter;
import org.springframework.stereotype.Component;

@Component
public class AccountsUseCaseImpl implements AccountsUseCase{

	private final CustomerGateway gateway;
	private final AccountsUseCaseConverter converter;

	public AccountsUseCaseImpl(CustomerGateway gateway, AccountsUseCaseConverter converter) {
		this.gateway = gateway;
		this.converter = converter;
	}

	@Override
	public AccountsResponse execute(String document) {
		return converter.toResponse(gateway.getPersonAndAccounts(document));
	}

}
