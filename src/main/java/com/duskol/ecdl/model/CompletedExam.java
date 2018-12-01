package com.duskol.ecdl.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Created by duskol on Nov 30, 2018
 *
 */
@Entity
@Table(name="exams")
public class CompletedExam {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="correct")
	@NotNull
	private Integer totalCorrect;
	
	@Column(name="incorrect")
	@NotNull
	private Integer totalIncorrect;
	
	@Column(name="unanswered")
	@NotNull
	private Integer totalUnanswered;
	
	@Column(name="passing_limit")
	@NotNull
	private BigDecimal passingLimit;
	
	@Column(name="score")
	@NotNull
    private BigDecimal score;
	
	@Column(name="status")
	@NotNull
    private CompletedExamStatus status;
	
	@Column(name="completed")
	@NotNull
    private Boolean isCompleted;
	
	@Column(name="start_date")
	@NotNull
	private Date startDate;
	
	@Column(name="end_date")
	@NotNull
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "test_id", nullable = false)
	@JsonIgnore 
	private Test test;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(Integer totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public Integer getTotalIncorrect() {
		return totalIncorrect;
	}

	public void setTotalIncorrect(Integer totalIncorrect) {
		this.totalIncorrect = totalIncorrect;
	}

	public Integer getTotalUnanswered() {
		return totalUnanswered;
	}

	public void setTotalUnanswered(Integer totalUnanswered) {
		this.totalUnanswered = totalUnanswered;
	}

	public BigDecimal getPassingLimit() {
		return passingLimit;
	}

	public void setPassingLimit(BigDecimal passingLimit) {
		this.passingLimit = passingLimit;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public CompletedExamStatus getStatus() {
		return status;
	}

	public void setStatus(CompletedExamStatus status) {
		this.status = status;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "CompletedExam [id=" + id + ", totalCorrect=" + totalCorrect + ", totalIncorrect=" + totalIncorrect
				+ ", totalUnanswered=" + totalUnanswered + ", passingLimit=" + passingLimit + ", score=" + score
				+ ", status=" + status + ", isCompleted=" + isCompleted + ", startDate=" + startDate + ", endDate="
				+ endDate + ", test=" + test + "]";
	}
	
}
