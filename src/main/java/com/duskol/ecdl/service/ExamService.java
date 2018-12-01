package com.duskol.ecdl.service;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.ExamDTO;

public interface ExamService {
	
	ExamDTO getExamDTO(Long id) throws ResourceNotFoundException;

	ExamDTO submitExam(ExamDTO examDTO) throws ResourceNotFoundException;

}
