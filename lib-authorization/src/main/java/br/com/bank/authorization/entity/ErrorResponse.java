package br.com.bank.authorization.entity;

public class ErrorResponse {
	private Metadata metadata;
	private ErrorEntity error;

	public Metadata getMetadata() {
		return metadata;
	}

	public ErrorResponse setMetadata(Metadata metadata) {
		this.metadata = metadata;
		return this;
	}

	public ErrorEntity getError() {
		return error;
	}

	public ErrorResponse setError(ErrorEntity error) {
		this.error = error;
		return this;
	}
}
