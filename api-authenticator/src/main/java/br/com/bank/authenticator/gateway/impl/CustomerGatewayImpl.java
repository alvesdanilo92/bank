package br.com.bank.authenticator.gateway.impl;

import br.com.bank.authenticator.config.properties.IntegrationsProperties;
import br.com.bank.authenticator.exceptions.GatewayException;
import br.com.bank.authenticator.exceptions.InvalidAccountException;
import br.com.bank.authenticator.gateway.CustomerGateway;
import br.com.bank.authenticator.gateway.data.request.CheckIntegrityGatewayRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CustomerGatewayImpl implements CustomerGateway {

    private final IntegrationsProperties integrationsProperties;
    private final WebClient webClient;

    public CustomerGatewayImpl(IntegrationsProperties integrationsProperties, WebClient webClient) {
        this.integrationsProperties = integrationsProperties;
        this.webClient = webClient;
    }

    @Override
    public void checkIntegrity(CheckIntegrityGatewayRequest checkIntegrityGatewayRequest) {

        webClient.post()
                .uri(integrationsProperties.getCheckIntegrityServiceUrl())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(checkIntegrityGatewayRequest), CheckIntegrityGatewayRequest.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {throw new InvalidAccountException();})
                .onStatus(HttpStatus::is5xxServerError, response -> {throw new GatewayException();})
                .bodyToMono(Void.class)
                .block();
    }
}
