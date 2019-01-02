package com.duskol.ecdl.logger;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * Created by duskol on Jan 2, 2019
 *
 */
@Aspect
@Component
public class QuestionLogger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestLogger.class);
	private static final String MESSAGE_FORMAT_START = "Method \"{}\" has been started";
	private static final String MESSAGE_FORMAT_FINISH = "Method \"{}\" successfully finished.......";
	private static final String MESSAGE_FORMAT_ERROR = "Method \"{}\" unsuccessfully finished. ERROR: {}";
	private static String METHOD_NAME;
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(requestMapping)")
	public void controller(RequestMapping requestMapping) {}
	
	@Before("controller(requestMapping)")
	public void advice(JoinPoint jp, RequestMapping requestMapping) {
		METHOD_NAME = jp.getSignature().getName();
		LOGGER.info(MESSAGE_FORMAT_START, METHOD_NAME);
		LOGGER.info("Method Type: {}", requestMapping.method()[0]);
		LOGGER.info("URL: {}", requestMapping.value()[0]);
		LOGGER.info("Args: {}",Arrays.toString(jp.getArgs()));
	}

}
