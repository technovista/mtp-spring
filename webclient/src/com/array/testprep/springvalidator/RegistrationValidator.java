package com.array.testprep.springvalidator;


import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.array.testprep.model.TestTaker;
import com.array.testprep.springservice.Service;


/**
 * 
 * @author mtshah
 * 
 */
public class RegistrationValidator implements Validator {
	protected final Logger logger = Logger.getLogger(TestTaker.class);

	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public boolean supports(Class clazz) {
		return clazz.equals(TestTaker.class);
	}

	@SuppressWarnings("unchecked")
	public void validate(Object obj, Errors errors) {
		TestTaker testTaker = (TestTaker) obj;		
		ValidationUtils.rejectIfEmpty(errors, "emailId",
				"error.testTaker.not-specified-emailId",
				"emailId can not be empty");
		ValidationUtils.rejectIfEmpty(errors, "password",
				"error.testTaker.not-specified-password",
				"Password can not be empty");

		if (testTaker == null) {
			errors.rejectValue("useName", "error.testTaker.not-specified", null,
					" Value required.");
		} else {
			TestTaker validTestTaker = new TestTaker();
			validTestTaker.setEmailId(testTaker.getEmailId());			
			if (!service.isValid(validTestTaker)) {
				
				errors.rejectValue("emailId", "error.testTaker.exist-user", null,
						" EmailId Already Exist");
			}		
		
		}
	}
}
