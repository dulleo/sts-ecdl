package com.duskol.ecdl.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Created by duskol on Nov 11, 2018
 *
 */
public class ExamTestDTO implements Serializable {
	
	private static final long serialVersionUID = -6943379181686318452L;
	
	private Long id;
	private String name;
	private Integer duration;
	private List<ExamQuestionDTO> examQuestionDTOs;

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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public List<ExamQuestionDTO> getExamQuestionsDTOs() {
		return examQuestionDTOs;
	}
	
	public void setExamQuestionDTOs(List<ExamQuestionDTO> examQuestionDTOs) {
		this.examQuestionDTOs = examQuestionDTOs;
	}

	@Override
	public String toString() {
		return "ExamTestDTO [id=" + id + ", name=" + name + ", duration=" + duration + ", examQuestionDTOs="
				+ examQuestionDTOs + "]";
	}
}
