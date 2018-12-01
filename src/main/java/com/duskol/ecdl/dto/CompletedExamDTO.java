package com.duskol.ecdl.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.duskol.ecdl.model.CompletedExamStatus;

public class CompletedExamDTO implements Serializable {
	
	private static final long serialVersionUID = -6091032085793560185L;
	
	private Long id;
	private Integer totalCorrect;
	private Integer totalIncorrect;
	private Integer totalUnanswered;
	private BigDecimal passingLimit;
    private BigDecimal score;
    private CompletedExamStatus status;
    private Boolean isCompleted;
    
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
	@Override
	public String toString() {
		return "CompletedExamDTO [id=" + id + ", totalCorrect=" + totalCorrect + ", totalIncorrect=" + totalIncorrect
				+ ", totalUnanswered=" + totalUnanswered + ", passingLimit=" + passingLimit + ", score=" + score
				+ ", status=" + status + ", isCompleted=" + isCompleted + "]";
	}
}
