package com.duskol.ecdl.logger;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestLogger {
	
	private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);

}
