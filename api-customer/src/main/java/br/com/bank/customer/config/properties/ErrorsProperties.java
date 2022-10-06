package br.com.bank.customer.config.properties;

import br.com.bank.customer.config.properties.data.ErrorPropertie;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties("errors")
public class ErrorsProperties {

    @NotEmpty
    private String internalOrigin;

    @NotNull
    private ErrorPropertie gatewayException;

    @NotNull
    private ErrorPropertie customerAlreadyHasAnOpenAccount;

    @NotNull
    private ErrorPropertie invalidAccount;

    public String getInternalOrigin() {
        return internalOrigin;
    }

    public void setInternalOrigin(String internalOrigin) {
        this.internalOrigin = internalOrigin;
    }

    public ErrorPropertie getGatewayException() {
        return gatewayException;
    }

    public void setGatewayException(ErrorPropertie gatewayException) {
        this.gatewayException = gatewayException;
    }

    public ErrorPropertie getCustomerAlreadyHasAnOpenAccount() {
        return customerAlreadyHasAnOpenAccount;
    }

    public void setCustomerAlreadyHasAnOpenAccount(ErrorPropertie customerAlreadyHasAnOpenAccount) {
        this.customerAlreadyHasAnOpenAccount = customerAlreadyHasAnOpenAccount;
    }

    public ErrorPropertie getInvalidAccount() {
        return invalidAccount;
    }

    public void setInvalidAccount(ErrorPropertie invalidAccount) {
        this.invalidAccount = invalidAccount;
    }
}
