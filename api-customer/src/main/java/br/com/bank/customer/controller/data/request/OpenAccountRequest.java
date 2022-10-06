package br.com.bank.customer.controller.data.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class OpenAccountRequest {

    @NotEmpty
    private String name;

    @CPF
    @NotEmpty
    private String document;

    @NotNull
    private LocalDate birth;

    @NotEmpty
    private String gender;

    @NotEmpty
    @Length(min = 6, max = 6)
    @Pattern(regexp = "^\\d*$", message = "The password must contain only numbers")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
