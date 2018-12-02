package com.duskol.ecdl.utils;

import org.springframework.stereotype.Component;

import com.duskol.ecdl.dto.CompletedExamDTO;
import com.duskol.ecdl.dto.ExamAnswerDTO;
import com.duskol.ecdl.dto.ExamQuestionDTO;
import com.duskol.ecdl.dto.ExamQuestionStatus;
import com.duskol.ecdl.dto.ExamTestDTO;
import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.model.Answer;
import com.duskol.ecdl.model.CompletedExam;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.model.Test;

/**
 * 
 * Created by duskol on Dec 1, 2018
 *
 */
@Component
public class EntityToDTOConverter {

	/**
	 * 
	 * @param createdTest
	 * @return
	 */
	public TestDTO convert(Test createdTest) {
		TestDTO t = new TestDTO();
		t.setId(createdTest.getId());
		t.setDuration(createdTest.getDuration());
		t.setIsActive(createdTest.getIsActive());
		t.setName(createdTest.getName());
		t.setPassingLimit(createdTest.getPassingLimit());
		t.setTotalExamQuestions(createdTest.getTotalExamQuestions());
		return t;
	}

	/**
	 * 
	 * @param question
	 * @param answers
	 * @return
	 */
	public ExamQuestionDTO convert(Question question) {
		ExamQuestionDTO examQuestionDto = new ExamQuestionDTO();
		examQuestionDto.setId(question.getId());
		examQuestionDto.setText(question.getText());
		examQuestionDto.setType(question.getType());
		examQuestionDto.setStatus(ExamQuestionStatus.UNANSWERED);
		return examQuestionDto;
	}

	/**
	 * 
	 * @param answer
	 * @return
	 */
	public ExamAnswerDTO convert(Answer answer) {
		ExamAnswerDTO examAnswerDTO = new ExamAnswerDTO();
		examAnswerDTO.setId(answer.getId());
		examAnswerDTO.setText(answer.getText());
		examAnswerDTO.setIsCorrect(answer.getIsCorrect());
		examAnswerDTO.setIsChecked(false);
		return examAnswerDTO;
	}

	/**
	 * 
	 * @param createdCompletedExam
	 * @return
	 */
	public CompletedExamDTO convert(CompletedExam createdCompletedExam) {
		CompletedExamDTO completedExamDTO = new CompletedExamDTO();
		completedExamDTO.setId(createdCompletedExam.getId());
		completedExamDTO.setTotalCorrect(createdCompletedExam.getTotalCorrect());
		completedExamDTO.setTotalIncorrect(createdCompletedExam.getTotalIncorrect());
		completedExamDTO.setTotalUnanswered(createdCompletedExam.getTotalUnanswered());
		completedExamDTO.setPassingLimit(createdCompletedExam.getPassingLimit());
		completedExamDTO.setScore(createdCompletedExam.getScore());
		completedExamDTO.setStatus(createdCompletedExam.getStatus());
		completedExamDTO.setIsCompleted(createdCompletedExam.getIsCompleted());
		return completedExamDTO;
	}

	/**
	 * 
	 * @param test
	 * @param examQuestionDTOs 
	 * @return
	 */
	public ExamTestDTO convertToExamTestDTO(Test test) {
		ExamTestDTO examTestDTO = new ExamTestDTO();
		examTestDTO.setId(test.getId());
		examTestDTO.setName(test.getName());
		examTestDTO.setDuration(test.getDuration());
		//examTestDTO.setPassingLimit(test.getPassingLimit());
		return examTestDTO;
	}

	/**
	 * 
	 * @param updatedTest
	 * @param updatedTestDTO
	 */
	public void convert(Test test, TestDTO testDTO) {
		testDTO.setId(test.getId());
		testDTO.setDuration(test.getDuration());
		testDTO.setId(test.getId());
		testDTO.setIsActive(test.getIsActive());
		testDTO.setName(test.getName());
		testDTO.setPassingLimit(test.getPassingLimit());
		testDTO.setTotalExamQuestions(test.getTotalExamQuestions());
	}
}
