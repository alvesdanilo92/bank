package br.com.bank.authenticator.exceptions;

import br.com.bank.http.commons.exceptions.ResourseException;

public class UserDisabledException extends ResourseException {

	private static final long serialVersionUID = -6571152278310870534L;

	public UserDisabledException(String code, String origin, String message, String developerMessage){
		super(code, origin, message, developerMessage, null);
	}
	
}