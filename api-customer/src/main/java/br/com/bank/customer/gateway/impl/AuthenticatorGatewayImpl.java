package br.com.bank.customer.gateway.impl;

import br.com.bank.customer.config.properties.IntegrationsProperties;
import br.com.bank.customer.gateway.AuthenticatorGateway;
import br.com.bank.customer.gateway.data.request.CreateUserGatewayRequest;
import br.com.bank.customer.gateway.data.response.CreateAuthenticationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthenticatorGatewayImpl implements AuthenticatorGateway {

    private final IntegrationsProperties integrationsProperties;
    private final WebClient webClient;

    public AuthenticatorGatewayImpl(IntegrationsProperties integrationsProperties, WebClient webClient) {
        this.integrationsProperties = integrationsProperties;
        this.webClient = webClient;
    }

    @Override
    public void createUser(CreateUserGatewayRequest request, String document, String password) {
        webClient.post()
                .uri(integrationsProperties.getCreateUserServiceUrl())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("document", document)
                .header("password", password)
                .body(Mono.just(request), CreateUserGatewayRequest.class)
                .retrieve()
                .bodyToMono(CreateAuthenticationResponse.class)
                .block();
    }

    @Override
    public void deactivateUser(String authorization) {
        webClient.put()
                .uri(integrationsProperties.getDeactivateUserServiceUrl())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("authorization", authorization)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
