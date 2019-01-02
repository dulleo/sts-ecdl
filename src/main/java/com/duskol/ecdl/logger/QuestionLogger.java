package com.duskol.ecdl.logger;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duskol.ecdl.dto.QuestionDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.exception.DataIntegrityException;
import com.duskol.ecdl.exception.InternalException;
import com.duskol.ecdl.exception.NotFoundException;
import com.duskol.ecdl.exception.ResourceNotFoundException;

/**
 * 
 * Created by duskol on Jan 2, 2019
 *
 */
@Aspect
@Component
public class QuestionLogger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionLogger.class);
	private static final String MESSAGE_FORMAT_START = "Method \"{}\" has been started";
	private static final String MESSAGE_FORMAT_FINISH = "Method \"{}\" successfully finished.......";
	private static final String MESSAGE_FORMAT_ERROR = "Method \"{}\" unsuccessfully finished. ERROR: {}";
	private static String METHOD_NAME;
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController com.duskol.ecdl.controller.QuestionController) && @annotation(requestMapping)")
	public void controller(RequestMapping requestMapping) {}
	
	@Before("controller(requestMapping)")
	public void advice(JoinPoint jp, RequestMapping requestMapping) {
		METHOD_NAME = jp.getSignature().getName();
		LOGGER.info(MESSAGE_FORMAT_START, METHOD_NAME);
		LOGGER.info("Method Type: {}", requestMapping.method()[0]);
		LOGGER.info("URL: {}", requestMapping.value()[0]);
		LOGGER.info("Method Args: {}", Arrays.toString(jp.getArgs()));
	}
	
	@Pointcut("execution(void com.duskol.ecdl.controller.QuestionController.createQuestion(Long, com.duskol.ecdl.dto.QuestionDTO)) "
			+ "&& args(id, questionDTO)")
	public void createQuestionPointcut(Long id, QuestionDTO questionDTO) {
		//
	}
	
	@Around("createQuestionPointcut(id, questionDTO)")
	public void createQuestion(ProceedingJoinPoint jp, Long id, QuestionDTO questionDTO) throws Throwable {
		
		try {
			jp.proceed();
			LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, METHOD_NAME);
		}
	}
	
	@Pointcut("execution(com.duskol.ecdl.dto.QuestionDTO com.duskol.ecdl.controller.QuestionController.getQuestion(Long)) "
			+ "&& args(id)")
	public void getQuestionPointcut(Long id) {
		//
	}
	
	@Around("getQuestionPointcut(id)")
	public QuestionDTO getQuestion(ProceedingJoinPoint jp, Long id) throws Throwable {
		
		QuestionDTO result = null;
		
		try {
			result = (QuestionDTO) jp.proceed();
			LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.QUESTION_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.QUESTION_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.QUESTION_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.QUESTION_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		}
		return result;
	}
	
	@Pointcut("execution(java.util.List<com.duskol.ecdl.dto.QuestionDTO> com.duskol.ecdl.controller.QuestionController.getQuestions(Long)) "
			+ "&& args(id)")
	public void getQuestionsPointcut(Long id) {
		//
	}
	
	@SuppressWarnings("unchecked")
	@Around("getQuestionsPointcut(id)")
	public List<QuestionDTO> getQuestions(ProceedingJoinPoint jp, Long id) throws Throwable {
		
		List<QuestionDTO> result = null;
		
		try {
			result = (List<QuestionDTO>) jp.proceed();
			LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.QUESTIONS_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.QUESTIONS_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.QUESTIONS_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.QUESTIONS_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		}
		return result;
	}
	
	@Pointcut("execution(void com.duskol.ecdl.controller.QuestionController.deleteQuestion(Long)) "
			+ "&& args(id)")
	public void deleteQuestionPointcut(Long id) {
		//
	}
	
	@Around("deleteQuestionPointcut(id)")
	public void deleteQuestion(ProceedingJoinPoint jp, Long id) throws Throwable {
		
		try {
			jp.proceed();
			LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.QUESTION_CAN_NOT_BE_DELETED, METHOD_NAME);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.QUESTION_CAN_NOT_BE_DELETED, METHOD_NAME);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.QUESTION_CAN_NOT_BE_DELETED, METHOD_NAME);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.QUESTION_CAN_NOT_BE_DELETED, METHOD_NAME);
		}
	}
	
	@Pointcut("execution(void com.duskol.ecdl.controller.QuestionController.editQuestion(Long, com.duskol.ecdl.dto.QuestionDTO)) "
			+ "&& args(id, questionDTO)")
	public void editQuestionPointcut(Long id, QuestionDTO questionDTO) {
		//
	}
	
	@Around("editQuestionPointcut(id, questionDTO)")
	public void editQuestion(ProceedingJoinPoint jp, Long id, QuestionDTO questionDTO) throws Throwable {
		
		try {
			jp.proceed();
			LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.QUESTION_CAN_NOT_BE_UPDATED, METHOD_NAME);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, METHOD_NAME);
		}
	}
	
	private void getNotFoundError(Exception e, ErrorCodes errorCodes, String methodName) throws NotFoundException {
		LOGGER.error(MESSAGE_FORMAT_ERROR, methodName, e.getMessage(),e);
		throw new NotFoundException(e.getMessage(), errorCodes);
	}
	
	private void getDataIntegrityViolationError(DataIntegrityViolationException e, ErrorCodes errorCodes, String methodName) throws DataIntegrityException {
		generateError(e, errorCodes, methodName);    
	}
	
	private void getDataAccessError(DataAccessException e, ErrorCodes errorCodes, String methodName) throws DataIntegrityException {
		generateError(e, errorCodes, methodName); 
	}
	
	private void generateError(Exception e, ErrorCodes errorCodes, String methodName) throws DataIntegrityException {
		if(e.getCause() instanceof JDBCException && ((JDBCException)e.getCause()).getSQLException() != null)
        {                    
			String message = ((JDBCException)e.getCause()).getSQLException().getMessage();
			LOGGER.error(MESSAGE_FORMAT_ERROR,methodName,message,e);
            throw new DataIntegrityException(message, errorCodes);
        } else {
        	LOGGER.error(MESSAGE_FORMAT_ERROR,methodName,e.getMessage(), e);
            throw new DataIntegrityException(e.getMessage(), errorCodes); 
        }
	}
	
	private void getInternalError(Exception e, ErrorCodes errorCodes, String methodName) throws InternalException {
		LOGGER.error(MESSAGE_FORMAT_ERROR, methodName, e.getMessage(), e);
		throw new InternalException(e.getMessage(), errorCodes);
	}
}
