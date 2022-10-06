package br.com.bank.authenticator.controller.data.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountRequest {

    @NotNull
    private int number;

    @NotNull
    private int digit;

    @NotEmpty
    @Length(min = 4, max = 4)
    @Pattern(regexp = "^\\d*$", message = "The agency must contain only numbers")
    private String agency;

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
