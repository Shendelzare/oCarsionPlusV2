package com.grupolemon.ocarsionplus.service.exceptions;

public class PrecioServiceException extends Exception {

	private static final long serialVersionUID = -1706624542158619239L;

	public PrecioServiceException(Throwable cause) {
		super(cause);
	}

	public PrecioServiceException(String message) {
		super(message);
	}

	public PrecioServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}