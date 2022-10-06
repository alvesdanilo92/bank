package br.com.bank.http.commons.config;

import br.com.bank.http.commons.controller.converter.ReponseConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
 
@RestControllerAdvice
public class RestAdviceConfig<T> implements ResponseBodyAdvice<T> {

	private final ReponseConverter<T> reponseConverter;

	public RestAdviceConfig(ReponseConverter<T> reponseConverter) {
		this.reponseConverter = reponseConverter;
	}

	@Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        String className = returnType.getContainingClass().toString();
        return className.contains("br.com.bank");
    }

	@Override
	@SuppressWarnings("unchecked")
	public T beforeBodyWrite(T body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		return (T) reponseConverter.toReponse(body);
	}
}