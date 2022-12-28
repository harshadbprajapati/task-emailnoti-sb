package com.springbootbackend.springbootbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EmailAlreadyRegistered extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailAlreadyRegistered(String s) {
	}

	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "Email is already registered")
	@ExceptionHandler(EmailAlreadyRegistered.class)
	public ResponseEntity<Object> EmailAlreadyRegistered(String errorMessage) {
		System.out.println(errorMessage);
		return new ResponseEntity<>("Email is already registered",HttpStatus.NOT_ACCEPTABLE);

	}
}
