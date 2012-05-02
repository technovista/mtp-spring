package com.array.testprep.springservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestResult;

import org.apache.log4j.Logger;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * This service class is related to TestResult Table. 
 * It perform all bussiness logic for insert , upadate, delte any testResult information.     
 * 
 * @author mtshah
 * 
 */
public class TestResultService implements Service {

	private Map<String, Object> modelMap;

	protected final Logger logger = Logger.getLogger(TestResultService.class);

	/**
	 * @see com.array.testprep.springservice.Service#getModelMap()
	 */
	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	/**
	 * Finds a user by id
	 * 
	 * @see service.Service#findById(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public TestResult findById(Integer id) throws Exception {
		ServiceDAO<TestResult> testResultdao = (ServiceDAO<TestResult>) ContextProvider
				.getApplicationContext().getBean("testResultDAO");
	
		TestResult testResult = new TestResult();
		//testResult
		//testResult.setTestResultId(id);
	
		
		if(testResultdao.selectBasedOnPrimaryKey(testResult)==null){
			return new TestResult();
		}else{
			return testResultdao.selectBasedOnPrimaryKey(testResult);
		}
	}

	/**
	 * Entry point method for service. Delegates to business methods
	 * 
	 * @see com.array.testprep.springservice.Service#processRequest(java.lang.String, java.lang.String,Object)
	 */
	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {
		
			logger.debug("Entering ProcessRequest of TestResult Service");
			logger.info("<Action : " + action + " > ");
			logger.info("<Form Object : " + entityObj + " > ");
			logger.info("<Form Entity : " + entity + " > ");
			if (entity.equalsIgnoreCase(Constants.ENTITY_TESTRESULT)) {
				// Gets all testResults
				if (action.equalsIgnoreCase(Constants.OPERATION_LIST)) {
					List<TestResult> allTestResultList = getAllTestResults();
					modelMap = new HashMap<String, Object>();
					modelMap.put("allTestResultList", allTestResultList);
					logger.debug("Total "+allTestResultList.size()+" users selected from TestResult Table");
				} else {
					// get single testResult
					if (action.equalsIgnoreCase(Constants.OPERATION_TESTRESULT)) {
						List<TestResult> testResultList = getTestResult((TestResult) entityObj);
						modelMap = new HashMap<String, Object>();
						modelMap.put("testResultList", testResultList);
					}
				}
				// update testResult by primary key
				if (action.equalsIgnoreCase(Constants.OPERATION_UPDATE)) {
								
					updateTestResult((TestResult) entityObj);
					// get all testResult fields after updating any of fields to store
					// object in session again
					List<TestResult> testResultList = getTestResult((TestResult) entityObj);		
					modelMap = new HashMap<String, Object>();
					modelMap.put("testResultList", testResultList);
				}
				// delete testResult by primary key
				if (action.equalsIgnoreCase(Constants.OPERATION_DELETE)) {

					this.deleteTestResult((TestResult) entityObj);
				}
				if (action.equalsIgnoreCase(Constants.OPERATION_ADD)) {

					this.insertTestResult((TestResult) entityObj);
				}
			}

			logger.debug("Exiting ProcessRequest of TestResult Service");
			logger.info("Exiting ProcessRequest of TestResult Service");
			
	}

	/**
	 * Get specific testResult
	 * 
	 * @param testResult :
	 * @return List : which contains this testResult fields
	 */
	@SuppressWarnings("unchecked")
	private List<TestResult> getTestResult(TestResult testResult) {
		ServiceDAO<TestResult> testResultdao = (ServiceDAO<TestResult>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTRESULT_DAO);
		return testResultdao.select(testResult);
	}

	/**
	 * Gets all testResult
	 * 
	 * @return List: list of all testResults
	 */

	@SuppressWarnings("unchecked")
	private List<TestResult> getAllTestResults() {
		ServiceDAO<TestResult> testResultdao = (ServiceDAO<TestResult>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTRESULT_DAO);
		return testResultdao.selectAll(Constants.ENTITY_TESTRESULT);
	}

	/**
	 * Add new testResult
	 * 
	 * @param testResult:
	 *            testResult object which will be added
	 */

	@SuppressWarnings( { "unchecked", "unused" })
	private void insertTestResult(TestResult testResult) {
		ServiceDAO<TestResult> testResultdao = (ServiceDAO<TestResult>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTRESULT_DAO);
		testResultdao.insert(testResult);
	}

	/**
	 * Updates a testResult
	 * 
	 * @param testResult:
	 *            testResult object which will be updated
	 */
	@SuppressWarnings("unchecked")
	private void updateTestResult(TestResult testResult) {
		ServiceDAO<TestResult> testResultdao = (ServiceDAO<TestResult>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTRESULT_DAO);
		/*testResult.setPassword(new String(RegistrationService.encrypt(testResult
				.getPassword())));*/
		testResultdao.update(testResult);
	}
	
	/**
	 * Deletes a testResult
	 * 
	 * @param testResult:
	 *            testResult object which will be deleted
	 */
	@SuppressWarnings("unchecked")
	private void deleteTestResult(TestResult testResult) {
		ServiceDAO<TestResult> testResultdao = (ServiceDAO<TestResult>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTRESULT_DAO);
		testResultdao.delete(testResult);
	}

	/**
	 * @param obj :
	 *            Object of TestResult
	 * @return linkStatus : returns boolean weather testResult is exists or not
	 */
	@SuppressWarnings("unchecked")
	public boolean isValid(Object obj) {
		
			
		return true;

	}
}
