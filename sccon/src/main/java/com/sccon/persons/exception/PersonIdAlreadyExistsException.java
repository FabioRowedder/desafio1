package com.sccon.persons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PersonIdAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonIdAlreadyExistsException(String message) {
		super(message);
	}
}
