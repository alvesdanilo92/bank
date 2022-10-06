package br.com.bank.http.commons.config;

import br.com.bank.http.commons.config.properties.LibHttpCommonsProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class RestTemplateConfig {

	private final LibHttpCommonsProperties properties;

	public RestTemplateConfig(LibHttpCommonsProperties properties) {
		this.properties = properties;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder
		    		.setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
		            .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
	            .build();
	}
}
