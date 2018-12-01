package com.duskol.ecdl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.QuestionDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.model.Test;
import com.duskol.ecdl.repository.QuestionRepository;
import com.duskol.ecdl.repository.TestRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	TestRepository testRepository;
	
	@Autowired
	QuestionRepository questionRepository; 
	
	@Override
	public QuestionDTO createQuestion(Long testId, QuestionDTO questionDto) throws ResourceNotFoundException {
		
		Test test = testRepository.getOne(testId);
		
		if(test == null)
			throw new ResourceNotFoundException("Test id:" + testId + " not found!", ErrorCodes.TEST_NOT_FOUND);

		
		Question question = new Question();
		question.setName(questionDto.getText());
		question.setType(questionDto.getType());
		question.setTest(test);
		
		Question savedQuestion = questionRepository.save(question);
		
		QuestionDTO newQ = new QuestionDTO();
		newQ.setId(savedQuestion.getId());
		newQ.setText(savedQuestion.getText());
		newQ.setType(savedQuestion.getType());
		
		return newQ;
	}

	@Override
	public Question getQuestion(Long id) {
		
		return questionRepository.getOne(id);
	}
}
