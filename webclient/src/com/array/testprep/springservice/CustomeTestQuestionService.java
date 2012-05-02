package com.array.testprep.springservice;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.CustomeTestQuestion;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * 
 * @author mtshah
 * 
 */
@SuppressWarnings("unchecked")
public class CustomeTestQuestionService implements Service {

	private Map<String, Object> modelMap;
	
	protected final Logger logger = Logger.getLogger(CustomeTestQuestionService.class);

	/**
	 * @see com.array.testprep.springservice.Service#getModelMap()
	 */
	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	/**
	 * Finds CustomeTestQuestion details by id
	 */
	@SuppressWarnings("unchecked")
	public CustomeTestQuestion findById(Integer id) throws Exception {

		CustomeTestQuestion CustomeTestQuestion = new CustomeTestQuestion();
		CustomeTestQuestion customeTestQuestionDetails = null;
		//CustomeTestQuestion.setQuestionAnswerId(id);
		logger.info("\n\n\t\t Entering the findById of Search Service");
		List<CustomeTestQuestion> customeTestQuestionList = this.searchTestQuestion(CustomeTestQuestion);
		if (customeTestQuestionList.size() > 0) {
			logger.debug("<Size of CustomeTestQuestion list is : " + customeTestQuestionList.size() + " > ");
			customeTestQuestionDetails = (CustomeTestQuestion) customeTestQuestionList.get(0);
		}
		return customeTestQuestionDetails;
	}

	

	

	/**
	 * Entry point method for service. Delegates to business methods
	 * 
	 * @see com.array.testprep.springservice.Service#processRequest(java.lang.String, java.lang.String)
	 */

	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {

		logger.info("< entityObj = >" + entityObj);
		logger.info("< entity = >" + entity);
		logger.info("< action = >" + action);
		if (action.equalsIgnoreCase(Constants.OPERATION_LIST)) {
			CustomeTestQuestion newCustomeTestQuestion = new CustomeTestQuestion();
			newCustomeTestQuestion.setCustomeTestId(Integer.parseInt(entityObj.toString()));
			if (entity.equalsIgnoreCase("CustomeTestQuestion")) {
				List<CustomeTestQuestion> allCustomeTestQuestion = selectCustomeTestQuestion(newCustomeTestQuestion);

				//List<CustomeTestQuestion> allCustomeTestQuestion = searchTestQuestion(newCustomeTestQuestion);
				modelMap = new HashMap<String, Object>();
				logger.info("history testQuestion list size = " + allCustomeTestQuestion.size());
				modelMap.put("allCustomeTestQuestion", allCustomeTestQuestion);
			} else if (entity.equalsIgnoreCase("customeTestQuestion")) {

				

			} else {
							}

		} else if (action.equalsIgnoreCase("End")) {

		}
		logger.info("Exiting from CustomeTestQuestion Service");
	}

	private List<CustomeTestQuestion> selectCustomeTestQuestion(
			CustomeTestQuestion newCustomeTestQuestion) {
		CustomeTestQuestion cn = new CustomeTestQuestion();
		cn.setCustomeTestId(newCustomeTestQuestion.getCustomeTestId());
		ServiceDAO<CustomeTestQuestion> custometestQuestiondao = (ServiceDAO<CustomeTestQuestion>) ContextProvider
		.getApplicationContext().getBean(Constants.CUSTOMETESTQUESTION_DAO);
		
		return 	custometestQuestiondao.select(cn);
		
	}

	/**
	 * Add new CustometestQuestion
	 * 
	 * @param CustometestQuestion:
	 *            CustometestQuestionResult object which will be added
	 */

	@SuppressWarnings( { "unchecked", "unused" })
	private void insertCustomeTestQuestion(CustomeTestQuestion customeTestQuestion) {
		ServiceDAO<CustomeTestQuestion> custometestQuestiondao = (ServiceDAO<CustomeTestQuestion>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETESTQUESTION_DAO);
		logger.info("in Insert method");
		custometestQuestiondao.insert(customeTestQuestion);

		
	}

	

	

	

	

	

	/**
	 * Gets list of all TestQuestions
	 * 
	 * @return List: contains all TestQuestions
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private List<CustomeTestQuestion> getAllCustomeTestQuestions() {
		ServiceDAO<CustomeTestQuestion> customeTestQuestiondao = (ServiceDAO<CustomeTestQuestion>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETESTQUESTION_DAO);
		return customeTestQuestiondao.selectAll(Constants.ENTITY_CUSTOMETESTQUESTION);
	}

	private List<Integer> lastIndex() {
		ServiceDAO<Integer> customeTestQuestiondao = (ServiceDAO<Integer>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETESTQUESTION_DAO);
		return customeTestQuestiondao.executeHQL("select max(customeTestQuestionId) from CustomeTestQuestion");
	}

	

	/**
	 * update CustomeTestQuestion
	 * 
	 * @param CustomeTestQuestion:
	 *            CustomeTestQuestion object which will be updated
	 */

	@SuppressWarnings( { "unchecked", "unused" })
	private void updateTestQuestion(CustomeTestQuestion CustomeTestQuestion) {
		ServiceDAO<CustomeTestQuestion> customeTestQuestiondao = (ServiceDAO<CustomeTestQuestion>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		customeTestQuestiondao.update(CustomeTestQuestion);
	}

	/**
	 * Deletes a CustomeTestQuestion
	 * 
	 * @param CustomeTestQuestion :
	 *            CustomeTestQuestion object which will be deleted
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void deleteTestQuestion(CustomeTestQuestion CustomeTestQuestion) {
		ServiceDAO<CustomeTestQuestion> customeTestQuestiondao = (ServiceDAO<CustomeTestQuestion>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		customeTestQuestiondao.delete(CustomeTestQuestion);
	}

	/**
	 * Search a CustomeTestQuestion based on criteria
	 * 
	 * @param CustomeTestQuestion :
	 *            CustomeTestQuestion object to be searched
	 * 
	 * @return List: contains result of search by criteria
	 */
	/* Search a CustomeTestQuestion based on criteria */
	@SuppressWarnings("unchecked")
	private List<CustomeTestQuestion> searchTestQuestion(CustomeTestQuestion customeTestQuestion) {
		ServiceDAO<CustomeTestQuestion> customeTestQuestiondao = (ServiceDAO<CustomeTestQuestion>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETEST_DAO);

		DetachedCriteria crit = DetachedCriteria.forClass(CustomeTestQuestion.class);
		List<CustomeTestQuestion> searchCriteriaList = null;

		/* Multi Criteria Search */
		if (customeTestQuestion.getCustomeTestQuestionId() != null) {
			crit.add(Expression.eq("customeTestQuestionId", customeTestQuestion.getCustomeTestQuestionId()));
		}

		if (customeTestQuestion.getCustomeTestId() != null && customeTestQuestion.getCustomeTestId() != -1) {
			// create alias for associations
			crit.add(Expression.eq("customeTestId", customeTestQuestion.getCustomeTestId()));

			
		}

		searchCriteriaList = customeTestQuestiondao.findByCriteria(crit);
		// result of search by criteria
		logger.info("List of search result as per criteria is"
				+ searchCriteriaList + "   Size=" + searchCriteriaList.size());
		return searchCriteriaList;

	}

	/**
	 * @param obj :
	 *            Object of CustomeTestQuestion
	 */
	public boolean isValid(Object obj) {
		return false;
	}

}
