package br.com.bank.http.commons.exceptions;

import java.util.Map;

public class ResourseException extends RuntimeException {

	private static final long serialVersionUID = 1535030735068144191L;

	private final String code;
	private final String origin;
	private final String message;
	private final String developerMessage;
	private final Map<String, String> data;

	public ResourseException(){
		this.code = null;
		this.origin = null;
		this.message = null;
		this.developerMessage = null;
		this.data = null;
	}
	public ResourseException(String code, String origin, String message, String developerMessage, Map<String, String> data) {
		this.code = code;
		this.origin = origin;
		this.message = message;
		this.developerMessage = developerMessage;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public String getOrigin() {
		return origin;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public Map<String, String> getData() {
		return data;
	}
}
