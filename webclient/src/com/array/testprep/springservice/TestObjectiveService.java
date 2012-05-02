package com.array.testprep.springservice;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;



import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.TestObjective;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * 
 * @author mtshah
 * 
 */
@SuppressWarnings("unchecked")
public class TestObjectiveService implements Service {

	private Map<String, Object> modelMap;

	protected final Logger logger = Logger.getLogger(TestObjectiveService.class);

	/**
	 * @see com.array.testprep.springservice.Service#getModelMap()
	 */
	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	/**
	 * Finds testObjective details by id
	 */
	@SuppressWarnings("unchecked")
	public TestObjective findById(Integer id)throws Exception  {

		TestObjective testObjective = new TestObjective();
		TestObjective testObjectiveDetails = null;
		testObjective.setTestObjectiveId(id);
		logger.info("\n\n\t\t Entering the findById of Search Service");
		List<TestObjective> testObjectiveList = this.searchTestObjective(testObjective);
		if (testObjectiveList.size() > 0) {
			logger.debug("<Size of testObjective list is : " + testObjectiveList.size() + " > ");
			testObjectiveDetails = (TestObjective) testObjectiveList.get(0);
		}
		return testObjectiveDetails;
	}

	/**
	 * Entry point method for service. Delegates to business methods
	 * 
	 * @see com.array.testprep.springservice.Service#processRequest(java.lang.String, java.lang.String)
	 */

	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {

		logger.info("Entering the process request of TestObjective Service");
		logger.info("<Action : " + action + " > ");
		logger.info("<Form Object : " + entityObj + " > ");
		logger.info("</entity Object : " + entity + " > ");

		TestObjective testObjective = null; // initialize the testObjective object

		if (entity.equalsIgnoreCase(Constants.ENTITY_TESTOBJECTIVE)) {
			// fetch the list of all testObjectives
			if (action.equalsIgnoreCase(Constants.OPERATION_LIST)) {
				List<TestObjective> testObjectiveList = getAllTestObjectives();
				logger.info("<Size of testObjective list is : " + testObjectiveList.size()
						+ " > ");
				modelMap = new HashMap<String, Object>();
				modelMap.put("objectiveList", testObjectiveList);
				logger.info("Total "+testObjectiveList.size()+" testObjectives selected from TestObjective Table");
			} else {
				// update testObjective details
				if (action.equalsIgnoreCase(Constants.OPERATION_UPDATE)) {
					testObjective = (TestObjective) entityObj;
					logger.debug("Update Category ");
					this.updateTestObjective(testObjective);
					logger.debug("TestObjective <"+((TestObjective)entityObj).getTestObjectiveType()+">:  updated in TestObjective Table");
				}
				// delete testObjective and testObjective details
				if (action.equalsIgnoreCase(Constants.OPERATION_DELETE)) {
					testObjective = (TestObjective) entityObj;
					logger.debug("Delete ");

					Properties p = new Properties();
//					String path = "";
					ApplicationContext context = ContextProvider
							.getApplicationContext();
					InputStream resourceAsStream = context.getResource(
							"WEB-INF//classes//application.properties")
							.getInputStream();
					p.load(resourceAsStream);

				}
				if (action.equalsIgnoreCase(Constants.OPERATION_ADD)) {
					
				logger.info("In Insert");
				testObjective = (TestObjective) entityObj;				
				this.insertTestObjective(testObjective);
				
				}
				if (action.equalsIgnoreCase(Constants.OPERATION_SEARCH)) {
					if (entityObj != null) {
						testObjective = new TestObjective();
						logger.debug("entityobj not null = "+entityObj.toString());
						logger.debug("\t\t\t\tBEFORE ID = "+Integer.parseInt(entityObj
								.toString()));
						
					
						testObjective.setTestId(Integer.parseInt(entityObj
									.toString()));
						logger.debug("\t\t\t\tAFTER ID =  "+testObjective.getTestObjectiveId());
						
						testObjective.setTestId(1
								);
						logger.debug("\t\t\t\tAFTER  After ID =  "+testObjective.getTestObjectiveId());
						
						List<TestObjective> testObjectiveList = searchTestObjective(testObjective);
						logger.debug("<Size of testObjective list is : "
								+ testObjectiveList.size() + " > ");
						modelMap = new HashMap<String, Object>();
						modelMap.put("testObjectiveList", testObjectiveList);
					}

				}
			}

		} else {

			if (entity.equalsIgnoreCase(Constants.ENTITY_SEARCH)) {
				logger.info("Entity is search");
				if (action.equalsIgnoreCase(Constants.OPERATION_SEARCH)) {
					testObjective = (TestObjective) entityObj;
					List<TestObjective> testObjectiveList = searchTestObjective(testObjective);

					logger.debug("<Size of testObjective list is : " + testObjectiveList.size()
							+ " > ");

					modelMap = new HashMap<String, Object>();
					modelMap.put("testObjectiveList", testObjectiveList);

				}
				logger.info("End of entity search");

			}
		}
		logger.info("Exiting the process request of TestObjective Service");
	}

