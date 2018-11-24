package com.duskol.ecdl.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Created by duskol on Nov 11, 2018
 *
 */
public class ExamQuestionDto extends QuestionDto implements Serializable {
	
	private static final long serialVersionUID = 6099628230448749380L;
	
	private List<ExamAnswerDto> answers;
	
	public ExamQuestionDto() {
		this.answers = new ArrayList<ExamAnswerDto>();
	}
	
	public List<ExamAnswerDto> getAnswers() {
		return answers;
	}

	@Override
	public String toString() {
		return "CompletedQuestionDto [answers=" + answers + "]";
	}
}
