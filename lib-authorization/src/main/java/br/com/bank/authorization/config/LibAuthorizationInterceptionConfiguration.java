package br.com.bank.authorization.config;

import br.com.bank.authorization.interceptor.AuthorizationInterceptor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class LibAuthorizationInterceptionConfiguration implements WebMvcConfigurer {

	private final AuthorizationInterceptor authorizationInterceptor;

    public LibAuthorizationInterceptionConfiguration(AuthorizationInterceptor authorizationInterceptor) {
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }
	
}
