package com.duskol.ecdl.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.model.Test;
import com.duskol.ecdl.repository.TestRepository;
import com.duskol.ecdl.utils.DTOToEntityConverter;
import com.duskol.ecdl.utils.EntityToDTOConverter;

/**
 * 
 * Created by duskol on Nov 2, 2018
 *
 */
@Service
public class TestServiceImpl implements TestService {
	
	private final static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	DTOToEntityConverter dtoToEntityConverter;
	
	@Autowired
	EntityToDTOConverter entityToDTOConverter;

	@Override
	public TestDTO createTest(TestDTO testDTO) {
		
		Test test = dtoToEntityConverter.convert(testDTO);
		
		Test createdTest = testRepository.save(test);
		
		TestDTO createdTestDTO = entityToDTOConverter.convert(createdTest);//convertToDTO(savedTest);
		
		return createdTestDTO;
	}

	@Override
	public Test getTest(Long id) throws ResourceNotFoundException {
		return testRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Test id:" + id + " not found!", ErrorCodes.TEST_NOT_FOUND));
	}

	@Override
	public List<Test> getTests() {
		return testRepository.findAll();
	}

	@Override
	public void deleteTest(Long id) throws ResourceNotFoundException {
		
		Optional<Test> optional = testRepository.findById(id);
		
		if(!optional.isPresent())
			throw new ResourceNotFoundException("Test id:" + id + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		testRepository.deleteById(id);
		
	}

	@Override
	public Test editTest(Test test) throws ResourceNotFoundException {

		logger.info("Test to edit: " + test.toString());
		
		Optional<Test> optional = testRepository.findById(test.getId());
		
		if(!optional.isPresent())
			throw new ResourceNotFoundException("Test id:" + test.getId() + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		Test t = new Test();
		t.setId(test.getId());
		t.setName(test.getName());
		return testRepository.save(t);
	}
}
