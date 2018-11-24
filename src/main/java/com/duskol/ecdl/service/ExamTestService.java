package com.duskol.ecdl.service;

import com.duskol.ecdl.dto.ExamTestDto;

public interface ExamTestService {
	
	ExamTestDto getExamTest(Long id);

	ExamTestDto submitExamTest(ExamTestDto examTest);

}
