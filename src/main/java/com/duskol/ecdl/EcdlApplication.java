package com.duskol.edcl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcdlApplication {
	
	private final static Logger logger = LoggerFactory.getLogger(EcdlApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EcdlApplication.class, args);
		logger.info("App started......");
	}
}
