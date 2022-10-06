package br.com.bank.customer.controller.data.response;

import java.util.List;

public class AccountsResponse {
	private String name;
	private List<AccountResponse> accounts;


	public String getName() {
		return name;
	}

	public AccountsResponse setName(String name) {
		this.name = name;
		return this;
	}

	public List<AccountResponse> getAccounts() {
		return accounts;
	}

	public AccountsResponse setAccounts(List<AccountResponse> accounts) {
		this.accounts = accounts;
		return this;
	}
}
