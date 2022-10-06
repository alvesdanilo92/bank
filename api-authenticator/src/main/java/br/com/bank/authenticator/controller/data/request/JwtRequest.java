package br.com.bank.authenticator.controller.data.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class JwtRequest implements Serializable {
	
	private static final long serialVersionUID = 4462929319796827980L;
	
	@NotNull(message = "username is mandatory")
	private String username;
	
	@NotNull(message = "password is mandatory")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
