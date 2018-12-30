package com.duskol.ecdl.utils;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.duskol.ecdl.dto.AnswerDTO;
import com.duskol.ecdl.dto.CompletedExamDTO;
import com.duskol.ecdl.dto.QuestionDTO;
import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.model.Answer;
import com.duskol.ecdl.model.CompletedExam;
import com.duskol.ecdl.model.CompletedExamStatus;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.model.Test;

/**
 * 
 * Created by duskol on Dec 1, 2018
 *
 */
@Component
public class DTOToEntityConverter {

	/**
	 * 
	 * @param test
	 * @return
	 */
	public CompletedExam convert(Test test) {
		
		CompletedExam c = new CompletedExam();
		c.setTotalCorrect(0);
		c.setTotalIncorrect(0);
		c.setTotalUnanswered(0);
		c.setPassingLimit(test.getPassingLimit());
		c.setScore(new BigDecimal("0"));
		c.setStatus(CompletedExamStatus.FAILED);
		c.setIsCompleted(false);
		c.setTest(test);
		Date dateNow = new Date();
		c.setStartDate(dateNow);
		c.setEndDate(dateNow);
		return c;
	}

	/**
	 * 
	 * @param completedExamDTO
	 * @return
	 */
	public CompletedExam convert(CompletedExamDTO completedExamDTO) {
		
		CompletedExam c = new CompletedExam();
		c.setId(completedExamDTO.getId());
		c.setTotalCorrect(completedExamDTO.getTotalCorrect());
		c.setTotalIncorrect(completedExamDTO.getTotalIncorrect());
		c.setTotalUnanswered(completedExamDTO.getTotalUnanswered());
		c.setScore(completedExamDTO.getScore());
		c.setStatus(completedExamDTO.getStatus());
		c.setIsCompleted(completedExamDTO.getIsCompleted());
		c.setPassingLimit(completedExamDTO.getPassingLimit());
		return c;
	}

	/**
	 * 
	 * @param testDTO
	 * @param test
	 */
	public void convert(TestDTO testDTO, Test test) {
		/*
		if(testDTO != null)
			test.setId(testDTO.getId());*/
		test.setName(testDTO.getName());
		test.setDuration(testDTO.getDuration());
		test.setStatus(testDTO.getStatus().getStatus());
		test.setPassingLimit(testDTO.getPassingLimit());
		test.setTotalExamQuestions(testDTO.getTotalExamQuestions());
	}

	/**
	 * 
	 * @param answerDTO
	 * @param answer
	 */
	public void convert(AnswerDTO answerDTO, Answer answer) {
		/**
		if(answerDTO.getId() != null)
			answer.setId(answerDTO.getId());*/
		answer.setIsCorrect(answerDTO.getIsCorrect());
		answer.setText(answerDTO.getText());
	}

	/**
	 * 
	 * @param questionDTO
	 * @param question
	 */
	public void convert(QuestionDTO questionDTO, Question question) {
		question.setText(questionDTO.getText());
		question.setType(questionDTO.getType());
	}
}
