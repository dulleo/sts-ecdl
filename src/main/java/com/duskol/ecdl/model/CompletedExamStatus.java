package com.duskol.ecdl.model;

public enum CompletedExamStatus {
	
	PASSED("PASSED"),
	FAILED("FAILED");
	
	private final String status;
	
	CompletedExamStatus(final String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
