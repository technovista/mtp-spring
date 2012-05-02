package com.array.testprep.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextProvider implements ApplicationContextAware  {
	
	private static ApplicationContext ctx;
    
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		 ctx = context; 
	}
	
	public static  ApplicationContext  getApplicationContext() {
		return ctx;		
	}

}
