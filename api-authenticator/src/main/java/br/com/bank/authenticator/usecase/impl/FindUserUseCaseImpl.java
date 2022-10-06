package br.com.bank.authenticator.usecase.impl;

import java.util.ArrayList;

import br.com.bank.authenticator.gateway.LoginGateway;
import br.com.bank.authenticator.gateway.repository.model.UserModel;
import br.com.bank.authenticator.usecase.FindUserUseCase;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class FindUserUseCaseImpl implements FindUserUseCase {

	private final LoginGateway loginGateway;

	public FindUserUseCaseImpl(LoginGateway loginGateway) {
		this.loginGateway = loginGateway;
	}

	@Override
	public User execute(String username, String password) {
		UserModel user = loginGateway.getUser(username);
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
