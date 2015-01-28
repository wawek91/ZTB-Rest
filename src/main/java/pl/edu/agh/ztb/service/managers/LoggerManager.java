package pl.edu.agh.ztb.service.managers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import loggers.impl.RestLogger;

public class LoggerManager {
	
	private static RestLogger loggerInstance;
	
	private LoggerManager() {
		
	}
	
	public static RestLogger getLoggerInstance() {
		
		if(loggerInstance == null) {
			ApplicationContext springContext = new ClassPathXmlApplicationContext("ztb7-context.xml");
			loggerInstance = springContext.getBean(RestLogger.class);
		}
		return loggerInstance;
	}
}
