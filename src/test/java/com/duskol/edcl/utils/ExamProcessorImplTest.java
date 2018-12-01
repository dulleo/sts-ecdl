package com.duskol.edcl.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.duskol.ecdl.dto.CompletedExamDTO;
import com.duskol.ecdl.dto.ExamAnswerDTO;
import com.duskol.ecdl.dto.ExamDTO;
import com.duskol.ecdl.dto.ExamQuestionDTO;
import com.duskol.ecdl.dto.ExamQuestionStatus;
import com.duskol.ecdl.dto.ExamTestDTO;
import com.duskol.ecdl.model.CompletedExamStatus;
import com.duskol.ecdl.model.QuestionType;
import com.duskol.ecdl.utils.ExamProcessor;
import com.duskol.ecdl.utils.ExamProcessorImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ExamProcessorImpl.class)
public class ExamProcessorImplTest {
	
	private final static Logger logger = LoggerFactory.getLogger(ExamProcessorImplTest.class);

	private static ExamDTO examDTO;

	@Autowired
	ExamProcessor examProcessor;

	@BeforeClass
	public static void setUp() {
		examDTO = getData();
		examDTO.getExamTestDTO().getExamQuestionsDTOs().stream().forEach(q -> {
			logger.info("Id: " + q.getId());
			logger.info("Status: " + q.getStatus());
		});
	}

	@Test
	public void totalCorrectTest() {
		examProcessor.process(examDTO);
		assertEquals(6l, (long) examDTO.getCompletedExamDTO().getTotalCorrect());
	}
	
	@Test
	public void totalInCorrectTest() {
		examProcessor.process(examDTO);
		assertEquals(2L, (long)examDTO.getCompletedExamDTO().getTotalIncorrect());
	}
	
	@Test
	public void totalUnansweredTest() {
		examProcessor.process(examDTO);
		assertEquals(2L, (long)examDTO.getCompletedExamDTO().getTotalUnanswered());
	}
	
	@Test
	public void scoreTest() {
		examProcessor.process(examDTO);
		BigDecimal expected = new BigDecimal((6.0/10)*100).setScale(2, RoundingMode.CEILING);
		BigDecimal actual = examDTO.getCompletedExamDTO().getScore();
		assertEquals(expected, actual);
	}
	
	@Test
	public void statusPassedTest() {
		examProcessor.process(examDTO);
		CompletedExamStatus expected = CompletedExamStatus.PASSED;
		CompletedExamStatus actual = examDTO.getCompletedExamDTO().getStatus();
		assertEquals(expected, actual);
	}
	
	@Test
	public void isCompletedTest() {
		examProcessor.process(examDTO);
		Boolean expected = true;
		Boolean actual = examDTO.getCompletedExamDTO().getIsCompleted();
		assertEquals(expected, actual);
	}

	/**
	 * 
	 * @return
	 */
	private static ExamDTO getData() {

		ExamDTO data = new ExamDTO();
		data.setExamTestDTO(getExamTestDTO());
		data.setCompletedExamDTO(getCompletedExamDTO());

		return data;
	}

	/**
	 * 
	 * @return
	 */
	private static ExamTestDTO getExamTestDTO() {
		ExamTestDTO examTestDTO = new ExamTestDTO();
		Long id = 1L;
		examTestDTO.setId(id);
		examTestDTO.setName("Exam-Test");
		examTestDTO.setDuration(50);
		examTestDTO.setExamQuestionDTOs(getExamQuestionDTOs());
		return examTestDTO;
	}

