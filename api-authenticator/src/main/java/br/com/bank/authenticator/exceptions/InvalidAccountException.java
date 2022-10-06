package br.com.bank.authenticator.exceptions;


import br.com.bank.http.commons.exceptions.ResourseException;

public class InvalidAccountException extends ResourseException {

	private static final long serialVersionUID = 108007876338870158L;

	public InvalidAccountException(){
		super();
	}

}