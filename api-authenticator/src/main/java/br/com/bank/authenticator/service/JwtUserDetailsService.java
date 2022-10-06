package br.com.bank.authenticator.service;

import br.com.bank.authenticator.usecase.FindUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private FindUserUseCase findUserUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserUseCase.execute(username, null);
    }

}
