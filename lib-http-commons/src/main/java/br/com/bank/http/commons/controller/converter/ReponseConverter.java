package br.com.bank.http.commons.controller.converter;

import br.com.bank.http.commons.entity.ReponseDefault;

public interface ReponseConverter<T> {

	ReponseDefault<T> toReponse(T response);
}
