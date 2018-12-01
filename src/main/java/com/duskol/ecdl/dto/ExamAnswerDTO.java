package com.duskol.ecdl.dto;

import java.io.Serializable;

/**
 * 
 * Created by duskol on Nov 11, 2018
 *
 */
public class ExamAnswerDTO implements Serializable {
	
	private static final long serialVersionUID = 4381396183707309704L;
	
	private Long id;
	private String text;
	private Boolean isCorrect;
	private Boolean isChecked;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public Boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	@Override
	public String toString() {
		return "ExamAnswerDto [id=" + id + ", text=" + text + ", isCorrect=" + isCorrect + ", isChecked=" + isChecked
				+ "]";
	}
}
