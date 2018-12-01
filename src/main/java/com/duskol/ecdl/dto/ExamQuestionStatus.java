package com.duskol.ecdl.dto;

public enum ExamQuestionStatus {
	
	CORRECT("CORRECT "),
	INCORRECT("INCORRECT"),
	UNANSWERED("UNANSWERED");
	
	private final String status;
	
	ExamQuestionStatus(final String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}

}
