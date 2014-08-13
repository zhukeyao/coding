package com.keyao.study.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicLogging {
	final Logger logger = LoggerFactory.getLogger(BasicLogging.class);
	
	public void testLogging() {
		logger.info("test logger info");
		logger.debug("test logger debug");
		logger.error("test logger error");
		logger.warn("test logger warn");
		logger.trace("test logger trace");
	}
	
	static public void main(String[] argv) throws Exception {
		BasicLogging basicLog = new BasicLogging();
		basicLog.testLogging();
	}

}
