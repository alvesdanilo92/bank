package br.com.bank.authenticator.config;

import br.com.bank.authenticator.config.properties.ErrorsProperties;
import br.com.bank.authenticator.controller.data.response.ErrorResponse;
import br.com.bank.authenticator.entity.ErrorEntity;
import br.com.bank.authenticator.exceptions.*;
import br.com.bank.http.commons.exceptions.ResourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	private static final String INVALID_PARAMETERS = "Invalid Parameters";
	private static final String INVALID_ACCOUNT = "Invalid Account";

	@Autowired
	private ErrorsProperties errors;
	
	@Autowired
	private Environment env;

	@ExceptionHandler(ResourseException.class)
	public ResponseEntity<Object> handleException(ResourseException e) {
		return new ResponseEntity<>(makeErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(ResourseException e) {
	    return new ResponseEntity<>(makeErrorResponse(e), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Object> handleUserAlreadyExistsException(ResourseException e) {
		var error = errors.getUserAlreadyExists();
		var resourseException = new ResourseException(error.getCode(), errors.getInternalOrigin(), error.getMessage(), error.getMessage(), error.getData());
		return new ResponseEntity<>(makeErrorResponse(resourseException), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(InvalidAccountException.class)
	public ResponseEntity<Object> handleInvalidAccountExceptionException(ResourseException e) {
		var resourseException =
				new ResourseException(
						String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()),
						errors.getInternalOrigin(),
						INVALID_PARAMETERS,
						INVALID_ACCOUNT,
						null
				);
		return new ResponseEntity<>(makeErrorResponse(resourseException), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler({UserDisabledException.class, InvalidCredentialsException.class})
	public ResponseEntity<Object> handleExceptionUnauthorized(ResourseException e) {
	    return new ResponseEntity<>(makeErrorResponse(e), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
		var resourseException =
				new ResourseException(
						String.valueOf(HttpStatus.BAD_REQUEST.value()),
						errors.getInternalOrigin(),
						INVALID_PARAMETERS,
						e.getAllErrors().get(0).getDefaultMessage(),
						null
						);
		return new ResponseEntity<>(makeErrorResponse(resourseException), HttpStatus.BAD_REQUEST);
	}

	private ErrorResponse makeErrorResponse(ResourseException e) {
		return new ErrorResponse()
					.setError(new ErrorEntity(e));
	}

}
