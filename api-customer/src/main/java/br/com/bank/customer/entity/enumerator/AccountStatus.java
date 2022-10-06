package br.com.bank.customer.entity.enumerator;

public enum AccountStatus {
    ACTIVE("ACT"),
    INACTIVE("INA");

    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
