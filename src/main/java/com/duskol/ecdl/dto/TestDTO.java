package com.duskol.ecdl.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.duskol.ecdl.model.TestStatus;

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
	private TestStatus status;
	private Integer totalExamQuestions;
	private Integer totalQuestions;
	
	public Integer getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
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
	public TestStatus getStatus() {
		return status;
	}
	public void setStatus(TestStatus status) {
		this.status  = status;
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
		return "TestDTO [id=" + id + ", name=" + name + ", duration=" + duration + ", passingLimit=" + passingLimit
				+ ", status=" + status + ", totalExamQuestions=" + totalExamQuestions + ", totalQuestions="
				+ totalQuestions + "]";
	}
}
