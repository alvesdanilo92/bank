package br.com.bank.authenticator.config;

import br.com.bank.authenticator.config.properties.ErrorsProperties;
import br.com.bank.authenticator.config.properties.IntegrationsProperties;
import br.com.bank.authenticator.config.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        ErrorsProperties.class,
        JwtProperties.class,
        IntegrationsProperties.class})
public class ApiAuthenticatorApplicationProperties {

}
