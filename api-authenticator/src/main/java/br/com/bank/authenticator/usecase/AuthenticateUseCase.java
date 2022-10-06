package br.com.bank.authenticator.usecase;

public interface AuthenticateUseCase {

	String execute(String username, String password);
}
