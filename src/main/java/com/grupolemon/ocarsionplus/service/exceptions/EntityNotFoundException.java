package com.grupolemon.ocarsionplus.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5051513966230400163L;

	public EntityNotFoundException(String exception) {
		super(exception);
	}
}