package com.yash.tcvm.exception;

public class UnavailableFileException extends RuntimeException {

	private static final long serialVersionUID = 7271027851000290204L;

	public UnavailableFileException(String errorMessage) {
		super(errorMessage);
	}
}
