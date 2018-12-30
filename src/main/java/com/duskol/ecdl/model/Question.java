package com.duskol.ecdl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * Created by duskol on Nov 2, 2018
 *
 */
@Entity
@Table(name="questions")
//Problem with lazy loading via the hibernate proxy object. 
//Got around it by annotating the class having lazy loaded private properties with:
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", updatable=false, nullable=false)
	private Long id;
	
	@NotBlank
	@Column(name="text")
	private String text;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private QuestionType type;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id", nullable = false)
	@JsonIgnore 
	private Test test;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public QuestionType getType() {
		return this.type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}
	
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Question() {}
}
