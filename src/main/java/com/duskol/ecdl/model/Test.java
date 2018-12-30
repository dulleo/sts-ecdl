package com.duskol.ecdl.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * 
 * Created by duskol on Nov 2, 2018
 *
 */
@Entity
@Table(name="tests")
public class Test {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", updatable=false, nullable=false)
	private Long id;
	
	@NotBlank
	@Column(name="name", unique=true)
	private String name;
	
	@Column(name="duration")
	private Integer duration;
	
	@Column(name="passing_limit")
	private BigDecimal passingLimit;
	
	@Column(name="status")
	private String status;
	
	@Column(name="total_exam_questions")
	private Integer totalExamQuestions;
	
	@OneToMany(mappedBy="test")
	private List<Question> questions = new ArrayList<>();

	public List<Question> getQuestions() {
		return questions;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public BigDecimal getPassingLimit() {
		return passingLimit;
	}

	public void setPassingLimit(BigDecimal passingLimit) {
		this.passingLimit = passingLimit;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalExamQuestions() {
		return totalExamQuestions;
	}

	public void setTotalExamQuestions(Integer totalExamQuestions) {
		this.totalExamQuestions = totalExamQuestions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Test() {
		
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", duration=" + duration + ", passingLimit=" + passingLimit
				+ ", status=" + status + ", totalExamQuestions=" + totalExamQuestions + ", questions=" + questions
				+ "]";
	}

	
}
