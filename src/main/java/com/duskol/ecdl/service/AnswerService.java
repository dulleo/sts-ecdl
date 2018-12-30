package com.duskol.ecdl.service;

import java.util.List;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.AnswerDTO;
import com.duskol.ecdl.model.Question;

public interface AnswerService {

	void createAnswers(Question savedQuestion, List<AnswerDTO> answers) throws ResourceNotFoundException;
	
	void deleteAnswers(Long questionId);
	
	List<AnswerDTO> getAnswers(Long questionId);

	void editAnswers(Question question, List<AnswerDTO> answers);

}
