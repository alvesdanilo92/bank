package br.com.bank.customer.config;

import br.com.bank.customer.config.properties.ErrorsProperties;
import br.com.bank.customer.exceptions.CustomerAlreadyHasAnOpenAccountException;
import br.com.bank.customer.exceptions.InvalidAccountException;
import br.com.bank.http.commons.entity.ErrorEntity;
import br.com.bank.http.commons.exceptions.ResourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LocalApplicationExceptionHandler{

	@Autowired
	private ErrorsProperties errorsProperties;

	@ExceptionHandler(ResourseException.class)
	public ResponseEntity<ErrorEntity> handleException(ResourseException e) {
		var errorEntity = new ErrorEntity(e.getCode(), e.getOrigin(), e.getMessage(), e.getDeveloperMessage(), e.getData());
		return new ResponseEntity<>(errorEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerAlreadyHasAnOpenAccountException.class)
	public ResponseEntity<ErrorEntity> handleCustomerAlreadyHasAnOpenAccountException(ResourseException e) {
		var error = errorsProperties.getCustomerAlreadyHasAnOpenAccount();
		return new ResponseEntity<>(
				new ErrorEntity(error.getCode(), errorsProperties.getInternalOrigin(), error.getMessage(), error.getDeveloperMessage(), error.getData()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(InvalidAccountException.class)
	public ResponseEntity<ErrorEntity> handleInvalidAccountException(ResourseException e) {
		var error = errorsProperties.getInvalidAccount();
		return new ResponseEntity<>(
				new ErrorEntity(error.getCode(), errorsProperties.getInternalOrigin(), error.getMessage(), error.getDeveloperMessage(), error.getData()), HttpStatus.NOT_FOUND);
	}


}
