package br.com.bank.authenticator.gateway.repository;

import br.com.bank.authenticator.gateway.repository.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserModel, Integer> {

    UserModel findByUsername(String username);
}
