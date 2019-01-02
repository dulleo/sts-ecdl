package com.duskol.ecdl.exception;

import com.duskol.ecdl.error.ErrorCodes;

/**
 * 
 * Created by Dusko Lucic on Dec 31, 2018
 *
 */
public class DataIntegrityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7590071632854117732L;
	private ErrorCodes errorCode;

	public DataIntegrityException() {
	}

	public DataIntegrityException(String message) {
		super(message);
	}

	public DataIntegrityException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public DataIntegrityException(String message, Throwable e) {
		super(message, e);
	}

	public DataIntegrityException(String message, Throwable e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}

	public DataIntegrityException(String message, Exception e) {
		super(message, e);
	}

	public DataIntegrityException(String message, Exception e, ErrorCodes errorCode) {
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
