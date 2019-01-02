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
	
	@Autowired
	QuestionService questionService;

	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(value="/ecdl/tests/{id}/questions", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void createQuestion(@PathVariable Long id,  @RequestBody @Valid @NotNull QuestionDTO questionDTO) throws ResourceNotFoundException {
		questionService.createQuestion(id, questionDTO);
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/ecdl/questions/{id}", method=RequestMethod.GET)
	public QuestionDTO getQuestion(@PathVariable Long id) throws ResourceNotFoundException {
		QuestionDTO result = questionService.getQuestion(id);
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/ecdl/tests/{id}/questions", method=RequestMethod.GET)
	public List<QuestionDTO> getQuestions(@PathVariable Long id) throws ResourceNotFoundException {
		List<QuestionDTO> questionDTOs = questionService.getQuestions(id);
		return questionDTOs;
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(value="/ecdl/questions/{questionId}", method=RequestMethod.DELETE)
	public void deleteQuestion(@PathVariable Long questionId) throws ResourceNotFoundException {
		questionService.deleteQuestion(questionId);
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(value="/ecdl/questions/{questionId}", method=RequestMethod.PUT,consumes = "application/json", produces = "application/json")
	public void editQuestion(@PathVariable Long questionId, @RequestBody @NotNull @Valid QuestionDTO questionDTO) throws ResourceNotFoundException {
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
