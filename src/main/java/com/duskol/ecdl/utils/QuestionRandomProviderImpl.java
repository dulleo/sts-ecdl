package com.duskol.ecdl.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.duskol.ecdl.model.Question;

/**
 * 
 * Created by duskol on Dec 1, 2018
 *
 */
@Component
public class QuestionRandomProviderImpl implements QuestionRandomProvider {

	/**
	 * Returns random number of questions
	 */
	@Override
	public List<Question> getRandom(List<Question> questions, Integer totalExamQuestions) {
		List<Question> randomQuestions = new ArrayList<>();
		Random rand = new Random();
		
		for (int i = 0; i < totalExamQuestions; i++) {
	        int randomIndex = rand.nextInt(questions.size());
	        Question randomQuestion = questions.get(randomIndex);
	        questions.remove(randomIndex);
	        randomQuestions.add(randomQuestion);
	    }
		
		return randomQuestions;
	}
}
