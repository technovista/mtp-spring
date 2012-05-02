package com.array.testprep.springservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.AnswerBank;
import com.array.testprep.model.QuestionAnswerRelationship;
import com.array.testprep.model.CustomeTest;
import com.array.testprep.model.CustomeTestQuestion;
import com.array.testprep.model.TestResult;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * 
 * @author mtshah
 * 
 */
@SuppressWarnings("unchecked")
public class CustomeTestService implements Service {

	private Map<String, Object> modelMap;
	private List<CustomeTestQuestion> customeTestQuestions;

	protected final Logger logger = Logger.getLogger(CustomeTestService.class);

	/**
	 * @see com.array.testprep.springservice.Service#getModelMap()
	 */
	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	/**
	 * Finds CustomeTest details by id
	 */
	@SuppressWarnings("unchecked")
	public CustomeTest findById(Integer id) throws Exception {

		CustomeTest CustomeTest = new CustomeTest();
		CustomeTest TestDetails = null;
		CustomeTest.setQuestionAnswerId(id);
		logger.info("\n\n\t\t Entering the findById of Search Service");
		List<CustomeTest> customeTestList = this.searchTest(CustomeTest);
		if (customeTestList.size() > 0) {
			logger.debug("<Size of CustomeTest list is : " + customeTestList.size() + " > ");
			TestDetails = (CustomeTest) customeTestList.get(0);
		}
		return TestDetails;
	}

	public List<CustomeTest> findByTestObjectiveId(String id[]) throws Exception {
		List<CustomeTest> TestList = null;

		return TestList;
	}

