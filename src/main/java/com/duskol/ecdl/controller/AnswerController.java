package com.duskol.ecdl.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdl.dto.AnswerDTO;
import com.duskol.ecdl.exception.ResourceNotFoundException;
import com.duskol.ecdl.service.AnswerService;

@RestController
@RequestMapping(value="/ecdl")
public class AnswerController {
	
	private final static Logger logger = LoggerFactory.getLogger(AnswerController.class);
	
	@Autowired
	AnswerService answerService;
	
	@PostMapping(path="/questions/{id}/answers", consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.CREATED)
	public void createAnswers(@PathVariable Long id, @RequestBody @Valid List<AnswerDTO> answers) throws ResourceNotFoundException {
		logger.info("Create answers:" + answers.toString());
		//answerService.createAnswers(id, answers);
	}

}
