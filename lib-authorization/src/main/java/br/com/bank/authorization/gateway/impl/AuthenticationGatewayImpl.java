package br.com.bank.authorization.gateway.impl;

import br.com.bank.authorization.config.properties.LibAuthorizationProperties;
import br.com.bank.authorization.entity.UserAccount;
import br.com.bank.authorization.gateway.AuthenticationGateway;
import br.com.bank.authorization.gateway.data.response.UserAccountResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationGatewayImpl implements AuthenticationGateway{

	private final LibAuthorizationProperties properties;
	private final RestTemplate restTemplate;

	public AuthenticationGatewayImpl(LibAuthorizationProperties properties, RestTemplate restTemplate) {
		this.properties = properties;
		this.restTemplate = restTemplate;
	}

	@Override
	public UserAccount validateToken(String token) {
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", token);    
	    headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
	    HttpEntity<Void> request = new HttpEntity<>(null, headers);
	   
		ResponseEntity<UserAccountResponse> response = restTemplate.exchange(properties.getUrlValidate(),
				HttpMethod.GET, request, UserAccountResponse.class);

	    return response.getBody().getResults();
	}
	

}
