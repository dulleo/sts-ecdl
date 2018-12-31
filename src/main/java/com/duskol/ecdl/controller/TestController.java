package com.duskol.ecdl.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.error.ErrorResponse;
import com.duskol.ecdl.exception.ResourceNotFoundException;
import com.duskol.ecdl.service.TestService;

@RestController
@RequestMapping(value="/ecdl/tests")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.CREATED)
	public void createTest(@RequestBody @Valid @NotNull TestDTO testDTO) {
		//logger.info("Save {}", testDTO.toString());
		testService.createTest(testDTO);
		//logger.info("Saved " + createdTestDTO.toString());
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public TestDTO editTest(@RequestBody @Valid @NotNull TestDTO testDTO) throws ResourceNotFoundException {
		//logger.info("Update testDTO: " + testDTO.toString());
		TestDTO updatedTestDTO = testService.editTest(testDTO);
		//logger.info("Updated testDTO: " + testDTO.toString());
		return updatedTestDTO;
	}
	
	@GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public TestDTO getTest(@PathVariable Long id) throws ResourceNotFoundException {
        //logger.info("Get test with id: " + id);
        TestDTO testDTO = testService.getTest(id);
        //logger.info(testDTO.toString());
        return testDTO;
	}
	
	@GetMapping()
	@ResponseStatus(value=HttpStatus.OK)
	public List<TestDTO> getTests() throws ResourceNotFoundException {
		//logger.info("Get tests called....");
		List<TestDTO> testDTOs = testService.getTests();
		//logger.info("Tests: " + testDTOs.toString());
		return testDTOs;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteTest(@PathVariable Long id) throws ResourceNotFoundException {
		//logger.info("Delete test id: " + id);
		testService.deleteTest(id);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse getErrorResponse(ResourceNotFoundException e) {
		ErrorCodes errorCode = e.getErrorCode();
		return new ErrorResponse(errorCode.getCode(), e.getMessage());
	}
}
