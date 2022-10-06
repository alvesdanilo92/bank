package br.com.bank.http.commons.controller.converter.impl;

import br.com.bank.http.commons.controller.converter.ReponseConverter;
import br.com.bank.http.commons.entity.Metadata;
import br.com.bank.http.commons.entity.ReponseDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ReponseConverterImpl<T> implements ReponseConverter<T> {
	
	@Autowired
	private Environment env;

	@Override
	@SuppressWarnings("unchecked")
	public ReponseDefault<T> toReponse(T response) {
		return new ReponseDefault<T>()
				.setMetadata(new Metadata().setStage(Arrays.toString(env.getActiveProfiles())))
				.setResults(response);
	}

}
