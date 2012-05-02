package com.array.testprep.springservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.Test;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * 
 * @author mtshah
 * 
 */
public class TestService implements Service {

	private Map<String, Object> modelMap;

	protected final Logger logger = Logger.getLogger(TestService.class);

	/**
	 * @see com.array.testprep.springservice.Service#getModelMap()
	 */
	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	/**
	 * Finds testType by id
	 * 
	 * @see service.Service#findById(java.lang.Integer)
	 */

	@SuppressWarnings("unchecked")
	public Test findById(Integer id) throws Exception{
		ServiceDAO<Test> testTypedao = (ServiceDAO<Test>) ContextProvider
				.getApplicationContext().getBean(Constants.TEST_DAO);
		Test test = new Test();
		test.setTestId(id);
		
		return testTypedao.selectBasedOnPrimaryKey(test);
	}

	/**
	 * Entry point method for service. Delegates to business methods
	 * 
	 * @see com.array.testprep.springservice.Service#processRequest(java.lang.String, java.lang.String,Object)
	 */
	public void processRequest(String action, String entity, Object entityObj)throws Exception {
		logger.info("****************MEGHA******Entering ProcessRequest of Test Service");
		logger.info("<Action : " + action + " > ");
		logger.info("<Form Object : " + entityObj + " > ");
		
		Test test = null;
		
		if (action.equalsIgnoreCase(Constants.OPERATION_DELETE)) {
			logger.info("****************MEGHA******In delete action");
			test = (Test) entityObj;
			this.deleteTestType(test);
			logger.info("TEST<"+test.getTestName()+">: deleted from Test Table. ");
			
		}
		if (action.equalsIgnoreCase(Constants.OPERATION_LIST)
				|| action.equalsIgnoreCase(Constants.OPERATION_SEARCH)) {
			
			List<Test> testList = getAllTestType();
			
			//Collections.sort(testTypeList);
			modelMap = new HashMap<String, Object>();	
			modelMap.put("testList", testList);
		
		}// for addition of new Test
		else if (action.equalsIgnoreCase(Constants.OPERATION_ADD)) {
			test = (Test) entityObj;
			this.insertTest(test);
			logger.debug("TEST <"+test.getTestName()+">: inserted into Test Table. ");
			
		}
		// for update Test
		 if (action.equalsIgnoreCase(Constants.OPERATION_UPDATE)) {
			test = (Test) entityObj;
			this.updateTestType(test);
			logger.info("TEST <"+test.getTestName()+">: updated into Test Table. ");
		}// for deletion of Test
		
		logger.info("Exiting ProcessRequest of Test Service");
	}

	/**
	 * Gets list of all testType
	 * 
	 * @return List: list contains all categories
	 */
	@SuppressWarnings("unchecked")
	private List<Test> getAllTestType() {
		ServiceDAO<Test> testTypedao = (ServiceDAO<Test>) ContextProvider
				.getApplicationContext().getBean(Constants.TEST_DAO);		
		return testTypedao.selectAll(Constants.ENTITY_TEST);
	}

	/**
	 * Add new testType
	 * 
	 * @param test
	 */
	@SuppressWarnings("unchecked")
	private void insertTest(Test test) {
		ServiceDAO<Test> testdao = (ServiceDAO<Test>) ContextProvider
				.getApplicationContext().getBean(Constants.TEST_DAO);
		testdao.insert(test);
	}

	/**
	 * update testType
	 * 
	 * @param testType:
	 *            testType object which will be updated
	 */
	@SuppressWarnings("unchecked")
	private void updateTestType(Test test) {
		ServiceDAO<Test> testTypedao = (ServiceDAO<Test>) ContextProvider
				.getApplicationContext().getBean(Constants.TEST_DAO);
		testTypedao.update(test);
	}

	/**
	 * delete testType
	 * 
	 * @param testType:
	 *            testType object which will be updated
	 */
	@SuppressWarnings("unchecked")
	private void deleteTestType(Test test) {
		ServiceDAO<Test> testTypedao = (ServiceDAO<Test>) ContextProvider
				.getApplicationContext().getBean(Constants.TEST_DAO);
		System.out.println("IN method delete test type");
		testTypedao.delete(test);
	}

	/**
	 * Deletes a testType
	 * 
	 * @param testType :
	 *            testType object which will be deleted
	 */
		//TODO Check this method megha
//	@SuppressWarnings("unchecked")
//	private void deleteTestType(Test testType) {
//		ServiceDAO<Test> testTypedao = (ServiceDAO<Test>) ContextProvider
//				.getApplicationContext().getBean(Constants.TESTTYPE_DAO);
//		ServiceDAO<Book> bookdao = (ServiceDAO<Book>) ContextProvider
//				.getApplicationContext().getBean(Constants.BOOK_DAO);
//		Book book = new Book();
//		book.setTestTypeId(testType.getTestTypeId());
//		List<Book> bookList = bookdao.select(book);
//		if (!(bookList.size() > 0)) {
//			testTypedao.delete(testType);
//		}else{
//			logger.debug("TESTTYPE<"+testType.getTestTypeName()+">: can not be deleted. ");
//		}
//
//	}

	/**
	 * @param obj :
	 *            Object of Test
	 * @return testTypeStatus : returns boolean weather testType is exists or
	 *         not
	 */
	@SuppressWarnings("unchecked")
	public boolean isValid(Object obj) {
		Test test = (Test) obj;
		boolean testTypeStatus = true;
		ServiceDAO<Test> testTypedao = (ServiceDAO<Test>) ContextProvider
				.getApplicationContext().getBean(Constants.TEST_DAO);
		List<Test> testTypeList = testTypedao.select(test);
		if (testTypeList.size() > 0) {
			testTypeStatus = false;
			// check null for new entry if not null 
			if (test.getTestId()!=null && test.getTestId().equals(
					//comaparing id with itself
					testTypeList.get(0).getTestId())) {
				testTypeStatus = true;
			}
		}
		logger.debug(" \n\n\t\t\t STATUS = " + testTypeStatus);
		return testTypeStatus;
	}
}
