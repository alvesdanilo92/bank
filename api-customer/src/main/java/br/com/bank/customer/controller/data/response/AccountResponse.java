package br.com.bank.customer.controller.data.response;

import java.time.LocalDateTime;

public class AccountResponse {
	private int number;
	private int digit;
	private String agency;
	private String status;
	private LocalDateTime openedAt;
	private LocalDateTime closedAt;


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

	public String getStatus() {
		return status;
	}

	public AccountResponse setStatus(String status) {
		this.status = status;
		return this;
	}

	public LocalDateTime getOpenedAt() {
		return openedAt;
	}

	public AccountResponse setOpenedAt(LocalDateTime openedAt) {
		this.openedAt = openedAt;
		return this;
	}

	public LocalDateTime getClosedAt() {
		return closedAt;
	}

	public AccountResponse setClosedAt(LocalDateTime closedAt) {
		this.closedAt = closedAt;
		return this;
	}
}
