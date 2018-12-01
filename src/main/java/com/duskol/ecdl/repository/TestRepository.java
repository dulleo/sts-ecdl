package com.duskol.ecdl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duskol.ecdl.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

}
