package com.duskol.ecdl.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.QuestionDTO;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.service.QuestionService;

@RestController
@RequestMapping(value="/ecdl")
public class QuestionController {
	
private final static Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	QuestionService questionService;
	
	@PostMapping(path="/tests/{id}/questions",consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.CREATED)
	public QuestionDTO createQuestion(@RequestParam Long id,  @RequestBody @Valid QuestionDTO questionDto) throws ResourceNotFoundException {
		logger.info("Save question:" + questionDto.toString());
		QuestionDTO createQuestion = questionService.createQuestion(id, questionDto);
		logger.info("Created question:" + createQuestion.toString());
		return createQuestion;
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value=HttpStatus.OK)
	public Question getQuestion(@PathVariable Long id) {
		
		return questionService.getQuestion(id);
		
	}

}
