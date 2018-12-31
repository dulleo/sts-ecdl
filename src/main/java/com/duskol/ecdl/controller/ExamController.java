package com.duskol.ecdl.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdl.dto.ExamDTO;
import com.duskol.ecdl.exception.ResourceNotFoundException;
import com.duskol.ecdl.service.ExamService;

@RestController
@RequestMapping(value="/ecdl/exams/tests")
public class ExamController {
	
	private final static Logger logger = LoggerFactory.getLogger(ExamController.class);
	
	@Autowired
	ExamService examTestService;
	
	@GetMapping("/{id}")
	@ResponseStatus(value=HttpStatus.OK)
	public ExamDTO getExamDTO(@PathVariable Long id) throws ResourceNotFoundException {
		logger.info("Get exam called....");
		ExamDTO examDTO = examTestService.getExamDTO(id);
		logger.info(("ExamDTO: " + examDTO.toString()));
		return examDTO;
	}
	
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public ExamDTO submitExamTest(@RequestBody @Valid ExamDTO examDTO) throws ResourceNotFoundException {
		logger.info("Submit exam....");
		logger.info("Exam: " + examDTO.toString());
		ExamDTO submitedExam = examTestService.submitExam(examDTO);
		logger.info("Submited exam: " + submitedExam.toString());
		
		return submitedExam;
	}
}
