package com.springbootbackend.springbootbackend.exceptions;

public class EmailNotValid extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailNotValid(String errorMessage) {
		super(errorMessage);
	}
}
