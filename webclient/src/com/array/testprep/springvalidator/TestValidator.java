package com.array.testprep.springvalidator;


import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.array.testprep.model.Test;
import com.array.testprep.springservice.Service;


/**
 * 
 * @author mtshah
 * 
 */
public class TestValidator implements Validator {
	protected final Logger logger = Logger.getLogger(Test.class);

	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public boolean supports(Class clazz) {
		return clazz.equals(Test.class);
	}

	@SuppressWarnings("unchecked")
	public void validate(Object obj, Errors errors) {
		logger.info("Entering into Test  Validator");
		System.out.println("ACTION :: < < " + obj.toString());
		Test test = (Test) obj;
		ValidationUtils.rejectIfEmpty(errors, "testName",
				"error.testType.not-specified-testTypeName",
				"Test name can not be empty");

		if (test == null) {
			logger.debug("Test field can not be empty");
			errors.rejectValue("testName", "error.testType.not-specified",
					null, " Value required.");
		} else {
			Test test1 = new Test();
			test1.setTestName(test.getTestName());
			test1.setTestId(test.getTestId());
			if (!service.isValid(test)) {
				logger.debug("Test is already exist");
				errors.rejectValue("testName",
						"error.testType.exist-testType", null,
						" TestTypeName Already Exist");
			}
		}
		logger.info("Exiting from Test  Validator");
	}
}
