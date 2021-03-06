package com.duskol.ecdl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.exception.ResourceNotFoundException;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.model.Test;
import com.duskol.ecdl.repository.RepositoryContainer;
import com.duskol.ecdl.utils.DTOToEntityConverter;
import com.duskol.ecdl.utils.EntityToDTOConverter;

/**
 * 
 * Created by duskol on Nov 2, 2018
 *
 */
@Service
@Transactional(rollbackOn= {Exception.class})
public class TestServiceImpl implements TestService {
	
	@Autowired
	RepositoryContainer repositoryContainer;
	
	@Autowired
	DTOToEntityConverter dtoToEntityConverter;
	
	@Autowired
	EntityToDTOConverter entityToDTOConverter;
	
	@Autowired
	QuestionService questionService;

	@Override
	public void createTest(TestDTO testDTO) {
		
		Test test = new Test();
		dtoToEntityConverter.convert(testDTO, test);
		repositoryContainer.getTestRepository().save(test);
	}

	@Override
	public TestDTO getTest(Long id) throws ResourceNotFoundException {
		
		Optional<Test> testOpt = repositoryContainer.getTestRepository().findById(id);
		if (!testOpt.isPresent())
			throw new ResourceNotFoundException(getErrorMessage(id), ErrorCodes.TEST_NOT_FOUND);
		
		TestDTO testDTO = new TestDTO();
		entityToDTOConverter.convert(testOpt.get(), testDTO);
		return testDTO;
	}

	@Override
	public List<TestDTO> getTests() throws ResourceNotFoundException {
		
		List<Test> tests = repositoryContainer.getTestRepository().findAll();
		if(tests.isEmpty()) 
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
		
		Optional<Test> testOpt = repositoryContainer.getTestRepository().findById(id);

		if (!testOpt.isPresent())
			throw new ResourceNotFoundException(getErrorMessage(id), ErrorCodes.TEST_NOT_FOUND);
		
		List<Question> questions = repositoryContainer.getQuestionRepository().findByTestId(id);
		
		if(questions != null && questions.isEmpty())
			questionService.deleteQuestions(questions);
		
		repositoryContainer.getTestRepository().deleteById(id);
	}

	@Override
	public void editTest(TestDTO testDTO) throws ResourceNotFoundException {
		
		Optional<Test> testOpt = repositoryContainer.getTestRepository().findById(testDTO.getId());
		if (!testOpt.isPresent())
			throw new ResourceNotFoundException(getErrorMessage(testDTO.getId()), ErrorCodes.TEST_NOT_FOUND);
		
		Test test = testOpt.get();
		dtoToEntityConverter.convert(testDTO, test);
		repositoryContainer.getTestRepository().save(test);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private String getErrorMessage(Long id) {
		return "Test id: " + id + " not found!";
	}
}
