package com.duskol.ecdl.service;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.QuestionDTO;
import com.duskol.ecdl.model.Question;

public interface QuestionService {
	
	QuestionDTO createQuestion(Long id, QuestionDTO questionDto) throws ResourceNotFoundException;

	Question getQuestion(Long id);
}
