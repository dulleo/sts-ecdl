package com.duskol.edcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duskol.edcl.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}