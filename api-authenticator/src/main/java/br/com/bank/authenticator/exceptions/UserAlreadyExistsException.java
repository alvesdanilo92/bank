package br.com.bank.authenticator.exceptions;

import br.com.bank.http.commons.exceptions.ResourseException;

public class UserAlreadyExistsException  extends ResourseException{

    public UserAlreadyExistsException(){
        super();
    }

}
