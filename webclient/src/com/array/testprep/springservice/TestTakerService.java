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
 * This service class is related to TestTaker Table. 
 * It perform all bussiness logic for insert , upadate, delte any testTaker information.     
 * 
 * @author mtshah
 * 
 */
public class TestTakerService implements Service {

	private Map<String, Object> modelMap;

	protected final Logger logger = Logger.getLogger(TestTakerService.class);

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
	public TestTaker findById(Integer id) throws Exception {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean("testTakerDAO");
	
		TestTaker testTaker = new TestTaker();
		testTaker.setTestTakerId(id);
	
		
		if(testTakerdao.selectBasedOnPrimaryKey(testTaker)==null){
			return new TestTaker();
		}else{
			return testTakerdao.selectBasedOnPrimaryKey(testTaker);
		}
	}

	/**
	 * Entry point method for service. Delegates to business methods
	 * 
	 * @see com.array.testprep.springservice.Service#processRequest(java.lang.String, java.lang.String,Object)
	 */
	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {
		
			logger.debug("Entering ProcessRequest of TestTaker Service");
			logger.info("<Action : " + action + " > ");
			logger.info("<Form Object : " + entityObj + " > ");
			logger.info("<Form Entity : " + entity + " > ");
			if (entity.equalsIgnoreCase(Constants.ENTITY_TESTTAKER)) {
				// Gets all testTakers
				if (action.equalsIgnoreCase(Constants.OPERATION_LIST)) {
					List<TestTaker> allTestTakerList = getAllTestTakers();
					modelMap = new HashMap<String, Object>();
					modelMap.put("allTestTakerList", allTestTakerList);
					logger.debug("Total "+allTestTakerList.size()+" users selected from TestTaker Table");
				} else {
					// get single testTaker
					if (action.equalsIgnoreCase(Constants.OPERATION_TESTTAKER)) {
						List<TestTaker> testTakerList = getTestTaker((TestTaker) entityObj);
						modelMap = new HashMap<String, Object>();
						modelMap.put("testTakerList", testTakerList);
						logger.debug("User <"+((TestTaker)entityObj).getEmailId()+">:  selected from TestTaker Table");
					}
				}
				// update testTaker by primary key
				if (action.equalsIgnoreCase(Constants.OPERATION_UPDATE)) {
								
					updateTestTaker((TestTaker) entityObj);
					logger.debug("Profile for User <"+((TestTaker)entityObj).getEmailId()+">: updated into TestTaker Table. ");
					// get all testTaker fields after updating any of fields to store
					// object in session again
					List<TestTaker> testTakerList = getTestTaker((TestTaker) entityObj);		
					modelMap = new HashMap<String, Object>();
					modelMap.put("testTakerList", testTakerList);
					logger.debug("User <"+((TestTaker)entityObj).getEmailId()+">: user selected from TestTaker Table");
				} else if (action
						.equalsIgnoreCase(Constants.OPERATION_UPDATEPASSWORD)) {
					TestTaker l =(TestTaker)entityObj;
					// Change old password with new password
					l.setPassword(l.getNewPassword());
					updatePassword(l);
					logger.debug("User <"+((TestTaker)entityObj).getEmailId()+">: updated password into TestTaker Table");
					// get all testTaker fields after updating any of fields to store
					// object in session again
					List<TestTaker> testTakerList = getTestTaker((TestTaker) entityObj);
					modelMap = new HashMap<String, Object>();
					modelMap.put("testTakerList", testTakerList);
					logger.debug("User <"+((TestTaker)entityObj).getEmailId()+">: user selected from TestTaker Table");
				}
				// delete testTaker by primary key
				if (action.equalsIgnoreCase(Constants.OPERATION_DELETE)) {

					this.deleteTestTaker((TestTaker) entityObj);
					logger.debug("User <"+((TestTaker)entityObj).getEmailId()+">: deleted from TestTaker Table");
				}
			}

			logger.debug("Exiting ProcessRequest of TestTaker Service");
			logger.info("Exiting ProcessRequest of TestTaker Service");
			
	}

	/**
	 * Get specific testTaker
	 * 
	 * @param testTaker :
	 * @return List : which contains this testTaker fields
	 */
	@SuppressWarnings("unchecked")
	private List<TestTaker> getTestTaker(TestTaker testTaker) {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		return testTakerdao.select(testTaker);
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

	@SuppressWarnings( { "unchecked", "unused" })
	private void insertTestTaker(TestTaker testTaker) {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
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
		/*testTaker.setPassword(new String(RegistrationService.encrypt(testTaker
				.getPassword())));*/
		testTakerdao.update(testTaker);
	}
	/**
	 * Updates a password
	 * 
	 * @param testTaker:
	 *            testTaker object which will be updated ( only password)
	 */
	@SuppressWarnings("unchecked")
	private void updatePassword(TestTaker testTaker) {
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		testTaker.setPassword(new String(RegistrationService.encrypt(testTaker
				.getPassword())));
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
		logger.debug("User <"+testTaker.getEmailId()+":> has try to testTaker");
		boolean testTakerStatus = false;
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		//logger.info("E-ID"+testTaker.getEmailId());
		//logger.info("pass"+testTaker.getPassword());
		
		String secret = new String(RegistrationService.encrypt(testTaker
				.getPassword()));
		testTaker.setPassword(secret);
		TestTaker tt = new TestTaker();
		tt.setEmailId(testTaker.getEmailId());
		tt.setPassword(testTaker.getPassword());

		//logger.info("E-ID=="+tt.getEmailId());
		//logger.info("pass=="+tt.getPassword());
		List<TestTaker> testTakerList = testTakerdao.select(tt);	
	//	List<TestTaker> testTakerList = testTakerdao.select(testTaker);
	//	logger.info("E-ID"+((TestTaker)testTakerList.get(0)).getEmailId() );
//		logger.info("pass"+((TestTaker)testTakerList.get(0)).getPassword() );
		
		if (testTakerList.size() > 0){
			testTakerStatus = true;
			//logger.info("User <"+((TestTaker)testTakerList.get(0)).getEmailId()+">: selected from TestTaker Table");
		}
			
		return testTakerStatus;

	}
}
