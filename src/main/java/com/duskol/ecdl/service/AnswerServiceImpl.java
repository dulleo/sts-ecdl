package com.duskol.ecdl.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.AnswerDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.model.Answer;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.repository.AnswerRepository;
import com.duskol.ecdl.repository.QuestionRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;

	@Override
	public void createAnswers(Long id, @Valid List<AnswerDTO> answersDto) throws ResourceNotFoundException {
		
		Question question = questionRepository.getOne(id);
		
		if(question == null)
			throw new ResourceNotFoundException("Question id:" + id + " not found!", ErrorCodes.QUESTION_NOT_FOUND);
		
		answersDto.stream().forEach(answerDto->{
			Answer a = new Answer();
			a.setIsCorrect(answerDto.getIsCorrect());
			a.setText(answerDto.getText());
			a.setQuestion(question);
			answerRepository.save(a);
		});
	}

}
