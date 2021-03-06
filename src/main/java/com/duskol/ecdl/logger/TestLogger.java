package com.duskol.ecdl.logger;

import java.util.Arrays;

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

import com.duskol.ecdl.dto.TestDTO;
import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.exception.DataIntegrityException;
import com.duskol.ecdl.exception.InternalException;
import com.duskol.ecdl.exception.NotFoundException;
import com.duskol.ecdl.exception.ResourceNotFoundException;

/**
 * 
 * Created by Dusko Lucic on Dec 31, 2018
 *
 */
@Aspect
@Component
public class TestLogger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLogger.class);
	private static final String MESSAGE_FORMAT_START = "Method \"{}\" has been started";
	private static final String MESSAGE_FORMAT_FINISH = "Method \"{}\" successfully finished.......";
	private static final String MESSAGE_FORMAT_ERROR = "Method \"{}\" unsuccessfully finished. ERROR: {}";
	private static String METHOD_NAME;
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController com.duskol.ecdl.controller.TestController) && @annotation(requestMapping)")
	public void controller(RequestMapping requestMapping) {}
	
	@Before("controller(requestMapping)")
	public void advice(JoinPoint jp, RequestMapping requestMapping) {
		METHOD_NAME = jp.getSignature().getName();
		LOGGER.info(MESSAGE_FORMAT_START, METHOD_NAME);
		LOGGER.info("Method Type: {}", requestMapping.method()[0]);
		LOGGER.info("URL: {}", requestMapping.value()[0]);
		LOGGER.info("Args: {}",Arrays.toString(jp.getArgs()));
	}
	
	@Pointcut("execution(void com.duskol.ecdl.controller.TestController.createTest(com.duskol.ecdl.dto.TestDTO)) "
			+ "&& args(testDTO)")
	public void createTestPointcut(com.duskol.ecdl.dto.TestDTO testDTO) {
		//
	}
	
	@Around("createTestPointcut(testDTO)")
	public void createTest(ProceedingJoinPoint jp, com.duskol.ecdl.dto.TestDTO testDTO) throws Throwable {
		
		try {
			jp.proceed();
			LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, METHOD_NAME);
		}
	}
	
	@Pointcut("execution(void com.duskol.ecdl.controller.TestController.editTest(com.duskol.ecdl.dto.TestDTO)) "
			+ "&& args(testDTO)")
	public void editTestPointcut(com.duskol.ecdl.dto.TestDTO testDTO) {
		
	}
	
	@Around("editTestPointcut(testDTO)")
	public void editTest(ProceedingJoinPoint jp, com.duskol.ecdl.dto.TestDTO testDTO) throws Throwable {
		
		try {
			jp.proceed();
			LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, METHOD_NAME);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, METHOD_NAME);
		}
	}
	
	@Pointcut("execution(com.duskol.ecdl.dto.TestDTO com.duskol.ecdl.controller.TestController.getTest(Long)) "
			+ "&& args(id)")
	public void getTestPointcut(Long id) {
		//
	}
	
	@Around("getTestPointcut(id)")
	public TestDTO getTest(ProceedingJoinPoint jp, Long id) throws Throwable {

		TestDTO result = null;
		
		try {
			result = (TestDTO) jp.proceed();
			LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.TEST_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.TEST_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.TEST_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.TEST_CAN_NOT_BE_PROVIDED, METHOD_NAME);
		}
		return result;
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
