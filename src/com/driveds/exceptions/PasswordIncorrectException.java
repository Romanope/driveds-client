package com.driveds.exceptions;

public class PasswordIncorrectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordIncorrectException (String message) {
		super(message);
	}
	
	public PasswordIncorrectException (String message, Throwable error) {
		super(message, error);
	}
	
}
