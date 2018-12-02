package com.duskol.ecdl.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Autowired
	TestRepository testRepository;
	
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
		
		Test test = testRepository.getOne(id);
		if(test==null)
			throw new ResourceNotFoundException("Test id:" + id + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		TestDTO testDTO = new TestDTO();
		entityToDTOConverter.convert(test, testDTO);
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
		
		Test test = testRepository.getOne(id);
		if(test == null)
			throw new ResourceNotFoundException("Test id:" + id + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		testRepository.deleteById(id);
	}

	@Override
	public TestDTO updateTest(TestDTO testDTO) throws ResourceNotFoundException {
		
		Test test = testRepository.getOne(testDTO.getId());
		if(test==null)
			throw new ResourceNotFoundException("Test id:" + testDTO.getId() + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		dtoToEntityConverter.convert(testDTO, test);
		
		Test updatedTest = testRepository.save(test);
		TestDTO updatedTestDTO = new TestDTO();
		entityToDTOConverter.convert(updatedTest, updatedTestDTO);
		
		return updatedTestDTO;
	}
}
