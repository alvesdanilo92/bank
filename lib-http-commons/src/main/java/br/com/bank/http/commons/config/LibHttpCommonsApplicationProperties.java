package br.com.bank.http.commons.config;

import br.com.bank.http.commons.config.properties.LibHttpCommonsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({LibHttpCommonsProperties.class})
public class LibHttpCommonsApplicationProperties {

}
