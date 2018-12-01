package com.duskol.ecdl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duskol.ecdl.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

	List<Question> findByTestId(Long testId);

}
