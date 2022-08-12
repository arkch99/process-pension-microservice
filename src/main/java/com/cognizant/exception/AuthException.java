package com.cognizant.exception;

public class AuthException extends RuntimeException{
	private static final long serialVersionUID = 8029036618910647410L;

	public AuthException(String message) {
		super(message);
	}
}