	private void insertTestObjective(TestObjective testObjective) {
		ServiceDAO<TestObjective> testObjectivedao = (ServiceDAO<TestObjective>) ContextProvider
		.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		testObjectivedao.insert(testObjective);
		
	}

	/**
	 * Gets list of all testObjectives
	 * 
	 * @return List: contains all testObjectives
	 */
	@SuppressWarnings("unchecked")
	private List<TestObjective> getAllTestObjectives() {
		ServiceDAO<TestObjective> testObjectivedao = (ServiceDAO<TestObjective>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		return testObjectivedao.selectAll(Constants.ENTITY_TESTOBJECTIVE);
	}

	/**
	 * update testObjective
	 * 
	 * @param testObjective:
	 *            testObjective object which will be updated
	 */

	@SuppressWarnings("unchecked")
	private void updateTestObjective(TestObjective testObjective) {
		ServiceDAO<TestObjective> testObjectivedao = (ServiceDAO<TestObjective>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		testObjectivedao.update(testObjective);
	}

	/**
	 * Deletes a testObjective
	 * 
	 * @param testObjective :
	 *            testObjective object which will be deleted
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void deleteTestObjective(TestObjective testObjective) {
		ServiceDAO<TestObjective> testObjectivedao = (ServiceDAO<TestObjective>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		testObjectivedao.delete(testObjective);
	}

	/**
	 * Search a testObjective based on criteria
	 * 
	 * @param testObjective :
	 *            testObjective object to be searched
	 * 
	 * @return List: contains result of search by criteria
	 */
	/* Search a testObjective based on criteria */
	@SuppressWarnings("unchecked")
	private List<TestObjective> searchTestObjective(TestObjective testObjective) {

		ServiceDAO<TestObjective> testObjectiveDAO = (ServiceDAO<TestObjective>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);

		DetachedCriteria crit = DetachedCriteria.forClass(TestObjective.class);
		List<TestObjective> searchCriteriaList = null;

		/* Multi Criteria Search */
		if (testObjective.getTestObjectiveId() != null) {
			crit.add(Expression.eq("testObjectiveId", testObjective.getTestObjectiveId()));
		}

		if (testObjective.getTestObjectiveType() != null) {
			crit.add(Restrictions.like("testObjective", "%" + testObjective.getTestObjectiveType() + "%"));
		}

		if (testObjective.getTestId() != null && testObjective.getTestId() != -1) {
			// create alias for associations
			crit.createAlias("test", "test").add(
					Restrictions.eq("test.testId", +testObjective
							.getTestId()));
		}

		
		searchCriteriaList = testObjectiveDAO.findByCriteria(crit);
		// result of search by criteria
		logger.debug("List of search result as per criteria is"
				+ searchCriteriaList + "   Size=" + searchCriteriaList.size());
		return searchCriteriaList;
	}

	/**
	 * @param obj :
	 *            Object of TestObjective
	 */
	public boolean isValid(Object obj) {
		return false;
	}

}
