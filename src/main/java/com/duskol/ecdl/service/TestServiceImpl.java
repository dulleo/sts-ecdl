package com.duskol.ecdl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.model.Answer;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.model.Test;
import com.duskol.ecdl.repository.AnswerRepository;
import com.duskol.ecdl.repository.QuestionRepository;
import com.duskol.ecdl.repository.TestRepository;
import com.duskol.ecdl.utils.DTOToEntityConverter;
import com.duskol.ecdl.utils.EntityToDTOConverter;

/**
 * 
 * Created by duskol on Nov 2, 2018
 *
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	DTOToEntityConverter dtoToEntityConverter;
	
	@Autowired
	EntityToDTOConverter entityToDTOConverter;

	@Override
	public TestDTO createTest(TestDTO testDTO) {
		
		Test test = new Test();
		dtoToEntityConverter.convert(testDTO, test);
		
		Test createdTest = testRepository.save(test);
		TestDTO createdTestDTO = new TestDTO();
		entityToDTOConverter.convert(createdTest, createdTestDTO);
		
		return createdTestDTO;
	}

	@Override
	public TestDTO getTest(Long id) throws ResourceNotFoundException {
		
		Optional<Test> testOpt = testRepository.findById(id);
		if (!testOpt.isPresent())
			throw new ResourceNotFoundException("Test id:" + id + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		TestDTO testDTO = new TestDTO();
		entityToDTOConverter.convert(testOpt.get(), testDTO);
		return testDTO;
	}

	@Override
	public List<TestDTO> getTests() throws ResourceNotFoundException {
		
		List<Test> tests = testRepository.findAll();
		if(tests == null) //proveri da li vraca null ili prazan niz
			throw new ResourceNotFoundException("Database does not contains tests!", ErrorCodes.TESTS_NOT_FOUND);
		
		List<TestDTO> dtos = new ArrayList<>();
		
		for(Test test: tests) {
			TestDTO testDTO = new TestDTO();
			entityToDTOConverter.convert(test, testDTO);
			dtos.add(testDTO);
		}
		
		return dtos;
	}

	@Override
	public void deleteTest(Long id) throws ResourceNotFoundException {
		
		Optional<Test> testOpt = testRepository.findById(id);

		if (!testOpt.isPresent())
			throw new ResourceNotFoundException("Test id:" + id + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		List<Question> questions = questionRepository.findByTestId(id);
		deleteQuestions(questions);
		
		testRepository.deleteById(id);
	}

	
	@Override
	public TestDTO updateTest(TestDTO testDTO) throws ResourceNotFoundException {
		
		Optional<Test> testOpt = testRepository.findById(testDTO.getId());
		if (!testOpt.isPresent())
			throw new ResourceNotFoundException("Test id:" + testDTO.getId() + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		Test test = testOpt.get();
		dtoToEntityConverter.convert(testDTO, test);
		Test updatedTest = testRepository.save(test);
		
		TestDTO updatedTestDTO = new TestDTO();
		entityToDTOConverter.convert(updatedTest, updatedTestDTO);
		
		return updatedTestDTO;
	}
	
	/**
	 * 
	 * @param questions
	 */
	private void deleteQuestions(List<Question> questions) {
		if(questions != null && questions.size() != 0) {
			for (Question question : questions) {
				deleteAnswers(question);
				questionRepository.deleteById(question.getId());
			}
		}
	}
	
	/**
	 * 
	 * @param question
	 */
	private void deleteAnswers(Question question) {
		List<Answer> answers = answerRepository.findByQuestionId(question.getId());
		if(answers != null && answers.size() != 0) {
			for (Answer answer : answers) {
				answerRepository.deleteById(answer.getId());
			}
		}
	}
}