	public List<CustomeTest> selectByMultiplaColumns(Integer id[]) {
		ServiceDAO<CustomeTest> Testdao = (ServiceDAO<CustomeTest>) ContextProvider
				.getApplicationContext().getBean("TestDAO");

		List<CustomeTest> TestList = null;
		CustomeTest CustomeTest = null;
		String query = " from CustomeTest as qa where qa.testObjectiveId = ?";
		TestList = Testdao.selectByMultiplaColumns(query, id);
		return TestList;
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
			logger.info("Before Casting ");
			
			CustomeTest newTest = (CustomeTest) entityObj;
			logger.info("After Casting ");
			if (entity.equalsIgnoreCase("AllCustomeTest")) {
				
				try {
					List<CustomeTest> allTest = searchTest(newTest);
					modelMap = new HashMap<String, Object>();
					logger.info("history test list size = " + allTest.size());
					modelMap.put("allTest", allTest);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (entity.equalsIgnoreCase("customeTest")) {

				logger.info("In if entity = test");

				System.out.println("test List = " + newTest.toString());

				this.insertTest(newTest);
				// get last inserted id ////
				List<Integer> testList = this.lastIndex();
				logger.info("max size  =" + testList.size());
				Integer id = testList.get(0);

				logger.info("Id is =" + id);
				// set id for insert test questions...
				newTest.setCustomeTestId(id);

				List<CustomeTestQuestion> customeTestQuestion = (List<CustomeTestQuestion>) newTest
						.getCustomeTestQuestionsList();
				System.out.println("test List = " + customeTestQuestion.toString());

				List<CustomeTestQuestion> resultList = new ArrayList<CustomeTestQuestion>();
				int correctAnswerSize = 0;
				Iterator<CustomeTestQuestion> it = customeTestQuestion.iterator();
				CustomeTestQuestion insertObject = new CustomeTestQuestion();
				insertObject.setCustomeTestId(id);
				while (it.hasNext()) {
					logger.info("In while loop ");
					CustomeTestQuestion tq = it.next();
					// insertObject.setTestId(tq.getTestId());
					insertObject.setQuestionId(tq.getQuestionId());
					tq.setCustomeTestId(id);
					if (tq.getSelectedAnswer() == null) {
						tq.setSelectedAnswer("false");
					} else if (tq.getCorrectAnswerId() == Integer.parseInt(tq
							.getSelectedAnswer())) {
						logger.info("In if correct answer = selected ans");
						correctAnswerSize++;
						tq.setSelectedAnswer("true");
					} else {
						logger.info("In else correct answer != selected ans");
						tq.setSelectedAnswer("false");
					}
					insertCustomeTestQuestion(insertObject); // insert all question
					// for 1 test
					resultList.add(tq);

				}// EOF While
				TestResult testResult = new TestResult();
				testResult.setCorrectAnswerSize(correctAnswerSize);

				this.insertTestResult(testResult);
				newTest.setTestResultId(this.lastResultIndex().get(0));
				this.updateTest(newTest);
				it = resultList.iterator();
				while (it.hasNext()) {
					logger
							.info("\n\n\tresultobject  = "
									+ it.next().toString());
				}
				modelMap = new HashMap<String, Object>();
				logger.info("result list size = " + resultList.size());
				modelMap.put("resultList", resultList);

			} else {
				List<CustomeTestQuestion> qaList = (List<CustomeTestQuestion>) entityObj;

				logger.info("Entering into CustomeTest Service");
				// this.setCustomeTestQuestions(qaList);
				modelMap = new HashMap<String, Object>();

				this.modelMap.put("myTestList", qaList);
			}

		} else if (action.equalsIgnoreCase("End")) {

		}
		logger.info("Exiting from CustomeTest Service");
	}

	/**
	 * Add new test
	 * 
	 * @param test:
	 *            testResult object which will be added
	 */

	@SuppressWarnings( { "unchecked", "unused" })
	private void insertTest(CustomeTest customeTest) {
		ServiceDAO<CustomeTest> testdao = (ServiceDAO<CustomeTest>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETEST_DAO);
		logger.info("in Insert method");
		testdao.insert(customeTest);

		// logger.info("After insert test row = "+newTest.getTestId());
	}

	/**
	 * Add new testResult
	 * 
	 * @param test:
	 *            testResult object which will be added
	 */

	@SuppressWarnings( { "unchecked", "unused" })
	private void insertTestResult(TestResult testResult) {
		ServiceDAO<TestResult> testdao = (ServiceDAO<TestResult>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTRESULT_DAO);

		testdao.insert(testResult);

		// logger.info("After insert test row = "+newTest.getTestId());
	}

	@SuppressWarnings( { "unchecked", "unused" })
	private void insertCustomeTestQuestion(CustomeTestQuestion customeTestQuestion) {
		ServiceDAO<CustomeTestQuestion> customeTestQuestiondao = (ServiceDAO<CustomeTestQuestion>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETESTQUESTION_DAO);
		logger.info("in Insert method");
		customeTestQuestiondao.insert(customeTestQuestion);

		// logger.info("After insert test row = "+newTest.getTestId());
	}

	public void setCustomeTestQuestions(List<QuestionAnswerRelationship> qaList) {
		logger.info("In set CustomeTest Question method");
		Iterator<QuestionAnswerRelationship> iter = qaList.iterator();
		QuestionAnswerRelationship qar = null;
		List<CustomeTestQuestion> customeTestQuestionList = new ArrayList<CustomeTestQuestion>();
		System.out.println("\n\t qa size = " + qaList.size());

		CustomeTestQuestion customeTestQuestion = null;
		int prev = 0, curr;
		List<AnswerBank> temp = new ArrayList<AnswerBank>();
		AnswerBank aBank = null;
		int index = 0;
		while (iter.hasNext()) {
			logger.info("In set WHILE");
			System.out.println("In while prev = " + prev);
			qar = iter.next();
			logger.info("before curr");
			curr = qar.getQuestionBank().getQuestionId();
			logger.info("After curr");
			if (prev != curr) {
				System.out.println("In if (prev!=curr) = " + curr + ", prev ="
						+ prev);

				if (prev != 0) {
					System.out.println("In if (prev!=0) = " + prev);

					customeTestQuestion.setChoice(temp);
					customeTestQuestionList.add(customeTestQuestion);
					temp = new ArrayList<AnswerBank>();
				}
				customeTestQuestion = new CustomeTestQuestion();
				customeTestQuestion.setCustomeTestQuestionId(index);
				logger.info("Aftr Set index");
				index++;
				customeTestQuestion.setQuestion(qar.getQuestionBank().getQuestion());
				// list index start from int 0;
				/*customeTestQuestion.setNumOfChoice(qar.getQuestionBank()
						.getNumOfChoice() - 1);*/
				aBank = new AnswerBank();
				aBank.setChoice(qar.getAnswerBank().getChoice());
				temp.add(aBank);

			} else {
				// System.out.println("In else (prev!=curr) = "+curr+" prev =
				// "+prev);
				aBank = new AnswerBank();
				aBank.setChoice(qar.getAnswerBank().getChoice());
				temp.add(aBank);

			}

			prev = qar.getQuestionBank().getQuestionId();

		}// EOF WHILE
		customeTestQuestion.setChoice(temp);
		customeTestQuestionList.add(customeTestQuestion);
		this.customeTestQuestions = customeTestQuestionList;
		logger.info("After sorting list size =" + customeTestQuestions.size());
	}

	public List<CustomeTestQuestion> getCustomeTestQuestions() {

		return this.customeTestQuestions;

	}

	/**
	 * Gets list of all Tests
	 * 
	 * @return List: contains all Tests
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private List<CustomeTest> getAllTests() {
		ServiceDAO<CustomeTest> Testdao = (ServiceDAO<CustomeTest>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETEST_DAO);
		return Testdao.selectAll(Constants.ENTITY_CUSTOMETEST);
	}

	private List<Integer> lastIndex() {
		ServiceDAO<Integer> Testdao = (ServiceDAO<Integer>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETEST_DAO);
		return Testdao.executeHQL("select max(customeTestId) from CustomeTest");
	}

	private List<Integer> lastResultIndex() {
		ServiceDAO<Integer> TestResultdao = (ServiceDAO<Integer>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTRESULT_DAO);
		return TestResultdao
				.executeHQL("select max(testResultId) from TestResult");
	}

	/**
	 * update CustomeTest
	 * 
	 * @param CustomeTest:
	 *            CustomeTest object which will be updated
	 */

	@SuppressWarnings( { "unchecked", "unused" })
	private void updateTest(CustomeTest CustomeTest) {
		ServiceDAO<CustomeTest> Testdao = (ServiceDAO<CustomeTest>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		Testdao.update(CustomeTest);
	}

	/**
	 * Deletes a CustomeTest
	 * 
	 * @param CustomeTest :
	 *            CustomeTest object which will be deleted
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void deleteTest(CustomeTest CustomeTest) {
		ServiceDAO<CustomeTest> Testdao = (ServiceDAO<CustomeTest>) ContextProvider
				.getApplicationContext().getBean(Constants.TESTOBJECTIVE_DAO);
		Testdao.delete(CustomeTest);
	}

	/**
	 * Search a CustomeTest based on criteria
	 * 
	 * @param CustomeTest :
	 *            CustomeTest object to be searched
	 * 
	 * @return List: contains result of search by criteria
	 */
	/* Search a CustomeTest based on criteria */
	@SuppressWarnings("unchecked")
	private List<CustomeTest> searchTest(CustomeTest customeTest) {
		ServiceDAO<CustomeTest> custometestDAO = (ServiceDAO<CustomeTest>) ContextProvider
				.getApplicationContext().getBean(Constants.CUSTOMETEST_DAO);

		DetachedCriteria crit = DetachedCriteria.forClass(CustomeTest.class);
		List<CustomeTest> searchCriteriaList = null;

		/* Multi Criteria Search */
		if (customeTest.getCustomeTestId() != null && customeTest.getCustomeTestId() != -1) {
			crit.add(Expression.eq("customeTestId", customeTest.getCustomeTestId()));
		}

		if (customeTest.getTestTakerId() != null && customeTest.getTestTakerId() != -1) {
			// create alias for associations
			crit.add(Expression.eq("testTakerId", customeTest.getTestTakerId()));

			
		}

		searchCriteriaList = custometestDAO.findByCriteria(crit);
		// result of search by criteria
		logger.debug("List of search result as per criteria is"
				+ searchCriteriaList + "   Size=" + searchCriteriaList.size());
		return searchCriteriaList;

	}

	/**
	 * @param obj :
	 *            Object of CustomeTest
	 */
	public boolean isValid(Object obj) {
		return false;
	}

}
