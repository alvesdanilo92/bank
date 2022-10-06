package br.com.bank.authenticator.controller.data.response;

import br.com.bank.authenticator.entity.ErrorEntity;

public class ErrorResponse {
	private ErrorEntity error;

	public ErrorEntity getError() {
		return error;
	}

	public ErrorResponse setError(ErrorEntity error) {
		this.error = error;
		return this;
	}
}
