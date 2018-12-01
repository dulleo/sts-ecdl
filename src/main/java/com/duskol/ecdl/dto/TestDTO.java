package com.duskol.ecdl.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 * Created by duskol on Nov 4, 2018
 *
 */
public class TestDTO implements Serializable {
	
	private static final long serialVersionUID = -671178117532448740L;
	
	private Long id;
	private String name;
	private Integer duration;
	private BigDecimal passingLimit;
	private Boolean isActive;
	private Integer totalExamQuestions;
	
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
	@Override
	public String toString() {
		return " [id=" + id + ", name=" + name + ", duration=" + duration + ", passingLimit=" + passingLimit
				+ ", isActive=" + isActive + ", totalExamQuestion=" + totalExamQuestions + "]";
	}
}
