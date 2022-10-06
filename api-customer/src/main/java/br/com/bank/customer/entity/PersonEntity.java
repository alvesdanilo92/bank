package br.com.bank.customer.entity;

import java.time.LocalDate;

public class PersonEntity {

    private String name;
    private String document;
    private LocalDate birth;
    private String gender;

    public String getName() {
        return name;
    }

    public PersonEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDocument() {
        return document;
    }

    public PersonEntity setDocument(String document) {
        this.document = document;
        return this;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public PersonEntity setBirth(LocalDate birth) {
        this.birth = birth;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public PersonEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
