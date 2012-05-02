/**
 * 
 */
package com.array.testprep.springvalidator;


import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.array.testprep.model.TestTaker;
import com.array.testprep.springservice.Service;


public class ForgotPasswordValidator implements Validator {

	private Service service;

	protected final Logger logger = Logger.getLogger(TestTaker.class);

	public boolean supports(Class clazz) {
		return clazz.equals(TestTaker.class);
	}

	@SuppressWarnings("unchecked")
	public void validate(Object obj, Errors errors) {
		logger.info("entering validate method of forgot password validator");
		if (!service.isValid(obj)) {
			errors.rejectValue("userName", "error.testTaker.invalidUser", null,
					"Invalid UserName.");
		}
		logger.info("Exiting validate method of ForgotPasswordValidator");
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
