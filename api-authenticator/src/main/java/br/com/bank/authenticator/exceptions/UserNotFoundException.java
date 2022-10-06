package br.com.bank.authenticator.exceptions;

import br.com.bank.http.commons.exceptions.ResourseException;

public class UserNotFoundException extends ResourseException {

	private static final long serialVersionUID = 5560210447762555461L;

	public UserNotFoundException(String code, String origin, String message, String developerMessage){
		super(code, origin, message, developerMessage, null);
	}
	
}
