package br.com.bank.http.commons.config;

import br.com.bank.http.commons.config.properties.CustomLogbookProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CustomLogbookProperties.class)
public class CustomLogbookPropertyConfiguration {
}
