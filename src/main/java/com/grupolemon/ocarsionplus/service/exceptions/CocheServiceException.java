package com.grupolemon.ocarsionplus.service.exceptions;

public class CocheServiceException extends Exception {

	private static final long serialVersionUID = -1706624542158619239L;

	public CocheServiceException(Throwable cause) {
			super(cause);
		}

	public CocheServiceException(String message) {
			super(message);
		}

	public CocheServiceException(String message, Throwable cause) {
			super(message, cause);
		}

}
