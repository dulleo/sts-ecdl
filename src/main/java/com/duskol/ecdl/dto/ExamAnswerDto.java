package com.duskol.ecdl.dto;

import java.io.Serializable;

/**
 * 
 * Created by duskol on Nov 11, 2018
 *
 */
public class ExamAnswerDto extends AnswerDto implements Serializable {
	
	private static final long serialVersionUID = -8828556285247975884L;
	
	private Boolean isChecked = false;
	

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "ExamAnswerDto [isChecked=" + isChecked + "]";
	}
}
