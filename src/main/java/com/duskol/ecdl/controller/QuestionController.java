package com.duskol.ecdl.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdl.dto.QuestionDTO;
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
		logger.info("Get question id:" + id);
		QuestionDTO result = questionService.getQuestion(id);
		logger.info(result.toString());
		return result;
	}
	
	@GetMapping(path="/ecdl/tests/{testId}/questions")
	@ResponseStatus(value=HttpStatus.OK)
	public List<QuestionDTO> getQuestions(@PathVariable Long testId) throws ResourceNotFoundException {
		logger.info("Get questions for test id:" + testId);
		List<QuestionDTO> questionDTOs = questionService.getQuestions(testId);
		return questionDTOs;
		
	}
	
	@DeleteMapping(path="/ecdl/questions/{questionId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteQuestion(@PathVariable Long questionId) throws ResourceNotFoundException {
		logger.info("Delete question id:" + questionId);
		questionService.deleteQuestion(questionId);
	}
	
	@PutMapping(path="/ecdl/questions/{questionId}",consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void editQuestion(@PathVariable Long questionId, @RequestBody @NotNull @Valid QuestionDTO questionDTO) throws ResourceNotFoundException {
		logger.info("Edit question id: " + questionId);
		questionService.editQuestion(questionId, questionDTO);
	}

}
