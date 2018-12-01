package com.duskol.ecdl.utils;

import java.util.List;

import com.duskol.ecdl.model.Question;

/**
 * 
 * Created by duskol on Dec 1, 2018
 *
 */
public interface QuestionRandomProvider {
	
	List<Question> getRandom(List<Question> questions, Integer examNumberOfQuestion);

}
