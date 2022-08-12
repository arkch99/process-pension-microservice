package com.cognizant.exception;

public class NotFoundException extends RuntimeException{
	private static final long serialVersionUID = -7938001841870054415L;

	public NotFoundException(String msg) {
		super(msg);
	}
}
