package com.duskol.ecdl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.controller.exception.ResourceNotFoundException;
import com.duskol.ecdl.dto.CompletedExamDTO;
import com.duskol.ecdl.dto.ExamAnswerDTO;
import com.duskol.ecdl.dto.ExamDTO;
import com.duskol.ecdl.dto.ExamQuestionDTO;
import com.duskol.ecdl.dto.ExamTestDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.model.Answer;
import com.duskol.ecdl.model.CompletedExam;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.model.Test;
import com.duskol.ecdl.repository.AnswerRepository;
import com.duskol.ecdl.repository.CompletedExamRepository;
import com.duskol.ecdl.repository.QuestionRepository;
import com.duskol.ecdl.repository.TestRepository;
import com.duskol.ecdl.utils.DTOToEntityConverter;
import com.duskol.ecdl.utils.EntityToDTOConverter;
import com.duskol.ecdl.utils.ExamProcessor;
import com.duskol.ecdl.utils.QuestionRandomProvider;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
	
	//private final static Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	CompletedExamRepository completedExamRepository;
	
	@Autowired
	QuestionRandomProvider questionRandomProvider;
	
	@Autowired
	ExamProcessor examProcessor;
	
	@Autowired
	EntityToDTOConverter entityToDTOConverter;
	
	@Autowired
	DTOToEntityConverter dtoToEntityConverter;

	@Override
	public ExamDTO getExamDTO(Long testId) throws ResourceNotFoundException {
		
		Test test = testRepository.getOne(testId);
		if(test == null)
			throw new ResourceNotFoundException("Test id:" + testId + " not found!", ErrorCodes.TEST_NOT_FOUND);
		
		List<Question> questions = questionRepository.findByTestId(testId);
		if(questions == null)
			throw new ResourceNotFoundException("For test id:" + testId + " questions are not found!", ErrorCodes.QUESTIONS_NOT_FOUND);
		
		ExamTestDTO examTestDTO = entityToDTOConverter.convertToExamTestDTO(test);
		examTestDTO.setExamQuestionDTOs(getExamQuestionDTOs(questions, test.getTotalExamQuestions()));
		
		CompletedExam createdCompletedExam = completedExamRepository.save(dtoToEntityConverter.convert(test));
		CompletedExamDTO completedExamDTO = entityToDTOConverter.convert(createdCompletedExam);
		
		ExamDTO examDTO = new ExamDTO();
		examDTO.setExamTestDTO(examTestDTO);
		examDTO.setCompletedExamDTO(completedExamDTO);
		
		return examDTO;
	}
	
	@Override
	public ExamDTO submitExam(ExamDTO examDTO) throws ResourceNotFoundException {
		
		Long testId = examDTO.getExamTestDTO().getId();
		Test test = testRepository.getOne(testId);
		if(test == null)
			throw new ResourceNotFoundException("Test id:" + testId + " not found!", 
					ErrorCodes.TEST_NOT_FOUND);
		
		Long completedExamId = examDTO.getCompletedExamDTO().getId();
		CompletedExam completedExam = completedExamRepository.getOne(completedExamId);
		if(completedExam == null)
			throw new ResourceNotFoundException("Completed Exam id:" + completedExamId + " not found!", 
					ErrorCodes.COMPLETED_EXAM_NOT_FOUND);
		
		examProcessor.process(examDTO);
		
		CompletedExam processedCompletedExam = dtoToEntityConverter.convert(examDTO.getCompletedExamDTO());
		processedCompletedExam.setEndDate(new Date());
		processedCompletedExam.setStartDate(completedExam.getStartDate());
		processedCompletedExam.setTest(test);
		
		CompletedExam updatedCompletedExam = completedExamRepository.save(processedCompletedExam);
		CompletedExamDTO updatedCompletedExamDTO = entityToDTOConverter.convert(updatedCompletedExam);
		
		examDTO.setCompletedExamDTO(updatedCompletedExamDTO);
		
		return examDTO;
	}

	/**
	 * 
	 * @param totalExamQuestions 
	 * @param randomQuestions
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	private List<ExamQuestionDTO> getExamQuestionDTOs(List<Question> questions, Integer totalExamQuestions) throws ResourceNotFoundException {
		
		//get random questions
		List<Question> randomQuestions = questionRandomProvider.getRandom(questions, totalExamQuestions); 
		
		List<ExamQuestionDTO> examQuestionDTOs = new ArrayList<>();
		
		for (Question question : randomQuestions) {
			
			//get all answers for specific question
			List<Answer> answers = answerRepository.findByQuestionId(question.getId());
			
			if(answers == null)
				throw new ResourceNotFoundException("For question id:" + question.getId() + " answers are not found!", ErrorCodes.ANSWERS_NOT_FOUND);
			
			ExamQuestionDTO examQuestionDTO = entityToDTOConverter.convert(question);
			examQuestionDTO.setExamAnswerDTOs(getExamAnswerDTOs(answers));
			examQuestionDTOs.add(examQuestionDTO);
			
		}
		
		return examQuestionDTOs;
	}

	/**
	 * 
	 * @param answers
	 * @return
	 */
	private List<ExamAnswerDTO> getExamAnswerDTOs(List<Answer> answers) {
		
		List<ExamAnswerDTO> examAnswerDTOs = new ArrayList<>();
		
		for(Answer answer : answers) {
			ExamAnswerDTO examAnswerDTO = entityToDTOConverter.convert(answer);
			examAnswerDTOs.add(examAnswerDTO);
		}
		
		return examAnswerDTOs;
	}	
}
