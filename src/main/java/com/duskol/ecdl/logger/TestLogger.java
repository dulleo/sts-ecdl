package com.duskol.ecdl.logger;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.duskol.ecdl.error.ErrorCodes;
import com.duskol.ecdl.exception.DataIntegrityException;
import com.duskol.ecdl.exception.InternalException;
import com.duskol.ecdl.exception.NotFoundException;
import com.duskol.ecdl.exception.ResourceNotFoundException;

@Aspect
@Component
public class TestLogger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLogger.class);
	private static final String MESSAGE_FORMAT = "Method {} unsuccessfully finished. ERROR: {} - {}";
	
	@Pointcut("execution(void com.duskol.ecdl.controller.TestController.createTest(com.duskol.ecdl.dto.TestDTO)) "
			+ "&& args(testDTO)")
	public void createTestPointcut(com.duskol.ecdl.dto.TestDTO testDTO) {
		//
	}
	
	@Around("createTestPointcut(testDTO)")
	public void createTest(ProceedingJoinPoint jp, com.duskol.ecdl.dto.TestDTO testDTO) throws Throwable {
		
		String methodName = jp.getSignature().getName();
		String args = Arrays.toString(jp.getArgs()); 
		
		try {
			LOGGER.info("Method {} has started", methodName);
			//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			//logger.info("Method " + UPDATE_MERCHANT_ITEMS_METHOD_NAME +  " requested by user: " + authentication.getName() + "-" + authentication.getAuthorities());
			LOGGER.info("Args: {}",args);
			jp.proceed();
			LOGGER.info("Method {} succesfully successfully finished.......", methodName);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, methodName);
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, methodName);
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, methodName);
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.TEST_CAN_NOT_BE_CREATED, methodName);
		}
	}
	
	private void getNotFoundError(Exception e, ErrorCodes errorCodes, String methodName) throws NotFoundException {
		LOGGER.error(MESSAGE_FORMAT, methodName, e.getMessage(),e);
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
			LOGGER.error(MESSAGE_FORMAT,methodName,message,e);
            throw new DataIntegrityException(message, errorCodes);
        } else {
        	LOGGER.error(MESSAGE_FORMAT,methodName,e.getMessage(), e);
            throw new DataIntegrityException(e.getMessage(), errorCodes); 
        }
	}
	
	private void getInternalError(Exception e, ErrorCodes errorCodes, String methodName) throws InternalException {
		LOGGER.error(MESSAGE_FORMAT, methodName, e.getMessage(), e);
		throw new InternalException(e.getMessage(), errorCodes);
	}
}
