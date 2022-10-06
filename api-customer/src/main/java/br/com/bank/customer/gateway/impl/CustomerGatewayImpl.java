package br.com.bank.customer.gateway.impl;

import br.com.bank.customer.config.properties.BankProperties;
import br.com.bank.customer.gateway.CustomerGateway;
import br.com.bank.customer.gateway.converter.CustomerGatewayConverter;
import br.com.bank.customer.gateway.repository.AccountsRepository;
import br.com.bank.customer.gateway.repository.PeopleRepository;
import br.com.bank.customer.gateway.repository.model.AccountModel;
import br.com.bank.customer.gateway.repository.model.PeopleModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static br.com.bank.customer.entity.enumerator.AccountStatus.ACTIVE;
import static br.com.bank.customer.entity.enumerator.AccountStatus.INACTIVE;

@Component
public class CustomerGatewayImpl implements CustomerGateway{

	private final PeopleRepository peopleRepository;
	private final AccountsRepository accountsRepository;
	private final BankProperties bankProperties;
	private final CustomerGatewayConverter customerGatewayConverter;

	public CustomerGatewayImpl(PeopleRepository peopleRepository, AccountsRepository accountsRepository, BankProperties bankProperties, CustomerGatewayConverter customerGatewayConverter) {
		this.peopleRepository = peopleRepository;
		this.accountsRepository = accountsRepository;
		this.bankProperties = bankProperties;
		this.customerGatewayConverter = customerGatewayConverter;
	}

	@Override
	public PeopleModel getPersonAndAccounts(String document) {
		return peopleRepository.findByDocument(document);
	}

	@Override
	public PeopleModel savePerson(PeopleModel person) {
		person.setUpdatedAt(LocalDateTime.now());
		return peopleRepository.save(person);
	}

	@Override
	public AccountModel openAccount(PeopleModel person) {
		var account = customerGatewayConverter
				.toOpenAccountModel(person.getId(), bankProperties.getDefaultAgency());

		accountsRepository.save(account);
		customerGatewayConverter.addAccountNumberAndAccountDigit(account);
		accountsRepository.save(account);

		return account;
	}

	@Override
	public AccountModel getOpenAccount(String document) {
		return peopleRepository
				.findByDocument(document)
				.getAccounts()
				.stream()
				.filter( a -> a.getStatus().equals(ACTIVE.getStatus()))
				.findAny()
				.orElse(null);
	}

	@Override
	public void closeAccount(AccountModel account) {
		account.setStatus(INACTIVE.getStatus());
		account.setClosedAt(LocalDateTime.now());
		accountsRepository.save(account);
	}
}
