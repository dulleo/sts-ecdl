package com.duskol.ecdl.service;

import com.duskol.ecdl.dto.ExamDTO;
import com.duskol.ecdl.exception.ResourceNotFoundException;

public interface ExamService {
	
	ExamDTO getExamDTO(Long id) throws ResourceNotFoundException;

	ExamDTO submitExam(ExamDTO examDTO) throws ResourceNotFoundException;

}
