package br.com.bank.authorization.interceptor;

import br.com.bank.authorization.annotation.AuthenticationRequered;
import br.com.bank.authorization.entity.ErrorEntity;
import br.com.bank.authorization.entity.ErrorResponse;
import br.com.bank.authorization.entity.Metadata;
import br.com.bank.authorization.exceptions.InvalidCredentialsException;
import br.com.bank.authorization.gateway.AuthenticationGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor{

	static final Logger log = LogManager.getLogger(AuthorizationInterceptor.class.getName());

	private final Environment env;
	private final AuthenticationGateway authenticationGateway;
	
	private static final String CODE = "SEC001";
	private static final String ORIGIN = "INTERNAL";
	private static final String INVALID_TOKEN = "Invalid Token";
	private static final String DEVELOPER_MESSAGE = "JWT Token has expired or Invalid";

	public AuthorizationInterceptor(Environment env, AuthenticationGateway authenticationGateway) {
		this.env = env;
		this.authenticationGateway = authenticationGateway;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		boolean isValidToken = true;
		if(HandlerMethod.class.isAssignableFrom(handler.getClass())){
			final var handlerMethod = (HandlerMethod) handler;
			final var method = handlerMethod.getMethod();
			
			if(method.getDeclaringClass().isAnnotationPresent(AuthenticationRequered.class) ||
					(method.isAnnotationPresent(AuthenticationRequered.class)) && 
						handlerMethod.hasMethodAnnotation(AuthenticationRequered.class)) {
				try {
					authorize(request, handlerMethod);
				}catch (InvalidCredentialsException e) {
					log.warn(e.getMessage());
					var metadata = new Metadata().setStage(Arrays.toString(env.getActiveProfiles()));
					var errorResponse = new ErrorResponse()
								.setMetadata(metadata)
								.setError(new ErrorEntity(CODE, ORIGIN, INVALID_TOKEN, DEVELOPER_MESSAGE));
					
					response.getWriter().write(convertObjectToJson(errorResponse));
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);

					isValidToken = false;
				}
			}
		}
		return isValidToken;
	}
	
	private void authorize(HttpServletRequest request, HandlerMethod handlerMethod) {
		AuthenticationRequered authenticationRequered = getAnnotation(handlerMethod);
		if(authenticationRequered != null && 
				authenticationRequered.value()) {
			String requestTokenHeader = request.getHeader("Authorization");
			try {
				var user = authenticationGateway.validateToken(requestTokenHeader);
				request.setAttribute("UserAccount", user);
			}catch (Exception e) {
				throw new InvalidCredentialsException(e.getMessage());
			}
		}
	}
	
	private AuthenticationRequered getAnnotation(HandlerMethod handlerMethod) {
		AuthenticationRequered authenticationRequered = 
				handlerMethod.getMethodAnnotation(AuthenticationRequered.class);
		
		if(authenticationRequered == null) {
			authenticationRequered = handlerMethod
											.getMethod()
											.getDeclaringClass()
											.getAnnotation(AuthenticationRequered.class);
		}
		
		return authenticationRequered;
	}
	
    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
