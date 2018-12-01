package com.duskol.ecdl.service;

import java.util.List;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.model.Test;

public interface TestService {

	TestDTO createTest(TestDTO testDTO);

	Test getTest(Long id) throws ResourceNotFoundException;

	List<Test> getTests();

	void deleteTest(Long id) throws ResourceNotFoundException;

	Test editTest(Test test) throws ResourceNotFoundException;

}
