package br.com.bank.authenticator.controller.data.response;

public class AccountResponse {
    private int number;
    private int digit;
    private String agency;

    public int getNumber() {
        return number;
    }

    public AccountResponse setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getDigit() {
        return digit;
    }

    public AccountResponse setDigit(int digit) {
        this.digit = digit;
        return this;
    }

    public String getAgency() {
        return agency;
    }

    public AccountResponse setAgency(String agency) {
        this.agency = agency;
        return this;
    }
}
