package com.array.testprep.springservice;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.TestTaker;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * @author mtshah
 *
 */
public class ForgotPasswordService implements Service {
	private Map<String, Object> modelMap;

	Logger logger = Logger.getLogger(ForgotPasswordService.class);

	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	public Object findById(Integer id) {

		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean isValid(Object obj) {
		logger.info("Entering isValid method of ForgotPasswordService ");
		TestTaker testTaker = (TestTaker) obj;
		String username = testTaker.getEmailId();
		boolean testTakerStatus = false;
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		String hqlQuery;
		if (username == null || (username.trim()).length() == 0) {
			hqlQuery = "Select testTaker from TestTaker testTaker where testTaker.email='"
					+ testTaker.getEmailId() + "'";
		} else {
			hqlQuery = "Select testTaker from TestTaker testTaker where testTaker.userName='"
					+ testTaker.getEmailId() + "'";
		}
		List<TestTaker> testTakerList = testTakerdao.executeHQL(hqlQuery);
		if (testTakerList.size() > 0)
			testTakerStatus = true;
		logger.info("Exiting isValid method of ForgotPasswordService");
		return testTakerStatus;
	}

	@SuppressWarnings("unchecked")
	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {
		logger.debug("Entering the process request of ForgotPasswordService");
	
		if (entity.equalsIgnoreCase(Constants.ENTITY_TESTTAKER))
			;
		{
			if (action.equalsIgnoreCase(Constants.OPERATION_SUBMIT)) {

				List<TestTaker> testTakerList = null;
				TestTaker testTaker = (TestTaker) entityObj;
				Properties p = new Properties();
				String path = "";
				ApplicationContext context = ContextProvider
						.getApplicationContext();
				InputStream resourceAsStream = context.getResource(
						"WEB-INF//classes//application.properties")
						.getInputStream();
				p.load(resourceAsStream);
				logger.debug("Hostname " + p.getProperty("SMTP_HOST"));
				path = p.getProperty("SMTP_HOST").trim();
				p.put("mail.smtp.host", path);
				String userName = testTaker.getEmailId();
				String email = testTaker.getEmailId();
				ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
						.getApplicationContext().getBean(Constants.TESTTAKER_DAO);

				if (userName == null || (userName.trim().length()) == 0) {
					testTaker.setEmailId(null);
					testTakerList = testTakerdao.select(testTaker);
				}
				if (email == null || (email.trim().length() == 0)) {
					testTaker.setEmailId(null);
					testTakerList = testTakerdao.select(testTaker);
				}
				testTaker = testTakerList.get(0);
				p.put("mail.from", "webmaster@ilib.co.in");
				Session session = Session.getInstance(p, null);

				MimeMessage msg = new MimeMessage(session);
				msg.setFrom();
				msg.setRecipients(Message.RecipientType.TO, testTaker.getEmailId());
				msg.setSubject("your password for account at i-Lib");
				msg.setSentDate(new Date());
				StringBuffer buffer = new StringBuffer();
				Random random = new Random();
				buffer.append(random.nextInt());
				buffer.append(testTaker.getEmailId());
				testTaker.setPassword(new String(RegistrationService.encrypt(buffer
						.toString())));
				testTakerdao.saveOrUpdate(testTaker);
				String content = "Dear "
						+ testTaker.getEmailId()
						+ ",\n\nYour password for your account at i-lib is "
						+ buffer.toString()
						+ ".Thanks for using the service.\n\nRegards,\ni-Lib Team";
				msg.setText(content);
				Transport.send(msg);

			}

		}
		logger.info("Exiting the process request of ForgotPasswordService");
		modelMap = new HashMap<String, Object>();
	}

}
