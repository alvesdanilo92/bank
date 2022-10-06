package br.com.bank.customer.usecase.converter.impl;

import br.com.bank.customer.controller.data.response.AccountResponse;
import br.com.bank.customer.controller.data.response.AccountsResponse;
import br.com.bank.customer.gateway.repository.model.AccountModel;
import br.com.bank.customer.gateway.repository.model.PeopleModel;
import br.com.bank.customer.usecase.converter.AccountsUseCaseConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountsUseCaseConverterImpl implements AccountsUseCaseConverter {

	@Override
	public AccountsResponse toResponse(PeopleModel people) {
		return new AccountsResponse().setName(people.getName())
				.setAccounts(people.getAccounts().stream().map(this::toAccountResponse).collect(Collectors.toList()));
	}
	
	private AccountResponse toAccountResponse(AccountModel account) {
		var accountResponse = new AccountResponse();
		BeanUtils.copyProperties(account, accountResponse);
		return accountResponse;
	}
}
