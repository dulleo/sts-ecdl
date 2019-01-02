package com.duskol.ecdl.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdl.dto.QuestionDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.error.ErrorResponse;
import com.duskol.ecdl.exception.DataIntegrityException;
import com.duskol.ecdl.exception.InternalException;
import com.duskol.ecdl.exception.NotFoundException;
import com.duskol.ecdl.exception.ResourceNotFoundException;
import com.duskol.ecdl.service.QuestionService;

@RestController
@RequestMapping(value="")
public class QuestionController {
	
private final static Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	QuestionService questionService;

	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(path="/ecdl/tests/{id}/questions", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void createQuestion(@PathVariable Long id,  @RequestBody @Valid @NotNull QuestionDTO questionDto) throws ResourceNotFoundException {
		questionService.createQuestion(id, questionDto);
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/ecdl/questions/{id}", method=RequestMethod.GET)
	public QuestionDTO getQuestion(@PathVariable Long id) throws ResourceNotFoundException {
		QuestionDTO result = questionService.getQuestion(id);
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(path="/ecdl/tests/{testId}/questions", method=RequestMethod.GET)
	public List<QuestionDTO> getQuestions(@PathVariable Long testId) throws ResourceNotFoundException {
		List<QuestionDTO> questionDTOs = questionService.getQuestions(testId);
		return questionDTOs;
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(path="/ecdl/questions/{questionId}", method=RequestMethod.GET)
	public void deleteQuestion(@PathVariable Long questionId) throws ResourceNotFoundException {
		questionService.deleteQuestion(questionId);
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(path="/ecdl/questions/{questionId}", method=RequestMethod.PUT,consumes = "application/json", produces = "application/json")
	public void editQuestion(@PathVariable Long questionId, @RequestBody @NotNull @Valid QuestionDTO questionDTO) throws ResourceNotFoundException {
		logger.info("Edit question id: " + questionId);
		questionService.editQuestion(questionId, questionDTO);
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
