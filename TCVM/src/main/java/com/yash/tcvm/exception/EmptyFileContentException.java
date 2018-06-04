package com.yash.tcvm.exception;

public class EmptyFileContentException extends RuntimeException {

	private static final long serialVersionUID = -4446199971225418671L;

	public EmptyFileContentException(String errorMessage) {
		super(errorMessage);
	}

}
