package br.com.bank.authenticator.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Validated
@ConfigurationProperties("integrations")
public class IntegrationsProperties {

    @NotEmpty
    private String checkIntegrityServiceUrl;

    public String getCheckIntegrityServiceUrl() {
        return checkIntegrityServiceUrl;
    }

    public void setCheckIntegrityServiceUrl(String checkIntegrityServiceUrl) {
        this.checkIntegrityServiceUrl = checkIntegrityServiceUrl;
    }
}
