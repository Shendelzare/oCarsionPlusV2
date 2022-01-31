package com.grupolemon.ocarsionplus.service.exceptions;

public class UsuarioServiceException extends Exception {

	private static final long serialVersionUID = -1706624542158619239L;

	public UsuarioServiceException(Throwable cause) {
		super(cause);
	}

	public UsuarioServiceException(String message) {
		super(message);
	}

	public UsuarioServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}