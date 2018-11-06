package com.duskol.edcl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.edcl.dto.QuestionDto;
import com.duskol.edcl.model.Question;
import com.duskol.edcl.model.Test;
import com.duskol.edcl.repository.QuestionRepository;
import com.duskol.edcl.repository.TestRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	TestRepository testRepository;
	
	@Autowired
	QuestionRepository questionRepository; 
	
	@Override
	public QuestionDto createQuestion(QuestionDto questionDto) {
		
		logger.info("[Service] --> " + questionDto.toString());
		
		Question q = new Question();
		q.setName(questionDto.getText());
		q.setType(questionDto.getType());
		
		Test test = testRepository.getOne(questionDto.getTestId());
		q.setTest(test);
		
		logger.info("test:" + q.getTest().toString());
		
		Question savedQuestion = questionRepository.save(q);
		
		QuestionDto newQ = new QuestionDto();
		newQ.setId(savedQuestion.getId());
		newQ.setText(savedQuestion.getName());
		newQ.setTestId(savedQuestion.getTest().getId());
		
		return newQ;
	}

	
	

}
