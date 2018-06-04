package com.yash.tcvm.exception;

public class EmptyFileNameException extends RuntimeException {

	private static final long serialVersionUID = -3195184561504511629L;

	public EmptyFileNameException(String errorMessage) {
		super(errorMessage);
	}

}
