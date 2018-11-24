package com.duskol.ecdl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdl.dto.ExamAnswerDto;
import com.duskol.ecdl.dto.ExamQuestionDto;
import com.duskol.ecdl.dto.ExamTestDto;
import com.duskol.ecdl.model.Answer;
import com.duskol.ecdl.model.Question;
import com.duskol.ecdl.model.Test;
import com.duskol.ecdl.repository.AnswerRepository;
import com.duskol.ecdl.repository.QuestionRepository;
import com.duskol.ecdl.repository.TestRepository;

@Service
public class ExamTestServiceImpl implements ExamTestService {
	
	private final static Logger logger = LoggerFactory.getLogger(ExamTestServiceImpl.class);
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;

	@Override
	public ExamTestDto getExamTest(Long testId) {
		
		Test test = testRepository.getOne(testId);
		
		ExamTestDto examTestDto = new ExamTestDto();
		examTestDto.setId(test.getId());
		examTestDto.setName(test.getName());
		
		List<Question> questions = questionRepository.findByTestId(testId);
		
		//get random questions
		List<Question> randomQuestions = getRandomQuestions(questions);
		
		for (Question question : randomQuestions) {
			ExamQuestionDto examQuestionDto = new ExamQuestionDto();
			examQuestionDto.setId(question.getId());
			examQuestionDto.setText(question.getName());
			examQuestionDto.setType(question.getType());
			
			List<Answer> answers = answerRepository.findByQuestionId(question.getId());
			
			for (Answer answer : answers) {
				ExamAnswerDto examAnswerDto = new ExamAnswerDto();
				examAnswerDto.setId(answer.getId());
				examAnswerDto.setText(answer.getText());
				examAnswerDto.setIsCorrect(answer.getIsCorrect());
				examQuestionDto.getAnswers().add(examAnswerDto);
			}
			
			examTestDto.getExamQuestions().add(examQuestionDto);
		}
		
		return examTestDto;
	}

	@Override
	public ExamTestDto submitExamTest(ExamTestDto examTest) {
		
		logger.info("Submit test....");
		
		return null;
	}
	
	/**
	 * 
	 * @param questions
	 * @return
	 */
	private List<Question> getRandomQuestions(List<Question> questions) {
		
		List<Question> randomQuestions = new ArrayList<>();
		Random rand = new Random();
		int numberOfElements = 6;			//ovo izmesti kasnije
		
		for (int i = 0; i < numberOfElements; i++) {
	        int randomIndex = rand.nextInt(questions.size());
	        Question randomQuestion = questions.get(randomIndex);
	        questions.remove(randomIndex);
	        randomQuestions.add(randomQuestion);
	    }
		
		return randomQuestions;
	}
}
