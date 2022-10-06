package br.com.bank.customer.config;

import br.com.bank.customer.config.properties.BankProperties;
import br.com.bank.customer.config.properties.ErrorsProperties;
import br.com.bank.customer.config.properties.IntegrationsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        ErrorsProperties.class,
        BankProperties.class,
        IntegrationsProperties.class})
public class ApiCustomerApplicationProperties {
}
