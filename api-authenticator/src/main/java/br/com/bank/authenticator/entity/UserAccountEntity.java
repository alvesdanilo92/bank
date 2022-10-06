package br.com.bank.authenticator.entity;

public class UserAccountEntity {
    private String document;
    private String number;
    private String digit;
    private String agency;

    public String getDocument() {
        return document;
    }

    public UserAccountEntity setDocument(String document) {
        this.document = document;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public UserAccountEntity setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getDigit() {
        return digit;
    }

    public UserAccountEntity setDigit(String digit) {
        this.digit = digit;
        return this;
    }

    public String getAgency() {
        return agency;
    }

    public UserAccountEntity setAgency(String agency) {
        this.agency = agency;
        return this;
    }
}
