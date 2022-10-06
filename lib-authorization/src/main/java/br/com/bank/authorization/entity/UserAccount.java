package br.com.bank.authorization.entity;

public class UserAccount {
	 private String document;
	 private Integer number;
	 private Integer digit;
	 private String agency;
	 private String status;

	public String getDocument() {
		return document;
	}

	public UserAccount setDocument(String document) {
		this.document = document;
		return this;
	}

	public Integer getNumber() {
		return number;
	}

	public UserAccount setNumber(Integer number) {
		this.number = number;
		return this;
	}

	public Integer getDigit() {
		return digit;
	}

	public UserAccount setDigit(Integer digit) {
		this.digit = digit;
		return this;
	}

	public String getAgency() {
		return agency;
	}

	public UserAccount setAgency(String agency) {
		this.agency = agency;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public UserAccount setStatus(String status) {
		this.status = status;
		return this;
	}
}
