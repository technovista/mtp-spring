
	
package com.array.testprep.springservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.AnswerBank;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

public class AnswerBankService implements Service {
	private Map<String, Object> modelMap;

	protected final Logger logger = Logger.getLogger(AnswerBankService.class);

	
	public Object findById(Integer id) throws Exception {
		ServiceDAO<AnswerBank> testTakerdao = (ServiceDAO<AnswerBank>) ContextProvider
				.getApplicationContext().getBean("answerBankDAO");

		AnswerBank answerBank = new AnswerBank();
		answerBank.setAnswerId(id);

		if (testTakerdao.selectBasedOnPrimaryKey(answerBank) == null) {
			return new AnswerBank();
		} else {
			return testTakerdao.selectBasedOnPrimaryKey(answerBank);
		}
	}

	
	@SuppressWarnings("unchecked")
	public Map getModelMap() {
		return this.modelMap;
	}

	
	public boolean isValid(Object obj) {
		
		return false;
	}

	
	public void processRequest(String action, String entity, Object entityObj)
			throws Exception {
		logger.info("Entering ProcessRequest of AnswerBank Service");
		logger.info("<Action : " + action + " > ");
		logger.info("<Form Object : " + entityObj + " > ");
		logger.info("<Form Entity : " + entity + " > ");
		if (entity.equalsIgnoreCase(Constants.ENTITY_ANSWER)) {
			// Gets all testTakers
			if (action.equalsIgnoreCase(Constants.OPERATION_LIST)) {
				List<AnswerBank> allAnswerBankList = this.getAllAnswerBanks();
				modelMap = new HashMap<String, Object>();
				modelMap.put("allAnswerBankList", allAnswerBankList);
				logger.info("Total Answers = "+allAnswerBankList.size()+" users selected from TestTaker Table");
			} 
			if (action.equalsIgnoreCase(Constants.OPERATION_ADD)) {

				logger.info("Insert answer data");
				
				this.insertAnswerBank((AnswerBank)entityObj);
				logger.debug("User <" + ((AnswerBank) entityObj).getChoice()
						+ ">: Registerd and data added into TestTaker Table");
			}
			if (action.equalsIgnoreCase(Constants.OPERATION_UPDATE)) {
				
				this.updateAnswerBank((AnswerBank)entityObj);
				
				} 
			// delete answer by primary key
			if (action.equalsIgnoreCase(Constants.OPERATION_DELETE)) {

				this.deleteAnswerBank((AnswerBank)entityObj);
				logger.debug("User <"+((AnswerBank)entityObj).getChoice()+">: deleted from TestTaker Table");
			}
		}
		logger.info("Exiting form  ProcessRequest of AnswerBank Service");
	}
	/**
	 * Add new answer
	 * 
	 * @param testTaker:
	 *            testTaker object which will be added
	 */
	@SuppressWarnings("unchecked")
	private void insertAnswerBank(AnswerBank answerBank) {
		ServiceDAO<AnswerBank> answerBankDAO = (ServiceDAO<AnswerBank>) ContextProvider
				.getApplicationContext().getBean(Constants.ANSWERBANK_DAO);
		
		
		answerBankDAO.insert(answerBank);
		logger.info("Inserted answer successfully ");
	}
	
	
	/**
	 * Gets list of all answerBanks
	 * 
	 * @return List: contains all answerBanks
	 */
	@SuppressWarnings("unchecked")
	private List<AnswerBank> getAllAnswerBanks() {
		ServiceDAO<AnswerBank> answerBankdao = (ServiceDAO<AnswerBank>) ContextProvider
				.getApplicationContext().getBean(Constants.ANSWERBANK_DAO);
		return answerBankdao.selectAll(Constants.ENTITY_ANSWERBANK);
	}

	/**
	 * update answerBank
	 * 
	 * @param answerBank:
	 *            answerBank object which will be updated
	 */

	@SuppressWarnings("unchecked")
	private void updateAnswerBank(AnswerBank answerBank) {
		ServiceDAO<AnswerBank> answerBankdao = (ServiceDAO<AnswerBank>) ContextProvider
				.getApplicationContext().getBean(Constants.ANSWERBANK_DAO);
		answerBankdao.update(answerBank);
	}

	/**
	 * Deletes a answerBank
	 * 
	 * @param answerBank :
	 *            answerBank object which will be deleted
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void deleteAnswerBank(AnswerBank answerBank) {
		ServiceDAO<AnswerBank> answerBankdao = (ServiceDAO<AnswerBank>) ContextProvider
				.getApplicationContext().getBean(Constants.ANSWERBANK_DAO);
		answerBankdao.delete(answerBank);
	}
	

}
