package com.mouzetech.maniadecrepeapp.services.exception;

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);		
	}

	public DataIntegrityException(String message) {
		super(message);
	}
	
	

}
