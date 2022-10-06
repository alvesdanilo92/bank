package br.com.bank.authenticator.gateway.impl;

import br.com.bank.authenticator.config.properties.ErrorsProperties;
import br.com.bank.authenticator.config.properties.data.ErrorPropertie;
import br.com.bank.authenticator.entity.NewUserEntity;
import br.com.bank.authenticator.exceptions.UserAlreadyExistsException;
import br.com.bank.authenticator.exceptions.UserNotFoundException;
import br.com.bank.authenticator.gateway.LoginGateway;
import br.com.bank.authenticator.gateway.repository.UsersRepository;
import br.com.bank.authenticator.gateway.repository.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LoginGatewayImpl implements LoginGateway {

    private final UsersRepository repository;
    private final ErrorsProperties errors;


    public LoginGatewayImpl(UsersRepository repository, ErrorsProperties errors) {
        this.repository = repository;
        this.errors = errors;
    }

    @Override
    public UserModel getUser(String username) {
        var user = repository.findByUsername(username);
        if(user == null || !user.isActive()) {
            ErrorPropertie error = errors.getUserNotFound();
            throw new UserNotFoundException(error.getCode(), errors.getInternalOrigin(), error.getMessage(), error.getDeveloperMessage());
        }
        return user;
    }

    @Override
    public UserModel createUser(NewUserEntity newUserEntity) {
        var user = repository.findByUsername(newUserEntity.getDocument());

        if (user != null && user.isActive()){
            throw new UserAlreadyExistsException();
        }else if(user == null){
            user = new UserModel();
            user.setCreatedAt(new Date());
        }

        user.setActive(true);
        user.setUsername(newUserEntity.getDocument());
        user.setPassword(newUserEntity.getPassword());

        user.setUpdatedAt(new Date());
        repository.save(user);

        return user;
    }

    @Override
    public void deactivateUser(String username) {
        var user = repository.findByUsername(username);

        if (user == null){
            ErrorPropertie error = errors.getUserNotFound();
            throw new UserNotFoundException(error.getCode(), errors.getInternalOrigin(), error.getMessage(), error.getDeveloperMessage());
        }

        user.setUpdatedAt(new Date());
        user.setActive(false);
        repository.save(user);
    }
}

