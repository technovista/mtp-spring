package com.array.testprep.springservice;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.AnswerBank;
import com.array.testprep.model.QuestionAnswerRelationship;
import com.array.testprep.model.TestObjective;
import com.array.testprep.model.CustomeTestQuestion;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * 
 * @author mtshah
 * 
 */
@SuppressWarnings("unchecked")
public class QuestionAnswerRelationshipService implements Service {

	private Map<String, Object> modelMap;
	private List<CustomeTestQuestion> customeTestQuestions;

	protected final Logger logger = Logger
			.getLogger(QuestionAnswerRelationshipService.class);

	/**
	 * @see com.array.testprep.springservice.Service#getModelMap()
	 */
	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	/**
	 * Finds questionAnswerRelationship details by id
	 */
	@SuppressWarnings("unchecked")
	public QuestionAnswerRelationship findById(Integer id) throws Exception {

		QuestionAnswerRelationship questionAnswerRelationship = new QuestionAnswerRelationship();
		QuestionAnswerRelationship questionAnswerRelationshipDetails = null;
		questionAnswerRelationship.setQuestionAnswerId(id);
		logger.info("\n\n\t\t Entering the findById of Search Service");
		List<QuestionAnswerRelationship> questionAnswerRelationshipList = this
				.searchQuestionAnswerRelationship(questionAnswerRelationship);
		if (questionAnswerRelationshipList.size() > 0) {
			logger.debug("<Size of questionAnswerRelationship list is : "
					+ questionAnswerRelationshipList.size() + " > ");
			questionAnswerRelationshipDetails = (QuestionAnswerRelationship) questionAnswerRelationshipList
					.get(0);
		}
		return questionAnswerRelationshipDetails;
	}

	public List<QuestionAnswerRelationship> findByTestObjectiveId(String id[])
			throws Exception {
		List<QuestionAnswerRelationship> questionAnswerRelationshipList = null;
		QuestionAnswerRelationship questionAnswerRelationship = null;
		for (int i = 0; i < id.length; i++) {
			questionAnswerRelationship = new QuestionAnswerRelationship();
			questionAnswerRelationship.setTestObjectiveId(Integer
					.parseInt(id[i]));
			questionAnswerRelationshipList = this
					.searchQuestionAnswerRelationship(questionAnswerRelationship);
			if (questionAnswerRelationshipList.size() > 0) {
				logger.debug("<Size of questionAnswerRelationship list is : "
						+ questionAnswerRelationshipList.size() + " > ");
			}
		}

		return questionAnswerRelationshipList;
	}

	public List<QuestionAnswerRelationship> selectByMultiplaColumns(Integer id[]) {
		ServiceDAO<QuestionAnswerRelationship> questionAnswerRelationshipdao = (ServiceDAO<QuestionAnswerRelationship>) ContextProvider
		.getApplicationContext().getBean("questionAnswerRelationshipDAO");

		List<QuestionAnswerRelationship> questionAnswerRelationshipList = new ArrayList<QuestionAnswerRelationship>();
		//QuestionAnswerRelationship questionAnswerRelationship = null;
		String query =" from QuestionAnswerRelationship as qa where qa.testObjectiveId = ? order by questionId" ;

		for(int i=0;i<id.length;i++)
		{
			logger.info("\n\n\t\t\tIN FOR LOOP ");
			//List<QuestionAnswerRelationship>  list = questionAnswerRelationshipdao.selectByMultiplaColumns(query, id[i]);
			
			questionAnswerRelationshipList.addAll(questionAnswerRelationshipdao.selectByMultiplaColumns(query, id[i]));
			logger.info("size =  "+questionAnswerRelationshipList.size());
			//questionAnswerRelationshipList=questionAnswerRelationshipdao.selectByMultiplaColumns(query, id[i]);
		}
		//Collections.shuffle(questionAnswerRelationshipList,new Random());
		//questionAnswerRelationshipList=questionAnswerRelationshipdao.selectByMultiplaColumns(query, id);
		return questionAnswerRelationshipList;
	}

