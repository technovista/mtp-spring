/**
 * @author mtshah
 *
 */
package com.array.testprep.springvalidator;


import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.array.testprep.model.TestTaker;
import com.array.testprep.springservice.Service;

public class TestTakerValidator implements Validator {

	private Service service;

	protected final Logger logger = Logger.getLogger(TestTaker.class);

	public boolean supports(Class clazz) {
		return clazz.equals(TestTaker.class);
	}

	@SuppressWarnings("unchecked")
	public void validate(Object obj, Errors errors) {
		logger.info("Entering into TestTaker Validator");
		TestTaker testTaker = (TestTaker) obj;		
		ValidationUtils.rejectIfEmpty(errors, "emailId",
				"error.testTaker.not-specified-emailId",
				"emailId can not be empty");
		ValidationUtils.rejectIfEmpty(errors, "password",
				"error.testTaker.not-specified-password",
				"Password can not be empty");

		if (testTaker == null) {
			errors.rejectValue("emailId", "error.testTaker.not-specified", null,
					"Value required.");
		} else {
			/* Validation Using IOC of Service */

			if (!service.isValid(obj))
				errors.rejectValue("emailId", "error.testTaker.invalid-user",
						null, "Invalid emailId.");
			logger.info("Exiting from TestTaker Validator");
		}
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
