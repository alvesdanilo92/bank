package br.com.bank.authorization.exceptions;

public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6821765201331397724L;
	
	public InvalidCredentialsException(String message) {
		super(message);
	}
	
}