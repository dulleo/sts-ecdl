package com.duskol.ecdl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duskol.ecdl.model.CompletedExam;

/**
 * 
 * Created by duskol on Dec 1, 2018
 *
 */
@Repository
public interface CompletedExamRepository extends JpaRepository<CompletedExam, Long>{
	
	//CompletedExam findByTestId(Long testId);

}
