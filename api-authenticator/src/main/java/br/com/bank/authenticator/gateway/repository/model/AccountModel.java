package br.com.bank.authenticator.gateway.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "aut_accounts")
public class AccountModel {

    private String document;
    private int number;
    private int digit;
    private String agency;

    @Id
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}
