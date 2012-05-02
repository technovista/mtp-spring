package com.array.testprep.springservice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.AnswerBank;
import com.array.testprep.model.QuestionAnswerRelationship;
import com.array.testprep.model.QuestionBank;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;
import com.array.testprep.util.Convrter;

public class QuestionBankService implements Service {
	private Map<String, Object> modelMap;

	protected final Logger logger = Logger.getLogger(QuestionBankService.class);

	@SuppressWarnings({ "unchecked", "unchecked" })
	public Object findById(Integer id) throws Exception {
		ServiceDAO<QuestionBank> questionBankdao = (ServiceDAO<QuestionBank>) ContextProvider
				.getApplicationContext().getBean("questionBankDAO");

		QuestionBank questionBank = new QuestionBank();
		questionBank.setQuestionId(id);

		if (questionBankdao.selectBasedOnPrimaryKey(questionBank) == null) {
			return new QuestionBank();
		} else {
			logger.info("qustion Bank               Obbbbbbbject = "+questionBankdao.selectBasedOnPrimaryKey(questionBank));
			return questionBankdao.selectBasedOnPrimaryKey(questionBank);
		}
	}

	
	@SuppressWarnings("unchecked")
	public Map getModelMap() {
		return this.modelMap;
	}

	
	public boolean isValid(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {
		logger.info("Entering ProcessRequest of QuestionBank Service");
		logger.info("<Action : " + action + " > ");
		logger.info("<Form Object : " + entityObj + " > ");
		logger.info("<Form Entity : " + entity + " > ");
		if (entity.equalsIgnoreCase(Constants.ENTITY_QUESTION)) {
			// Gets all testTakers
			if (action.equalsIgnoreCase(Constants.OPERATION_LIST)) {
				List<QuestionBank> allQuestionBankList = this.getAllQuestionBanks();
				modelMap = new HashMap<String, Object>();
				modelMap.put("allQuestionBankList", allQuestionBankList);
				logger.info("Total Questions = "+allQuestionBankList.size()+" users selected from TestTaker Table");
			} 
			if (action.equalsIgnoreCase(Constants.OPERATION_ADD)) {

				logger.info("Insert question data");
				
				this.insertQuestionBank((QuestionBank)entityObj);
				logger.debug("User <" + ((QuestionBank) entityObj).getQuestion()
						+ ">: Registerd and data added into TestTaker Table");
			}
			if (action.equalsIgnoreCase(Constants.OPERATION_UPDATE)) {
				
				this.updateQuestionBank((QuestionBank)entityObj);
				
				} 
			// delete question by primary key
			if (action.equalsIgnoreCase(Constants.OPERATION_DELETE)) {

				this.deleteQuestionBank((QuestionBank)entityObj);
				logger.debug("User <"+((QuestionBank)entityObj).getQuestion()+">: deleted from TestTaker Table");
			}
		}
		logger.info("Exiting form  ProcessRequest of QuestionBank Service");
	}
	/**
	 * Add new question
	 * 
	 * @param testTaker:
	 *            testTaker object which will be added
	 */
	@SuppressWarnings("unchecked")
	private void insertQuestionBank(QuestionBank questionBank) {
		ServiceDAO<QuestionBank> questionBankDAO = (ServiceDAO<QuestionBank>) ContextProvider
				.getApplicationContext().getBean(Constants.QUESTIONBANK_DAO);
		
		
		questionBankDAO.insert(questionBank);
		logger.info("Inserted question successfully ");
		
		
		ServiceDAO<AnswerBank> answerBankDAO = (ServiceDAO<AnswerBank>) ContextProvider
		.getApplicationContext().getBean(Constants.ANSWERBANK_DAO);	

List<Integer> maxIdList= this.lastIndex();
Integer maxId = maxIdList.get(0);
logger.info("Entered choices "+questionBank.getChoices());
Convrter con =   new Convrter();
List<String> choices = con.StringToArray(questionBank.getChoices(), "|");
int numOfChoices = choices.size();
AnswerBank answerBank = new AnswerBank();
String answerIds=""; 
Iterator it = choices.iterator();
int i= maxId;
while(it.hasNext()){
	 answerBank = new AnswerBank();
	 i++;
	 answerBank.setAnswerId(i);
	 answerBank.setChoice(it.next().toString());
	 logger.info("Answer Added "+ i);
	 answerBankDAO.insert(answerBank);	
	 answerIds+=i+",";
	
}

//   add Question



//questionBank.setChoices(answerIds);
//questionBank.setCorrectChoices(answerIds);
//questionBank.setNumOfCorrectAnswers(2);


//add questionAnswer 
ServiceDAO<Integer> questionDAO = (ServiceDAO<Integer>) ContextProvider
.getApplicationContext().getBean(Constants.QUESTIONBANK_DAO);
List<Integer> questionList = questionDAO.executeHQL("select max(questionId) from QuestionBank");
Integer questionId = questionList.get(0);


ServiceDAO<QuestionAnswerRelationship> questionAnswerRelationshipDAO = (ServiceDAO<QuestionAnswerRelationship>) ContextProvider
.getApplicationContext().getBean(Constants.QUESTIONANSWERRELATIONSHIP_DAO);
QuestionAnswerRelationship questionAnswerRelationship =null;
for(int j=0;j<numOfChoices;j++)
{
	questionAnswerRelationship = new QuestionAnswerRelationship();
	// set answerID 
	questionAnswerRelationship.setAnswerId(++maxId);
	//set question Id
	questionAnswerRelationship.setQuestionId(questionId);
	//set test Objective id 
	questionAnswerRelationship.setTestObjectiveId(questionBank.getTestObjectiveId());
	// set correctAnswerOrder
	//set isCorrectAnswer 
	questionAnswerRelationship.setIsCorrectAnswer("y");
	//set ChoiceDisplayOrder
	questionAnswerRelationshipDAO.insert(questionAnswerRelationship);
	
}


logger.info("Inserted questionAnswer successfully ");
	}
	
	
	
	private List<Integer> lastIndex() {
		ServiceDAO<Integer> answerBankDAO = (ServiceDAO<Integer>) ContextProvider
		.getApplicationContext().getBean(Constants.ANSWERBANK_DAO);		
		
		return answerBankDAO.executeHQL("select max(answerId) from AnswerBank");
	}

	/**
	 * Gets list of all testObjectives
	 * 
	 * @return List: contains all testObjectives
	 */
	@SuppressWarnings("unchecked")
	private List<QuestionBank> getAllQuestionBanks() {
		ServiceDAO<QuestionBank> questionBankdao = (ServiceDAO<QuestionBank>) ContextProvider
				.getApplicationContext().getBean(Constants.QUESTIONBANK_DAO);
		return questionBankdao.selectAll(Constants.ENTITY_QUESTIONBANK);
	}

	/**
	 * update testObjective
	 * 
	 * @param testObjective:
	 *            testObjective object which will be updated
	 */

	@SuppressWarnings("unchecked")
	private void updateQuestionBank(QuestionBank testObjective) {
		ServiceDAO<QuestionBank> questionBankdao = (ServiceDAO<QuestionBank>) ContextProvider
				.getApplicationContext().getBean(Constants.QUESTIONBANK_DAO);
		questionBankdao.update(testObjective);
	}

	/**
	 * Deletes a testObjective
	 * 
	 * @param testObjective :
	 *            testObjective object which will be deleted
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void deleteQuestionBank(QuestionBank testObjective) {
		ServiceDAO<QuestionBank> questionBankdao = (ServiceDAO<QuestionBank>) ContextProvider
				.getApplicationContext().getBean(Constants.QUESTIONBANK_DAO);
		questionBankdao.delete(testObjective);
	}
	

}
