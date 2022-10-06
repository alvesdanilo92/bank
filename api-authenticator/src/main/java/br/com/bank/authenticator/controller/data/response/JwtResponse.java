package br.com.bank.authenticator.controller.data.response;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -3465049802135145410L;
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
