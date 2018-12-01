package com.duskol.ecdl.service;

import java.util.List;

import javax.validation.Valid;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.AnswerDTO;

public interface AnswerService {

	void createAnswers(Long id, @Valid List<AnswerDTO> answers) throws ResourceNotFoundException;

}