	/**
	 * Entry point method for service. Delegates to business methods
	 * 
	 * @see com.array.testprep.springservice.Service#processRequest(java.lang.String, java.lang.String)
	 */

	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {

		logger
				.info("Entering the process request of QuestionAnswerRelationship Service");
		logger.info("<Action : " + action + " > ");
		logger.info("<Form Object : " + entityObj + " > ");
		logger.info("</entity Object : " + entity + " > ");

		QuestionAnswerRelationship questionAnswerRelationship = null; // initialize
		// the
		// questionAnswerRelationship
		// object
		if(entity.equalsIgnoreCase("QuestionAnswer"))
		{
			if(action.equalsIgnoreCase(Constants.OPERATION_ADD)){
					
				/*TODO remove comment and  set code*/
					logger.info("Insert question data");
					/*questionAnswerRelationship=(QuestionAnswerRelationship)entityObj;
					
					logger.info("String = "+questionAnswerRelationship.getAnswerIds());
					StringTokenizer st = new StringTokenizer(questionAnswerRelationship.getAnswerIds(),",");
					while(st.hasMoreTokens())
					{
						questionAnswerRelationship.setAnswerId(Integer.parseInt(st.nextToken()));
							this.insertQuestionAnswerRelationship((QuestionAnswerRelationship)entityObj);
					}*/
				
			}
			else if(action.equalsIgnoreCase(Constants.OPERATION_DELETE)){
				this.deleteQuestionAnswerRelationship((QuestionAnswerRelationship)entityObj);			
				
			}
			else if(action.equalsIgnoreCase(Constants.OPERATION_UPDATE)){
				this.updateQuestionAnswerRelationship((QuestionAnswerRelationship)entityObj);
				
			}else if(action.equalsIgnoreCase(Constants.OPERATION_LIST)){
				
				List<QuestionAnswerRelationship> allQuestionBankList = this.getAllQuestionAnswerRelationships();
				modelMap = new HashMap<String, Object>();
				modelMap.put("allQuestionBankList", allQuestionBankList);
				logger.info("Total Questions = "+allQuestionBankList.size()+" users selected from TestTaker Table");
			}		
			
			
		}
		if(action.equalsIgnoreCase("start test"))
		{
			TestObjective qType = (TestObjective) entityObj;
			logger.info("\n\nQAR<<multi Col size Q TY ID============= "+qType.getTestObjectiveId());
		
			Integer id[]=new  Integer[entity.length()];
			for (int i=0 ;i<entity.length();i++)
			{
				id[i]=Integer.parseInt(""+entity.charAt(i));
			}
		//	id[0] = new Object();
		//	id[0] = entity; 
			logger.info("Before fun call id.length = "+ id.length);
			
//			this.selectByMultiplaColumns(id);
			List<QuestionAnswerRelationship> questionAnswerRelationshipList1 = this.selectByMultiplaColumns(id);
			logger.info("After fun call list qar1 size = "+ questionAnswerRelationshipList1.size());
			this.setCustomeTestQuestions(questionAnswerRelationshipList1);
			logger.info("After test fun call list .length = "+ customeTestQuestions.size());
			
			modelMap = new HashMap<String, Object>();
			modelMap.put("myTestList",
					this.getCustomeTestQuestions());
			logger.info("Correct answer = "+this.getCustomeTestQuestions().toString());
			logger.info("QAR<<multi Col size = "+questionAnswerRelationshipList1.size());
		
		}
		else if (entity
				.equalsIgnoreCase(Constants.ENTITY_QUESTIONANSWERRELATIONSHIP)) {
			// fetch the list of all questionAnswerRelationships
			if (action.equalsIgnoreCase(Constants.OPERATION_LIST)) {
				List<QuestionAnswerRelationship> questionAnswerRelationshipList = getAllQuestionAnswerRelationships();
				logger.info("<Size of questionAnswerRelationship list is : "
						+ questionAnswerRelationshipList.size() + " > ");
				modelMap = new HashMap<String, Object>();
				modelMap.put("questionAnswerRelationshipList",
						questionAnswerRelationshipList);
				logger
						.info("Total "
								+ questionAnswerRelationshipList.size()
								+ " questionAnswerRelationships selected from QuestionAnswerRelationship Table");
			} else {
				// update questionAnswerRelationship details
				if (action.equalsIgnoreCase(Constants.OPERATION_UPDATE)) {
					questionAnswerRelationship = (QuestionAnswerRelationship) entityObj;
					logger.info("Update Category ");
					this
							.updateQuestionAnswerRelationship(questionAnswerRelationship);
					logger
							.debug("QuestionAnswerRelationship <"
									+ ((QuestionAnswerRelationship) entityObj)
											.getQuestionAnswerId()
									+ ">:  updated in QuestionAnswerRelationship Table");
				}
				// delete questionAnswerRelationship and
				// questionAnswerRelationship details
				if (action.equalsIgnoreCase(Constants.OPERATION_DELETE)) {
					questionAnswerRelationship = (QuestionAnswerRelationship) entityObj;
					logger.info("Delete ");

					Properties p = new Properties();
					// String path = "";
					ApplicationContext context = ContextProvider
							.getApplicationContext();
					InputStream resourceAsStream = context.getResource(
							"WEB-INF//classes//application.properties")
							.getInputStream();
					p.load(resourceAsStream);

				}

				if (action.equalsIgnoreCase(Constants.OPERATION_SEARCH)) {
					if (entityObj != null) {
						questionAnswerRelationship = new QuestionAnswerRelationship();
						logger.info("entityobj not null = "
								+ entityObj.toString());
						logger.info("\t\t\t\tBEFORE ID = "
								+ Integer.parseInt(entityObj.toString()));
						List<QuestionAnswerRelationship> qAR = null;
						String temp;
						for (int i = 0; i < entityObj.toString().length(); i++) {
							temp = "" + entityObj.toString().charAt(i);
							i++;
							questionAnswerRelationship
									.setTestObjectiveId(Integer.parseInt(temp));
							qAR = searchQuestionAnswerRelationship(questionAnswerRelationship);

						}
						logger.info("size of questiolist = " + qAR.size());
						questionAnswerRelationship.setTestObjectiveId(Integer
								.parseInt(entityObj.toString()));
						logger.info("\t\t\t\tAFTER ID =  "
								+ questionAnswerRelationship
										.getQuestionAnswerId());

						logger.info("\t\t\t\tAFTER  After ID =  "
								+ questionAnswerRelationship
										.getQuestionAnswerId());

						List<QuestionAnswerRelationship> questionAnswerRelationshipList = searchQuestionAnswerRelationship(questionAnswerRelationship);
						logger
								.info("<Size of questionAnswerRelationship list is : "
										+ questionAnswerRelationshipList.size()
										+ " > ");
						modelMap = new HashMap<String, Object>();
						modelMap.put("questionAnswerRelationshipList",
								questionAnswerRelationshipList);
					}

				}
			}

		} else {

			if (entity.equalsIgnoreCase(Constants.ENTITY_SEARCH)) {
				logger.info("Entity is search");
				if (action.equalsIgnoreCase(Constants.OPERATION_SEARCH)) {
					questionAnswerRelationship = (QuestionAnswerRelationship) entityObj;
					List<QuestionAnswerRelationship> questionAnswerRelationshipList = searchQuestionAnswerRelationship(questionAnswerRelationship);

					logger
							.debug("<Size of questionAnswerRelationship list is : "
									+ questionAnswerRelationshipList.size()
									+ " > ");

					modelMap = new HashMap<String, Object>();
					modelMap.put("questionAnswerRelationshipList",
							questionAnswerRelationshipList);

				}
				logger.info("End of entity search");

			}
		}
		logger
				.info("Exiting the process request of QuestionAnswerRelationship Service");
	}

