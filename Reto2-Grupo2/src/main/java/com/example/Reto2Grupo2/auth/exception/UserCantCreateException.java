package com.example.Reto2Grupo2.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "No se puede crear el usuario")
public class UserCantCreateException extends Exception{

	
	private static final long serialVersionUID = -678102909825845235L;
	
	public UserCantCreateException(String errorMessage) {
		super(errorMessage);
	}

}
