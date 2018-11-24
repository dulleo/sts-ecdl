package com.duskol.ecdl.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Created by duskol on Nov 11, 2018
 *
 */
public class ExamTestDto extends TestDto implements Serializable {
	
	private static final long serialVersionUID = -7556133312267521198L;
	
	private List<ExamQuestionDto> examQuestions;
	
	public ExamTestDto() {
		this.examQuestions = new ArrayList<ExamQuestionDto>();
	}

	public List<ExamQuestionDto> getExamQuestions() {
		return examQuestions;
	}

	@Override
	public String toString() {
		return "ExamTestDto [examQuestions=" + examQuestions + "]";
	}
}
