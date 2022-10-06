package br.com.bank.customer.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Validated
@ConfigurationProperties("integrations")
public class IntegrationsProperties {

    @NotEmpty
    private String createUserServiceUrl;

    @NotEmpty
    private String deactivateUserServiceUrl;

    public String getCreateUserServiceUrl() {
        return createUserServiceUrl;
    }

    public void setCreateUserServiceUrl(String createUserServiceUrl) {
        this.createUserServiceUrl = createUserServiceUrl;
    }

    public String getDeactivateUserServiceUrl() {
        return deactivateUserServiceUrl;
    }

    public void setDeactivateUserServiceUrl(String deactivateUserServiceUrl) {
        this.deactivateUserServiceUrl = deactivateUserServiceUrl;
    }
}
