package br.com.bank.customer.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bank")
public class BankProperties {

    private String defaultAgency;

    public String getDefaultAgency() {
        return defaultAgency;
    }

    public void setDefaultAgency(String defaultAgency) {
        this.defaultAgency = defaultAgency;
    }
}
