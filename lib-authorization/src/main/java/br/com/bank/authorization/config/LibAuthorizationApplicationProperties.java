package br.com.bank.authorization.config;

import br.com.bank.authorization.config.properties.LibAuthorizationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({LibAuthorizationProperties.class})
public class LibAuthorizationApplicationProperties {

}
