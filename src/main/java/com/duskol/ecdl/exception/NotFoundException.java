package com.duskol.ecdl.exception;

import com.duskol.ecdl.error.ErrorCodes;

public class NotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7521222622700745548L;
	private ErrorCodes errorCode;
	
	public NotFoundException() { }
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public NotFoundException(String message, Throwable e) {
		super(message, e);
	}
	
	public NotFoundException(String message, Throwable e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}
	
	public NotFoundException(String message, Exception e) {
		super(message, e);
	}
	
	public NotFoundException(String message, Exception e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}
	
	public ErrorCodes getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

}
