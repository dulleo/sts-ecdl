package com.duskol.edcl.dto;

import java.util.List;

/**
 * 
 * Created by duskol on Nov 4, 2018
 *
 */
public class TestDto {
	
	private Long id;
	private String name;
	private List<QuestionDto> questions;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<QuestionDto> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDto> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "TestDto [id=" + id + ", name=" + name + ", questions=" + questions + "]";
	}
}
