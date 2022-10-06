package br.com.bank.authenticator.usecase.impl;

import br.com.bank.authenticator.config.JwtTokenUtil;
import br.com.bank.authenticator.config.properties.ErrorsProperties;
import br.com.bank.authenticator.exceptions.InvalidCredentialsException;
import br.com.bank.authenticator.exceptions.UserDisabledException;
import br.com.bank.authenticator.gateway.AccountGateway;
import br.com.bank.authenticator.usecase.AuthenticateUseCase;
import br.com.bank.authenticator.usecase.converter.AuthenticateUseCaseConverter;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class AuthenticateUseCaseImpl implements AuthenticateUseCase {

	private final ErrorsProperties errors;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final UserDetailsService jwtInMemoryUserDetailsService;
	private final AccountGateway accountGateway;
	private final AuthenticateUseCaseConverter converter;

	public AuthenticateUseCaseImpl(ErrorsProperties errors, AuthenticationManager authenticationManager,
								   JwtTokenUtil jwtTokenUtil, UserDetailsService jwtInMemoryUserDetailsService,
								   AccountGateway accountGateway, AuthenticateUseCaseConverter converter) {
		this.errors = errors;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
		this.accountGateway = accountGateway;
		this.converter = converter;
	}

	@Override
	public String execute(String username, String password) {
		authenticate(username, password);
		final var userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(username);
		final Map<String, Object> claims = new HashMap<>();
		claims.put("account", converter.toAccountResponse( accountGateway.getAccount(username) ));
		return jwtTokenUtil.generateToken(userDetails, claims);
	}
	
	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			var error = errors.getUserDisabled();
			throw new UserDisabledException(error.getCode(), errors.getInternalOrigin(), error.getMessage(),
					error.getDeveloperMessage());
		} catch (BadCredentialsException | InternalAuthenticationServiceException e) {
			var error = errors.getInvalidCredentials();
			throw new InvalidCredentialsException(error.getCode(), errors.getInternalOrigin(), error.getMessage(),
					error.getDeveloperMessage());
		}
	}

}
