package com.array.testprep.springservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.TestTaker;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * 
 * @author mtshah
 * 
 */
@SuppressWarnings("unchecked")
public class RegistrationService implements Service {
	private Map<String, Object> modelMap;

	protected final Logger logger = Logger.getLogger(RegistrationService.class);

	/**
	 * @see com.array.testprep.springservice.Service#getModelMap()
	 */
	public Map<String, Object> getModelMap() {

		return modelMap;
	}

	/**
	 * @see service.Service#findById(java.lang.Integer)
	 */

	@SuppressWarnings("unchecked")
	public TestTaker findById(Integer id) throws Exception {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);

		TestTaker testTaker = new TestTaker();
		testTaker.setTestTakerId(id);
		return testTakerdao.selectBasedOnPrimaryKey(testTaker);
	}

	/**
	 * Entry point method for service. Delegates to business methods
	 * 
	 * @see com.array.testprep.springservice.Service#processRequest(java.lang.String, java.lang.String,
	 *      Object )
	 */
	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {
		logger.debug("Entering ProcessRequest of Registration  Service");
		logger.info("<Action : " + action + " > ");
		logger.info("<Form Object : " + entityObj + " > ");
		TestTaker testTaker = null;
		if (action.equalsIgnoreCase(Constants.OPERATION_LIST)) {
			List<TestTaker> testTakerList = getAllTestTakers();
			modelMap = new HashMap<String, Object>();
			modelMap.put("testTakerList", testTakerList);
			logger.debug("Total " + testTakerList.size()
					+ " users selected from TestTaker Table");
		} else {

			if (action.equalsIgnoreCase(Constants.OPERATION_ADD)) {

				logger.info("Insert profile data");
				testTaker = (TestTaker) entityObj;
				testTaker.setIsAdmin("n");
				insertTestTaker(testTaker);
				logger.debug("User <" + ((TestTaker) entityObj).getEmailId()
						+ ">: Registerd and data added into TestTaker Table");
			} else if (action.equalsIgnoreCase(Constants.OPERATION_UPDATE)) {
				testTaker = (TestTaker) entityObj;
				updateTestTaker(testTaker);
				logger.debug("User <" + ((TestTaker) entityObj).getEmailId()
						+ ">: updated into TestTaker Table");
			} else if (action.equalsIgnoreCase(Constants.OPERATION_DELETE)) {
				testTaker = (TestTaker) entityObj;
				deleteTestTaker(testTaker);
				logger.debug("User <" + ((TestTaker) entityObj).getEmailId()
						+ ">: deleted into TestTaker Table");
			}
		}
		logger.debug("Exiting ProcessRequest of Registration Service");
	}

	/**
	 * Gets all testTaker
	 * 
	 * @return List: list of all testTakers
	 */
	@SuppressWarnings("unchecked")
	private List<TestTaker> getAllTestTakers() {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		return testTakerdao.selectAll(Constants.ENTITY_TESTTAKER);
	}

	/**
	 * Add new testTaker
	 * 
	 * @param testTaker:
	 *            testTaker object which will be added
	 */
	@SuppressWarnings("unchecked")
	private void insertTestTaker(TestTaker testTaker) {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		String secret = new String(RegistrationService.encrypt(testTaker
				.getPassword()));
		testTaker.setPassword(secret);
		testTakerdao.insert(testTaker);
	}

	/**
	 * Updates a testTaker
	 * 
	 * @param testTaker:
	 *            testTaker object which will be updated
	 */

	@SuppressWarnings("unchecked")
	private void updateTestTaker(TestTaker testTaker) {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		testTakerdao.update(testTaker);
	}

	/**
	 * Deletes a testTaker
	 * 
	 * @param testTaker:
	 *            testTaker object which will be deleted
	 */
	@SuppressWarnings("unchecked")
	private void deleteTestTaker(TestTaker testTaker) {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		testTakerdao.delete(testTaker);
	}

	/**
	 * @param obj :
	 *            Object of TestTaker
	 * @return linkStatus : returns boolean weather testTaker is exists or not
	 */
	@SuppressWarnings("unchecked")
	public boolean isValid(Object obj) {
		TestTaker testTaker = (TestTaker) obj;
		boolean testTakerStatus = true;
		logger.debug("User <" + testTaker.getEmailId()
				+ ":> has try registrion with this userNsme");
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		List<TestTaker> testTakerList = testTakerdao.select(testTaker);
		if (testTakerList.size() > 0) {
			testTakerStatus = false;
			logger.debug("User <" + ((TestTaker) testTakerList.get(0)).getEmailId()
					+ ">: is exist in TestTaker Table");
		}

		return testTakerStatus;
	}

	public static byte[] encrypt(String x) {
		java.security.MessageDigest d = null;
		try {

			d = java.security.MessageDigest.getInstance("SHA-1");
			d.reset();
			d.update(x.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return d.digest();
	}
}