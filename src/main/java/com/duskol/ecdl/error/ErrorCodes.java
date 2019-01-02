package com.duskol.ecdl.error;

public enum ErrorCodes {
	
	TEST_NOT_FOUND(1),
	QUESTION_NOT_FOUND(2),
	QUESTIONS_NOT_FOUND(3), 
	ANSWERS_NOT_FOUND(4), 
	COMPLETED_EXAM_NOT_FOUND(5), 
	TESTS_NOT_FOUND(6), 
	TEST_CAN_NOT_BE_CREATED(7), 
	TEST_CAN_NOT_BE_PROVIDED(8), 
	QUESTION_CAN_NOT_BE_CREATED(9);
	
	private final Integer code;
	
	ErrorCodes(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
}
