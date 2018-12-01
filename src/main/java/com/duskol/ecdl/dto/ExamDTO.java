package com.duskol.ecdl.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * Created by duskol on Nov 30, 2018
 *
 */
public class ExamDTO implements Serializable{
	
	private static final long serialVersionUID = -3707069719107558680L;
	
	@JsonInclude
	private ExamTestDTO examTestDTO;
	@JsonInclude
	private CompletedExamDTO completedExamDTO;
	
	public ExamTestDTO getExamTestDTO() {
		return examTestDTO;
	}
	public void setExamTestDTO(ExamTestDTO examTestDTO) {
		this.examTestDTO = examTestDTO;
	}
	public CompletedExamDTO getCompletedExamDTO() {
		return completedExamDTO;
	}
	public void setCompletedExamDTO(CompletedExamDTO completedExamDTO) {
		this.completedExamDTO = completedExamDTO;
	}
	@Override
	public String toString() {
		return "ExamDTO [examTestDTO=" + examTestDTO + ", completedExamDTO=" + completedExamDTO + "]";
	}
}
