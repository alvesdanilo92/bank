package br.com.bank.customer.exceptions;

import br.com.bank.http.commons.exceptions.ResourseException;

public class CustomerAlreadyHasAnOpenAccountException extends ResourseException {

    public CustomerAlreadyHasAnOpenAccountException() {
        super();
    }
}
