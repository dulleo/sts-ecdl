package com.duskol.ecdl.dto;

import java.io.Serializable;

/**
 * 
 * Created by duskol on Nov 11, 2018
 *
 */
public class AnswerDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -152534843061428322L;
	private Long id;
	private String text;
	private Boolean isCorrect;
	
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
	@Override
	public String toString() {
		return "AnswerDto [id=" + id + ", text=" + text + ", isCorrect=" + isCorrect + "]";
	}
}
