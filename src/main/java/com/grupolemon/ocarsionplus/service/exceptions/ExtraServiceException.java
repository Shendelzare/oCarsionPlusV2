package com.grupolemon.ocarsionplus.service.exceptions;

public class ExtraServiceException  extends Exception {

	private static final long serialVersionUID = -1726624542158619239L;

	public ExtraServiceException(Throwable cause) {
		super(cause);
	}

	public ExtraServiceException(String message) {
		super(message);
	}

	public ExtraServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
