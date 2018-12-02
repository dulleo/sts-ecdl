package com.duskol.ecdl.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.error.ErrorResponse;
import com.duskol.ecdl.service.TestService;

@RestController
@RequestMapping(value="/ecdl/tests")
public class TestController {
	
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	TestService testService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.CREATED)
	public TestDTO createTest(@RequestBody @Valid TestDTO testDTO) {
		logger.info("Save testDTO: " + testDTO.toString());
		TestDTO createdTestDTO = testService.createTest(testDTO);
		logger.info("Saved testDTO: " + createdTestDTO.toString());
		return createdTestDTO;
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.CREATED)
	public TestDTO updateTest(@RequestBody @Valid TestDTO testDTO) throws ResourceNotFoundException {
		logger.info("Update testDTO: " + testDTO.toString());
		TestDTO updatedTestDTO = testService.updateTest(testDTO);
		logger.info("Updated testDTO: " + testDTO.toString());
		return updatedTestDTO;
	}
	
	@GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public TestDTO getTest(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Get testDTO id: " + id);
        TestDTO testDTO = testService.getTest(id);
        logger.info("TestDTO: " + testDTO.toString());
        return testDTO;
	}
	
	@GetMapping()
	@ResponseStatus(value=HttpStatus.OK)
	public List<TestDTO> getTests() throws ResourceNotFoundException {
		logger.info("Get tests called....");
		List<TestDTO> testDTOs = testService.getTests();
		logger.info("Tests: " + testDTOs.toString());
		return testDTOs;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteTest(@PathVariable Long id) throws ResourceNotFoundException {
		logger.info("Delete test called...");
		testService.deleteTest(id);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse getErrorResponse(ResourceNotFoundException e) {
		ErrorCodes errorCode = e.getErrorCode();
		return new ErrorResponse(errorCode.getCode(), e.getMessage());
	}
}
