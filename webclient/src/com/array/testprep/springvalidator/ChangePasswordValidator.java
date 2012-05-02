/**
 * @author mtshah
 *
 */
package com.array.testprep.springvalidator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.array.testprep.model.TestTaker;
import com.array.testprep.springservice.Service;

public class ChangePasswordValidator implements Validator {

	private Service service;

	protected final Logger logger = Logger.getLogger(TestTaker.class);

	public boolean supports(Class clazz) {
		return clazz.equals(TestTaker.class);
	}

	@SuppressWarnings("unchecked")
	public void validate(Object obj, Errors errors) {
		TestTaker testTaker = (TestTaker) obj;
		System.out.print("\n\t\t In validator ");

		if (testTaker == null) {
			errors.rejectValue("password", "error.testTaker.not-specified", null,
					"Value required.");
		} else {
			/* Validation Using IOC of Service */

			if (!service.isValid(testTaker)) {

				errors.rejectValue("password", "error.testTaker.invalid-pass",
						null, "Invalid Password.");
			}

		}

		logger.info("Exiting from ChangePassword Validator");
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