	/**
	 * Gets list of all questionAnswerRelationships
	 * 
	 * @return List: contains all questionAnswerRelationships
	 */
	@SuppressWarnings("unchecked")
	private List<QuestionAnswerRelationship> getAllQuestionAnswerRelationships() {
		ServiceDAO<QuestionAnswerRelationship> questionAnswerRelationshipdao = (ServiceDAO<QuestionAnswerRelationship>) ContextProvider
				.getApplicationContext().getBean(
						Constants.QUESTIONANSWERRELATIONSHIP_DAO);
		return questionAnswerRelationshipdao
				.selectAll(Constants.ENTITY_QUESTIONANSWERRELATIONSHIP);
	}

	/**
	 * update questionAnswerRelationship
	 * 
	 * @param questionAnswerRelationship:
	 *            questionAnswerRelationship object which will be updated
	 */

	@SuppressWarnings("unchecked")
	private void updateQuestionAnswerRelationship(
			QuestionAnswerRelationship questionAnswerRelationship) {
		ServiceDAO<QuestionAnswerRelationship> questionAnswerRelationshipdao = (ServiceDAO<QuestionAnswerRelationship>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		questionAnswerRelationshipdao.update(questionAnswerRelationship);
	}
	public void setCustomeTestQuestions(List<QuestionAnswerRelationship> qaList) {
		Iterator<QuestionAnswerRelationship> iter = qaList.iterator();
		QuestionAnswerRelationship qar = null;
		List<CustomeTestQuestion> customeTestQuestionList = new ArrayList<CustomeTestQuestion>();
		System.out.println("\n\t qa size =  "+qaList.size());
		
		CustomeTestQuestion customeTestQuestion = null;
		int prev = 0, curr;
		List<AnswerBank> temp = new ArrayList<AnswerBank>();
		AnswerBank aBank= null;
		int index = 0;
		int numOfChoice=0;
		while (iter.hasNext()) {
			System.out.println("In while  prev = "+prev);
			qar = iter.next();
			curr = qar.getQuestionBank().getQuestionId();

			if (prev != curr) {
				System.out.println("In if (prev!=curr) = "+curr+", prev = "+prev);
				
				if (prev != 0) {
					System.out.println("In if (prev!=0) = "+prev);
					
					customeTestQuestion.setChoice(temp);
					customeTestQuestionList.add(customeTestQuestion);
					temp = new ArrayList<AnswerBank>();
				}
				// set first object 
				logger.info("Setting first question object");
				customeTestQuestion = new CustomeTestQuestion();
				customeTestQuestion.setCustomeTestQuestionId(index);
				logger.info("After Setting index="+index);

				index++;
				customeTestQuestion.setQuestion(qar.getQuestionBank().getQuestion());
				logger.info("After Setting question="+qar.getQuestionBank().getQuestion());
						
				if(qar.getIsCorrectAnswer().equalsIgnoreCase("y")){
					customeTestQuestion.setCorrectAnswerId(qar.getAnswerBank().getAnswerId());
					logger.info("After Setting correcr ans id="+qar.getAnswerBank().getAnswerId());

				}
				
				/*TODO Date: 6-10-2009
				 * 
				 * customeTestQuestion.setCorrectAnswerId(qar.getQuestionBank().getCorrectAnswerId());
				logger.info("After Setting correcr ans id="+qar.getQuestionBank().getCorrectAnswerId());
*/
				customeTestQuestion.setQuestionId(qar.getQuestionBank().getQuestionId());
				logger.info("After Setting q id ="+qar.getQuestionBank().getQuestionId());

				// list index start from int 0; 
				customeTestQuestion.setNumOfChoice(numOfChoice);
				logger.info("After Setting no of choice ="+numOfChoice );
				
				/*TODO Date: 6-10-2009
				 * 
				 * customeTestQuestion.setNumOfChoice(qar.getQuestionBank().getNumOfChoice() - 1);
				logger.info("After Setting no of choice ="+qar.getQuestionBank().getNumOfChoice() );
				*/
				aBank = new AnswerBank();
				
				aBank.setChoice(qar.getAnswerBank().getChoice());
				aBank.setAnswerId(qar.getAnswerBank().getAnswerId());
				temp.add(aBank);
				logger.info("After Setting choice="+qar.getAnswerBank().getChoice());


			} else {
				//last object
				System.out.println("In else (prev!=curr) = "+curr+" prev = "+prev);
				aBank = new AnswerBank();
				aBank.setChoice(qar.getAnswerBank().getChoice());
				aBank.setAnswerId(qar.getAnswerBank().getAnswerId());
				temp.add(aBank);
				customeTestQuestion.setNumOfChoice(numOfChoice);
				logger.info("After Setting no of choice ="+numOfChoice );

			}
			numOfChoice++;
			prev = qar.getQuestionBank().getQuestionId();

		}// EOF WHILE
		customeTestQuestion.setChoice(temp);
		customeTestQuestionList.add(customeTestQuestion);
		this.customeTestQuestions=customeTestQuestionList;
		logger.info("Before shuffel correct ans="+customeTestQuestionList.toString());
		Collections.shuffle(customeTestQuestions,new Random());
		logger.info("After sorting list size =" + customeTestQuestions.size());
	}
	
	public List<CustomeTestQuestion> getCustomeTestQuestions() {

		return this.customeTestQuestions;

	}
	
	
	
	
	
	/**
	 * Add a questionAnswerRelationship
	 * 
	 * @param questionAnswerRelationship :
	 *            questionAnswerRelationship object which will be added
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void insertQuestionAnswerRelationship(
			QuestionAnswerRelationship questionAnswerRelationship) {
		ServiceDAO<QuestionAnswerRelationship> questionAnswerRelationshipdao = (ServiceDAO<QuestionAnswerRelationship>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
	
		questionAnswerRelationshipdao.insert(questionAnswerRelationship);
	}
	
	
	/**
	 * Deletes a questionAnswerRelationship
	 * 
	 * @param questionAnswerRelationship :
	 *            questionAnswerRelationship object which will be deleted
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void deleteQuestionAnswerRelationship(
			QuestionAnswerRelationship questionAnswerRelationship) {
		ServiceDAO<QuestionAnswerRelationship> questionAnswerRelationshipdao = (ServiceDAO<QuestionAnswerRelationship>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		questionAnswerRelationshipdao.delete(questionAnswerRelationship);
	}

	/**
	 * Search a questionAnswerRelationship based on criteria
	 * 
	 * @param questionAnswerRelationship :
	 *            questionAnswerRelationship object to be searched
	 * 
	 * @return List: contains result of search by criteria
	 */
	/* Search a questionAnswerRelationship based on criteria */
	@SuppressWarnings("unchecked")
	private List<QuestionAnswerRelationship> searchQuestionAnswerRelationship(
			QuestionAnswerRelationship questionAnswerRelationship) {

		ServiceDAO<QuestionAnswerRelationship> questionAnswerRelationshipDAO = (ServiceDAO<QuestionAnswerRelationship>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);

		DetachedCriteria crit = DetachedCriteria
				.forClass(QuestionAnswerRelationship.class);
		List<QuestionAnswerRelationship> searchCriteriaList = null;

		/* Multi Criteria Search */
		if (questionAnswerRelationship.getQuestionAnswerId() != null) {
			crit.add(Expression.eq("questionAnswerRelationshipId",
					questionAnswerRelationship.getQuestionAnswerId()));
		}

		if (questionAnswerRelationship.getTestObjectiveId() != null
				&& questionAnswerRelationship.getTestObjectiveId() != -1) {
			// create alias for associations
			crit.createAlias("testObjective", "testObjective").add(
					Restrictions.eq("testObjective.testObjectiveId",
							+questionAnswerRelationship.getTestObjectiveId()));
		}
		if (questionAnswerRelationship.getQuestionId() != null
				&& questionAnswerRelationship.getQuestionId() != -1) {
			// create alias for associations
			crit.createAlias("question", "question").add(
					Restrictions.eq("question.questionId",
							+questionAnswerRelationship.getQuestionId()));
		}
		if (questionAnswerRelationship.getAnswerId() != null
				&& questionAnswerRelationship.getAnswerId() != -1) {
			// create alias for associations
			crit.createAlias("answer", "answers").add(
					Restrictions.eq("answer.answerId",
							+questionAnswerRelationship.getAnswerId()));
		}

		searchCriteriaList = questionAnswerRelationshipDAO.findByCriteria(crit);
		// result of search by criteria
		logger.debug("List of search result as per criteria is"
				+ searchCriteriaList + "   Size=" + searchCriteriaList.size());
		return searchCriteriaList;
	}

	/**
	 * @param obj :
	 *            Object of QuestionAnswerRelationship
	 */
	public boolean isValid(Object obj) {
		return false;
	}

}
