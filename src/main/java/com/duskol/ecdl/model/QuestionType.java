package com.duskol.edcl.model;

public enum QuestionType {
	
	SINGLE("SINGLE"),
	MULTIPLE("MULTIPLE");
	
	private final String type;
	
	QuestionType(final String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
