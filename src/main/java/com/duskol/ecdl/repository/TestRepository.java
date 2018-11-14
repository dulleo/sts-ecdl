package com.duskol.edcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duskol.edcl.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

}
