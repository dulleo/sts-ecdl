package com.duskol.ecdl.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Component;

import com.duskol.ecdl.dto.ExamDTO;
import com.duskol.ecdl.dto.ExamQuestionDTO;
import com.duskol.ecdl.dto.ExamQuestionStatus;
import com.duskol.ecdl.model.CompletedExamStatus;
import com.duskol.ecdl.model.QuestionType;

@Component
public class ExamProcessorImpl implements ExamProcessor {

	@Override
	public void process(ExamDTO examDTO) {
		processExamQuestionDTOs(examDTO.getExamTestDTO().getExamQuestionsDTOs());
		processCompletedExamDTO(examDTO);
	}

	/**
	 * 
	 * @param examQuestionsDTOs
	 */
	private void processExamQuestionDTOs(List<ExamQuestionDTO> examQuestionsDTOs) {
		
		examQuestionsDTOs.stream().forEach(question -> {
			
			long totalChecked = question.getExamAnswerDTOs().stream().filter(a -> a.getIsChecked()).count();
			
			if (totalChecked == 1 && question.getType() == QuestionType.SINGLE) {
				long totalCorrectAnswered = question.getExamAnswerDTOs().stream().filter(answer -> answer.getIsChecked() && answer.getIsCorrect()).count();
				if(totalChecked == totalCorrectAnswered)
					question.setStatus(ExamQuestionStatus.CORRECT);
			}

			if (totalChecked > 1 && question.getType() == QuestionType.MULTIPLE) {
				long totalCorrect = question.getExamAnswerDTOs().stream().filter(a -> a.getIsCorrect()).count();
				long totalCorrectAnswered = question.getExamAnswerDTOs().stream().filter(answer -> answer.getIsChecked() && answer.getIsCorrect()).count();
				
				if(totalChecked == totalCorrect && totalCorrect == totalCorrectAnswered)
					question.setStatus(ExamQuestionStatus.CORRECT);
			}

			if (totalChecked != 0 && question.getStatus() == ExamQuestionStatus.UNANSWERED) {
				question.setStatus(ExamQuestionStatus.INCORRECT);
			}
		});
	}

	/**
	 * 
	 * @param examDTO
	 */
	private void processCompletedExamDTO(ExamDTO examDTO) {
		
		long totalUnanswered = examDTO.getExamTestDTO().getExamQuestionsDTOs()
				.stream()
				.filter(q-> q.getStatus() == ExamQuestionStatus.UNANSWERED)
				.count();
		long totalCorrect = examDTO.getExamTestDTO().getExamQuestionsDTOs()
				.stream()
				.filter(q-> q.getStatus() == ExamQuestionStatus.CORRECT)
				.count();
		long totalIncorrect = examDTO.getExamTestDTO().getExamQuestionsDTOs()
				.stream()
				.filter(q-> q.getStatus() == ExamQuestionStatus.INCORRECT)
				.count();
		
		examDTO.getCompletedExamDTO().setTotalCorrect((int) totalCorrect);
		examDTO.getCompletedExamDTO().setTotalIncorrect((int) totalIncorrect);
		examDTO.getCompletedExamDTO().setTotalUnanswered((int) totalUnanswered);
		
		Double result = (((double)totalCorrect / (totalCorrect + totalIncorrect + totalUnanswered)) * 100);
		
		BigDecimal bd = new BigDecimal(result.toString());
		BigDecimal score = bd.setScale(2, RoundingMode.CEILING);
		
		examDTO.getCompletedExamDTO().setScore(score);
		
		if(score.doubleValue() >= examDTO.getCompletedExamDTO().getPassingLimit().doubleValue())
			examDTO.getCompletedExamDTO().setStatus(CompletedExamStatus.PASSED);
		
		examDTO.getCompletedExamDTO().setIsCompleted(true);
	}
}
