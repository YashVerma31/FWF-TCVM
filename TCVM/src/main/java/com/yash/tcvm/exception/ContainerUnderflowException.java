package com.yash.tcvm.exception;

public class ContainerUnderflowException extends Exception {

	private static final long serialVersionUID = 5574959741983885046L;

	public ContainerUnderflowException(String errorMessage) {
		super(errorMessage);
	}
}
