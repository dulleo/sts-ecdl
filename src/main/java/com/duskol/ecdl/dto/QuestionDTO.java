package com.duskol.ecdl.dto;

import java.io.Serializable;
import java.util.List;

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
	private List<AnswerDTO> answers;
	
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
	public List<AnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		return "QuestionDTO [id=" + id + ", text=" + text + ", type=" + type + ", answers=" + answers + "]";
	}
	
}
