package com.duskol.edcl.service;

import java.util.List;

import com.duskol.edcl.controller.exception.ResourceNotFoundException;
import com.duskol.edcl.model.Test;

public interface TestService {

	Test createTest(Test test);

	Test getTest(Long id) throws ResourceNotFoundException;

	List<Test> getTests();

	void deleteTest(Long id) throws ResourceNotFoundException;

}
