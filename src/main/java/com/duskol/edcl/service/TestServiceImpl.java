package com.duskol.edcl.service;

import java.util.List;
import java.util.Optional;

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
	
	@Autowired
	TestRepository testRepository;

	@Override
	public Test createTest(Test test) {
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
}
