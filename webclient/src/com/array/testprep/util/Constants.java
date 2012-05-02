package com.array.testprep.util;


import org.apache.log4j.Logger;

/**
 * Application Constants
 * @author mtshah
 *  
 */

public class Constants {

	protected static final Logger logger = Logger.getLogger(Constants.class);
	
	/* Service bean names */
	public static final String TESTTAKER_SERVICE = "testTakerService";
	
	public static final String TESTOBJECTIVE_SERVICE = "testObjectiveService";
	public static final String ANSWERBANK_SERVICE = "answerBankService";
	public static final String QUESTIONBANK_SERVICE = "questionBankService";

	public static final String QUESTIONANSWERRELATIONSHIP_SERVICE = "QuestionAnswerRelationshipService";
	public static final String TEST_SERVICE = "testService";
	public static final String LINK_SERVICE = "linkService";
	
	/* DAO bean names */
	public static final String TESTTAKER_DAO = "testTakerDAO";
	public static final String TESTRESULT_DAO = "testResultDAO";
	
	public static final String TESTOBJECTIVE_DAO = "testObjectiveDAO";
	public static final String FORMAT_DAO = "formatDAO";
	public static final String TEST_DAO = "testDAO";
	public static final String LINK_DAO = "linkDAO";
	public static final String RESOURCE_DAO = "resourceDAO";
	public static final String QUESTIONBANK_DAO = "questionBankDAO";
	public static final String ANSWERBANK_DAO = "answerBankDAO";
	public static final String QUESTIONANSWERRELATIONSHIP_DAO = "questionAnswerRelationshipDAO";
	public static final String CUSTOMETEST_DAO="customeTestDAO";
	public static final String CUSTOMETESTQUESTION_DAO="customeTestQuestionDAO";
	public static final String LOGIN = "Login";
	/* Entity Names */
	public static final String ENTITY_TESTTAKER = "TestTaker";
	public static final String ENTITY_TESTRESULT = "TestResult";
	public static final String ENTITY_CUSTOMETESTQUESTION = "CustomeTestQuestion";
	
	public static final String ENTITY_LOGOUT = "Logout";
	public static final String ENTITY_QUESTION = "question";
	public static final String ENTITY_QUESTIONBANK = "QuestionBank";
	
	public static final String ENTITY_ANSWER = "answer";
	public static final String ENTITY_ANSWERBANK = "AnswerBank";
	
	public static final String ENTITY_TESTOBJECTIVE = "TestObjective";
	public static final String ENTITY_QUESTIONANSWERRELATIONSHIP = "QuestionAnswerRelationship";
	public static final String ENTITY_CUSTOMETEST = "CustomeTest";
	
	public static final String ENTITY_FORMAT = "Format";
	public static final String ENTITY_TEST = "Test";
	public static final String ENTITY_PROFILE= "Profile";
	public static final String ENTITY_LINK = "Link";
	public static final String ENTITY_SEARCH= "Search";
	public static final String ENTITY_TESTTAKER_FRENCH= "ouverture de session";
	
	/* Operation Names */
	public static final String OPERATION_TESTTAKER = "login";
	public static final String OPERATION_TESTRESULT = "testResult";
	
	public static final String OPERATION_SEARCH = "search";
	public static final String OPERATION_SEARCH_TESTOBJECTIVE = "search";
	public static final String OPERATION_ADD = "add";
	public static final String OPERATION_UPDATE = "update";
	public static final String OPERATION_UPDATEPASSWORD = "updatePassword";
	
	public static final String OPERATION_DELETE = "delete";
	public static final String OPERATION_DELETE_USER = "delete";
	public static final String OPERATION_LIST = "list";
	public static final String OPERATION_SUBMIT="submit";
	public static final String OPERATION_SUBMIT_FRENCH="Soumettre";
	
	public static final String ACTION_SEARCH = "action_search";
	
	public static final String ALL_CUSTOMETEST = "AllCustomeTest";
	/* separators */
	public static final String UNDERSCORE = "_";
	
	
	/* boolean */
	public static final String ISADMIN = "y";
	public static final String ISNOTAPPROVED = "n";
	public static final String ISAPPROVED = "y";
	
	/* Request parameter */
	public static final String  ACTION_PARA="action";
	public static final String STATUS_MSG="successMsg"; 	
					
	
}
