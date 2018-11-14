package com.duskol.edcl.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.edcl.dto.QuestionDto;
import com.duskol.edcl.service.QuestionService;

@RestController
@RequestMapping(value="/ecdl/questions")
public class QuestionController {
	
private final static Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	QuestionService questionService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.CREATED)
	public QuestionDto createQuestion(@RequestBody QuestionDto questionDto) {
		logger.info("Save question:" + questionDto.toString());
		return questionService.createQuestion(questionDto);
	}

}