	/**
	 * 
	 * @return
	 */
	private static List<ExamQuestionDTO> getExamQuestionDTOs() {

		List<ExamQuestionDTO> questions = new ArrayList<>();

		for (long i = 1; i <= 10; i++) {
			ExamQuestionDTO q = new ExamQuestionDTO();
			q.setId(i);
			q.setText("Question" + i);
			q.setType(QuestionType.SINGLE);
			q.setStatus(ExamQuestionStatus.UNANSWERED);
			q.setExamAnswerDTOs(getExamAmswerDTOs());

			questions.add(q);
		}

		// 1 - single - correct
		questions.get(0).getExamAnswerDTOs().get(1).setIsChecked(true);
		questions.get(0).getExamAnswerDTOs().get(1).setIsCorrect(true);

		// 2 - single - incorrect
		questions.get(1).getExamAnswerDTOs().get(0).setIsChecked(true);
		questions.get(1).getExamAnswerDTOs().get(1).setIsCorrect(true);

		// 3 - single - unanswered
		questions.get(2).getExamAnswerDTOs().get(2).setIsCorrect(true);

		// 4 - single - correct
		questions.get(3).getExamAnswerDTOs().get(1).setIsChecked(true);
		questions.get(3).getExamAnswerDTOs().get(1).setIsCorrect(true);

		// 5 - single - correct
		questions.get(4).getExamAnswerDTOs().get(3).setIsChecked(true);
		questions.get(4).getExamAnswerDTOs().get(3).setIsCorrect(true);

		// 6 - multiple - correct
		questions.get(5).setType(QuestionType.MULTIPLE);
		questions.get(5).getExamAnswerDTOs().get(0).setIsChecked(true);
		questions.get(5).getExamAnswerDTOs().get(0).setIsCorrect(true);
		questions.get(5).getExamAnswerDTOs().get(3).setIsChecked(true);
		questions.get(5).getExamAnswerDTOs().get(3).setIsCorrect(true);

		// 7 - multiple - unanswered
		questions.get(6).setType(QuestionType.MULTIPLE);
		questions.get(6).getExamAnswerDTOs().get(0).setIsCorrect(true);
		questions.get(6).getExamAnswerDTOs().get(3).setIsCorrect(true);

		// 8 - multiple - incorrect
		questions.get(7).setType(QuestionType.MULTIPLE);
		questions.get(7).getExamAnswerDTOs().get(0).setIsChecked(true);
		questions.get(7).getExamAnswerDTOs().get(1).setIsCorrect(true);
		questions.get(7).getExamAnswerDTOs().get(3).setIsChecked(true);
		questions.get(7).getExamAnswerDTOs().get(3).setIsCorrect(true);

		// 9 - multiple - correct
		questions.get(8).setType(QuestionType.MULTIPLE);
		questions.get(8).getExamAnswerDTOs().get(0).setIsChecked(true);
		questions.get(8).getExamAnswerDTOs().get(0).setIsCorrect(true);
		questions.get(8).getExamAnswerDTOs().get(2).setIsChecked(true);
		questions.get(8).getExamAnswerDTOs().get(2).setIsCorrect(true);
		questions.get(8).getExamAnswerDTOs().get(3).setIsChecked(true);
		questions.get(8).getExamAnswerDTOs().get(3).setIsCorrect(true);

		// 10 - multiple - correct
		questions.get(9).setType(QuestionType.MULTIPLE);
		questions.get(9).getExamAnswerDTOs().get(0).setIsChecked(true);
		questions.get(9).getExamAnswerDTOs().get(0).setIsCorrect(true);
		questions.get(9).getExamAnswerDTOs().get(1).setIsChecked(true);
		questions.get(9).getExamAnswerDTOs().get(1).setIsCorrect(true);
		questions.get(9).getExamAnswerDTOs().get(2).setIsChecked(true);
		questions.get(9).getExamAnswerDTOs().get(2).setIsCorrect(true);
		questions.get(9).getExamAnswerDTOs().get(3).setIsChecked(true);
		questions.get(9).getExamAnswerDTOs().get(3).setIsCorrect(true);

		return questions;
	}

	/**
	 * 
	 * @return
	 */
	private static List<ExamAnswerDTO> getExamAmswerDTOs() {

		List<ExamAnswerDTO> answers = new ArrayList<>();

		for (long i = 1; i <= 4; i++) {
			ExamAnswerDTO a = new ExamAnswerDTO();
			a.setId(i);
			a.setText("Answer" + i);
			a.setIsChecked(false);
			a.setIsCorrect(false);
			answers.add(a);
		}

		return answers;
	}

	/**
	 * 
	 * @return
	 */
	private static CompletedExamDTO getCompletedExamDTO() {

		CompletedExamDTO c = new CompletedExamDTO();
		c.setId(2L);
		c.setIsCompleted(false);
		c.setPassingLimit(new BigDecimal(50).setScale(2, RoundingMode.CEILING));
		c.setScore(new BigDecimal(0).setScale(2, RoundingMode.CEILING));
		c.setStatus(CompletedExamStatus.FAILED);
		c.setTotalCorrect(0);
		c.setTotalIncorrect(0);
		c.setTotalUnanswered(0);
		return c;
	}

}
