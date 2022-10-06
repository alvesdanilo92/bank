package br.com.bank.authenticator.entity;

import br.com.bank.http.commons.exceptions.ResourseException;

import java.util.Map;

public class ErrorEntity {

    private String code;
    private String origin;
    private String message;
    private String developerMessage;
    private Map<String, String> data;

    public ErrorEntity(ResourseException resourseException) {
        this(resourseException.getCode(), resourseException.getOrigin(), resourseException.getMessage(),
                resourseException.getDeveloperMessage(), resourseException.getData());
    }

    public ErrorEntity(String code, String origin, String message, String developerMessage, Map<String, String> data) {
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
