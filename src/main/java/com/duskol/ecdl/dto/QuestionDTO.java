package com.duskol.ecdl.dto;

import java.io.Serializable;

import com.duskol.ecdl.model.QuestionType;

/**
 * 
 * Created by duskol on Nov 4, 2018
 *
 */
public class QuestionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8734731300134684231L;
	
	private Long id;
	private String text;
	private QuestionType type;
	
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
	public QuestionType getType() {
		return type;
	}
	public void setType(QuestionType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "QuestionDto [id=" + id + ", text=" + text + ", type=" + type + "]";
	}
}
