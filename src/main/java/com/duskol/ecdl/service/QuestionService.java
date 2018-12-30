package com.duskol.ecdl.service;

import java.util.List;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.QuestionDTO;
import com.duskol.ecdl.model.Question;

public interface QuestionService {
	
	void createQuestion(Long id, QuestionDTO questionDto) throws ResourceNotFoundException;

	QuestionDTO getQuestion(Long id) throws ResourceNotFoundException;
	
	void deleteQuestions(List<Question> questions);

	List<QuestionDTO> getQuestions(Long testId) throws ResourceNotFoundException;

	void deleteQuestion(Long questionId) throws ResourceNotFoundException;

	void editQuestion(Long questionId, QuestionDTO questionDTO) throws ResourceNotFoundException;
}
