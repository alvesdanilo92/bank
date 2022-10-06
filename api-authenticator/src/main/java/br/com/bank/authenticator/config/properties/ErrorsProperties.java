package br.com.bank.authenticator.config.properties;

import br.com.bank.authenticator.config.properties.data.ErrorPropertie;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("errors")
public class ErrorsProperties {

	private String internalOrigin;
	private ErrorPropertie userNotFound;
	private ErrorPropertie userDisabled;
	private ErrorPropertie invalidCredentials;
	private ErrorPropertie userAlreadyExists;

	public String getInternalOrigin() {
		return internalOrigin;
	}

	public void setInternalOrigin(String internalOrigin) {
		this.internalOrigin = internalOrigin;
	}

	public ErrorPropertie getUserNotFound() {
		return userNotFound;
	}

	public void setUserNotFound(ErrorPropertie userNotFound) {
		this.userNotFound = userNotFound;
	}

	public ErrorPropertie getUserDisabled() {
		return userDisabled;
	}

	public void setUserDisabled(ErrorPropertie userDisabled) {
		this.userDisabled = userDisabled;
	}

	public ErrorPropertie getInvalidCredentials() {
		return invalidCredentials;
	}

	public void setInvalidCredentials(ErrorPropertie invalidCredentials) {
		this.invalidCredentials = invalidCredentials;
	}

	public ErrorPropertie getUserAlreadyExists() {
		return userAlreadyExists;
	}

	public void setUserAlreadyExists(ErrorPropertie userAlreadyExists) {
		this.userAlreadyExists = userAlreadyExists;
	}
}
