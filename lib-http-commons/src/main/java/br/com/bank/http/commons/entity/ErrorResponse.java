package br.com.bank.http.commons.entity;

public class ErrorResponse {
	private Metadata metadata;
	private ErrorEntity error;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public ErrorEntity getError() {
		return error;
	}

	public void setError(ErrorEntity error) {
		this.error = error;
	}
}
