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

import com.duskol.ecdl.dto.ExamTestDto;
import com.duskol.ecdl.service.ExamTestService;

@RestController
@RequestMapping(value="/ecdl/exams")
public class ExamTestController {
	
	private final static Logger logger = LoggerFactory.getLogger(ExamTestController.class);
	
	@Autowired
	ExamTestService examTestService;
	
	@GetMapping("/{id}")
	@ResponseStatus(value=HttpStatus.OK)
	public ExamTestDto getExamTest(@PathVariable Long id) {
		logger.info("Get exam called....");
		ExamTestDto examTest = examTestService.getExamTest(id);
		logger.info(("Exam test: " + examTest.toString()));
		return examTest;
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(value=HttpStatus.OK)
	public ExamTestDto submitExamTest(@RequestBody @Valid ExamTestDto examTest) {
		logger.info("Submit exam started");
		ExamTestDto submitedTest = examTestService.submitExamTest(examTest);
		logger.info("Submit exam: " + examTest.toString());
		logger.info("Submit exam completed...");
		return submitedTest;
	}
}
