package br.com.bank.authenticator.usecase;

import org.springframework.security.core.userdetails.User;
public interface FindUserUseCase {

    User execute(String username, String password);
}
