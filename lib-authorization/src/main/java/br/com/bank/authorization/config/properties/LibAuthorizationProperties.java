package br.com.bank.authorization.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("authorization")
public class LibAuthorizationProperties{

	private String urlValidate;

	public String getUrlValidate() {
		return urlValidate;
	}

	public void setUrlValidate(String urlValidate) {
		this.urlValidate = urlValidate;
	}
}
