package com.duskol.ecdl.dto;

import java.io.Serializable;
import java.util.List;

import com.duskol.ecdl.model.QuestionType;

/**
 * 
 * Created by duskol on Nov 11, 2018
 *
 */
public class ExamQuestionDTO implements Serializable {
	
	private static final long serialVersionUID = -8555735163303813720L;
	
	private Long id;
	private String text;
	private QuestionType type;
	private ExamQuestionStatus status;
	private List<ExamAnswerDTO> examAnswerDTOs;
	
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
	public ExamQuestionStatus getStatus() {
		return status;
	}
	public void setStatus(ExamQuestionStatus status) {
		this.status = status;
	}
	public List<ExamAnswerDTO> getExamAnswerDTOs() {
		return this.examAnswerDTOs;
	}
	public void setExamAnswerDTOs(List<ExamAnswerDTO> answers) {
		this.examAnswerDTOs = answers;
	}
	@Override
	public String toString() {
		return "ExamQuestionDTO [id=" + id + ", text=" + text + ", type=" + type + ", status=" + status
				+ ", examAnswerDTOs=" + examAnswerDTOs + "]";
	}
}
