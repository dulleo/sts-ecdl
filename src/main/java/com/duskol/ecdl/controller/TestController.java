package com.duskol.ecdl.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.error.ErrorResponse;
import com.duskol.ecdl.exception.DataIntegrityException;
import com.duskol.ecdl.exception.InternalException;
import com.duskol.ecdl.exception.NotFoundException;
import com.duskol.ecdl.exception.ResourceNotFoundException;
import com.duskol.ecdl.service.TestService;

@RestController
@RequestMapping(value="")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(value="/ecdl/tests", consumes = "application/json", produces = "application/json", method= RequestMethod.POST)
	public void createTest(@RequestBody @Valid @NotNull TestDTO testDTO) {
		testService.createTest(testDTO);
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(value="/ecdl/tests", consumes = "application/json", produces = "application/json", method=RequestMethod.PUT)
	public void editTest(@RequestBody @Valid @NotNull TestDTO testDTO) throws ResourceNotFoundException {
		testService.editTest(testDTO);
	}
	
    @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/ecdl/tests/{id}", method=RequestMethod.GET)
    public TestDTO getTest(@PathVariable Long id) throws ResourceNotFoundException {
        TestDTO testDTO = testService.getTest(id);
        return testDTO;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/ecdl/tests", method=RequestMethod.GET)
	public List<TestDTO> getTests() throws ResourceNotFoundException {
		List<TestDTO> testDTOs = testService.getTests();
		return testDTOs;
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(value="/ecdl/tests/{id}", method=RequestMethod.DELETE)
	public void deleteTest(@PathVariable Long id) throws ResourceNotFoundException {
		testService.deleteTest(id);
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse getErrorResponse(NotFoundException e) {
		ErrorCodes errorCode = e.getErrorCode();
		return new ErrorResponse(errorCode.getCode(), e.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse dataIntegrityError(DataIntegrityException e) {
		ErrorCodes errorCode = e.getErrorCode();
		return new ErrorResponse(errorCode.getCode(), e.getMessage());
	}
	
	@ExceptionHandler(InternalException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse internalError(InternalException e) {
		ErrorCodes errorCode = e.getErrorCode();
		return new ErrorResponse(errorCode.getCode(), e.getMessage());
	}
}
