package com.duskol.ecdl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.dto.AnswerDTO;
import com.duskol.ecdl.exception.ResourceNotFoundException;
import com.duskol.ecdl.model.Answer;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.repository.RepositoryContainer;
import com.duskol.ecdl.utils.DTOToEntityConverter;
import com.duskol.ecdl.utils.EntityToDTOConverter;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	RepositoryContainer repositoryContainer;
	
	@Autowired
	DTOToEntityConverter dtoToEntityConverter;
	
	@Autowired
	EntityToDTOConverter entityToDTOConverter;

	@Override
	public void createAnswers(Question savedQuestion, List<AnswerDTO> answersDTO) throws ResourceNotFoundException {
		
		List<Answer> answers = new ArrayList<>();
		
		for (AnswerDTO answerDTO : answersDTO) {
			Answer answer = new Answer();
			dtoToEntityConverter.convert(answerDTO, answer);
			answer.setQuestion(savedQuestion);
			answers.add(answer);
		}
		
		repositoryContainer.getAnswerRepository().saveAll(answers);
	}

	/**
	 * 
	 */
	@Override
	public void deleteAnswers(Long questionId) {
		List<Answer> answers = repositoryContainer.getAnswerRepository().findByQuestionId(questionId);
		repositoryContainer.getAnswerRepository().deleteAll(answers);
	}

	@Override
	public List<AnswerDTO> getAnswers(Long questionId) {
		
		List<Answer> answers = repositoryContainer.getAnswerRepository().findByQuestionId(questionId);
		
		List<AnswerDTO> answerDTOs = new ArrayList<>();
		
		answers.stream().forEach(a -> {
			AnswerDTO dto = new AnswerDTO();
			entityToDTOConverter.convert(a, dto);
			answerDTOs.add(dto);
		});

		return answerDTOs;
	}

	/**
	 * 
	 */
	@Override
	public void editAnswers(Question question, List<AnswerDTO> answerDTOs) {
		
		List<Answer> answers = repositoryContainer.getAnswerRepository().findByQuestionId(question.getId());
		
		answerDTOs.stream().forEach(dto -> {
			Answer entity = answers.stream().filter(answer -> dto.getId().longValue() == answer.getId().longValue())
				.findFirst().get();
			
			dtoToEntityConverter.convert(dto, entity);	
			repositoryContainer.getAnswerRepository().save(entity);
		});
		
	}
}
