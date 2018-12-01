package com.duskol.ecdl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duskol.ecdl.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

	List<Answer> findByQuestionId(Long id);

}
