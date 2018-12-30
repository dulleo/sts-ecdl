package com.duskol.ecdl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.AnswerDTO;
import com.duskol.ecdl.dto.QuestionDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.model.Test;
import com.duskol.ecdl.repository.RepositoryContainer;
import com.duskol.ecdl.utils.DTOToEntityConverter;
import com.duskol.ecdl.utils.EntityToDTOConverter;

@Service
@Transactional(rollbackOn = {Exception.class})
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	RepositoryContainer repositoryContainer;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	EntityToDTOConverter entityToDTOConverter;
	
	@Autowired
	DTOToEntityConverter dtoToEntityConverter;
	
	@Override
	public void createQuestion(Long testId, QuestionDTO questionDTO) throws ResourceNotFoundException {
		
		Optional<Test> optional = repositoryContainer.getTestRepository().findById(testId);
		
		if(!optional.isPresent())
			throw new ResourceNotFoundException("Test id:" + testId + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		Question question = new Question();
		dtoToEntityConverter.convert(questionDTO, question);
		
		Test test = optional.get();
		question.setTest(test);
		
		Question savedQuestion = repositoryContainer.getQuestionRepository().save(question);
		
		answerService.createAnswers(savedQuestion, questionDTO.getAnswers());
	}
	
	@Override
	public void editQuestion(Long questionId, QuestionDTO questionDTO) throws ResourceNotFoundException {
		
		Optional<Question> optional = repositoryContainer.getQuestionRepository().findById(questionId);
		
		if(!optional.isPresent())
			throw new ResourceNotFoundException("Question id:" + questionId + " not found!", ErrorCodes.QUESTION_NOT_FOUND);
		
		Question question = optional.get();
		
		answerService.editAnswers(question, questionDTO.getAnswers());
		
		dtoToEntityConverter.convert(questionDTO, question);
		
		repositoryContainer.getQuestionRepository().save(question);
	}

	@Override
	public QuestionDTO getQuestion(Long questionId) throws ResourceNotFoundException {
		
		Optional<Question> optional = repositoryContainer.getQuestionRepository().findById(questionId);
		
		if(!optional.isPresent())
			throw new ResourceNotFoundException("Question id:" + questionId + " not found!", ErrorCodes.QUESTION_NOT_FOUND);
		
		QuestionDTO questionDTO = new QuestionDTO();
		entityToDTOConverter.convert(optional.get(),questionDTO);
		
		List<AnswerDTO> answerDTOs = answerService.getAnswers(questionId);
		questionDTO.setAnswers(answerDTOs);

		return questionDTO;
	}

	/**
	 * 
	 */
	@Override
	public void deleteQuestions(List<Question> questions) {
		
		for (Question question : questions) {
			answerService.deleteAnswers(question.getId());
		}
		repositoryContainer.getQuestionRepository().deleteAll(questions);
		
	}

	@Override
	public List<QuestionDTO> getQuestions(Long testId) throws ResourceNotFoundException {
		
		Optional<Test> optional = repositoryContainer.getTestRepository().findById(testId);
		
		if(!optional.isPresent())
			throw new ResourceNotFoundException("Test id:" + testId + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		List<Question> questions = repositoryContainer.getQuestionRepository().findByTestId(testId);
		
		List<QuestionDTO> questionDTOs = new ArrayList<>();
		
		if(questions.isEmpty())
			return questionDTOs;
		
		questions.stream().forEach(question-> { 
			QuestionDTO dto = new QuestionDTO();
			entityToDTOConverter.convert(question,dto);
			questionDTOs.add(dto);
		});
		
		return questionDTOs;
		
	}

	@Override
	public void deleteQuestion(Long questionId) throws ResourceNotFoundException {
	
		Optional<Question> optional = repositoryContainer.getQuestionRepository().findById(questionId);
		
		if(!optional.isPresent())
			throw new ResourceNotFoundException("Question id:" + questionId + " not found!", ErrorCodes.QUESTION_NOT_FOUND);
		
		answerService.deleteAnswers(questionId);
		
		repositoryContainer.getQuestionRepository().deleteById(questionId);
		
	}
}
