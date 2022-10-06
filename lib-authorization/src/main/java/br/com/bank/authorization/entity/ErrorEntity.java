package br.com.bank.authorization.entity;

public class ErrorEntity {
	private final String code;
	private final String origin;
	private final String invalidToken;
	private final String developerMessage;

	public ErrorEntity(String code, String origin, String invalidToken, String developerMessage) {
		this.code = code;
		this.origin = origin;
		this.invalidToken = invalidToken;
		this.developerMessage = developerMessage;
	}

	public String getCode() {
		return code;
	}

	public String getOrigin() {
		return origin;
	}

	public String getInvalidToken() {
		return invalidToken;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}
}
