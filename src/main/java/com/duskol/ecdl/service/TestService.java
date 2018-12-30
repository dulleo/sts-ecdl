package com.duskol.ecdl.service;

import java.util.List;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.TestDTO;

public interface TestService {

	TestDTO createTest(TestDTO testDTO);

	TestDTO getTest(Long id) throws ResourceNotFoundException;

	List<TestDTO> getTests() throws ResourceNotFoundException;

	void deleteTest(Long id) throws ResourceNotFoundException;

	TestDTO editTest(TestDTO testDTO) throws ResourceNotFoundException;

}
