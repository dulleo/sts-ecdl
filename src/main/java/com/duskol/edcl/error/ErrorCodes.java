package com.duskol.edcl.error;

public enum ErrorCodes {
	
	TEST_NOT_FOUND(1);
	
	private final Integer code;
	
	ErrorCodes(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
}
