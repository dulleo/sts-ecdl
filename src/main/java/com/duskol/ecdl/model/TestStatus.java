package com.duskol.ecdl.model;
/**
 * 
 * Created by duskol on Dec 15, 2018
 *
 */
public enum TestStatus {
	
	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE");
	
	private final String status;
	
	TestStatus(final String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
