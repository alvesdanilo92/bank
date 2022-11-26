package br.com.bank.authenticator.controller.data.response;

public class CreateAuthenticationResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public CreateAuthenticationResponse(String message) {
        this.message = message;
    }
}
