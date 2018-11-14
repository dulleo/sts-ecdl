package com.duskol.edcl.controller.exception;

import com.duskol.edcl.error.ErrorCodes;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8228294046628894150L;
	
	private ErrorCodes errorCode;

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}
	
	public ResourceNotFoundException() {}
	
	public ResourceNotFoundException(String meesge) {
		super(meesge);
	}
	
	public ResourceNotFoundException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ResourceNotFoundException(String message, Throwable e) {
		super(message, e);
	}

	public ResourceNotFoundException(String message, Throwable e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}

	public ResourceNotFoundException(String message, Exception e) {
		super(message, e);
	}

	public ResourceNotFoundException(String message, Exception e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}
}
