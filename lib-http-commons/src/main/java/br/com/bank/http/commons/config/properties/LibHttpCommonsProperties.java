package br.com.bank.http.commons.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("resttemplate")
public class LibHttpCommonsProperties{

	private int connectTimeout;
	private int readTimeout;

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
}
