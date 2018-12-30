package com.duskol.ecdl.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * Created by duskol on Dec 9, 2018
 *
 */
@Component
public class RepositoryContainer {
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	CompletedExamRepository completedExamRepository;

	public TestRepository getTestRepository() {
		return testRepository;
	}

	public QuestionRepository getQuestionRepository() {
		return questionRepository;
	}

	public AnswerRepository getAnswerRepository() {
		return answerRepository;
	}

	public CompletedExamRepository getCompletedExamRepository() {
		return completedExamRepository;
	}
}
