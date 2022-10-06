package br.com.bank.authorization.gateway;

import br.com.bank.authorization.entity.UserAccount;

public interface AuthenticationGateway {

	UserAccount validateToken(String token);
}
