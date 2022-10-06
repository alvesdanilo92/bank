package br.com.bank.authenticator.entity;

public class NewUserEntity {
    private String document;
    private int number;
    private int digit;
    private String agency;
    private String password;

    public String getDocument() {
        return document;
    }

    public NewUserEntity setDocument(String document) {
        this.document = document;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public NewUserEntity setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getDigit() {
        return digit;
    }

    public NewUserEntity setDigit(int digit) {
        this.digit = digit;
        return this;
    }

    public String getAgency() {
        return agency;
    }

    public NewUserEntity setAgency(String agency) {
        this.agency = agency;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public NewUserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
}
