package com.duskol.edcl.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.edcl.controller.exception.ResourceNotFoundException;
import com.duskol.edcl.error.ErrorCodes;
import com.duskol.edcl.model.Test;
import com.duskol.edcl.repository.TestRepository;

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

	@Override
	public Test createTest(Test test) {
		logger.info("Test to save: " + test.toString());
		return testRepository.save(test);
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
