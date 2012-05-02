package com.array.testprep.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CustomeTest implements ServiceDO {
	private Integer customeTestId;
	private Integer questionAnswerId;
	private Integer correctAnswerSize;
	private Integer testTakerId;
	private QuestionAnswerRelationship questionAnswerRelationship;
	private TestResult testResult;
	private Integer testResultId;
	private List<CustomeTestQuestion> customeTestQuestionsList;
	private CustomeTestQuestion customeTestQuestion;
	private String selectedAnswer ;
	private TestTaker testTaker;
 
	public Integer getCustomeTestId() {
		return customeTestId;
	}

	public void setCustomeTestId(Integer customeTestId) {
		this.customeTestId = customeTestId;
	}

	public int getQuestionAnswerId() {
		return questionAnswerId;
	}

	public void setQuestionAnswerId(int questionAnswerId) {
		this.questionAnswerId = questionAnswerId;
	}

	

	public QuestionAnswerRelationship getQuestionAnswerRelationship() {
		return questionAnswerRelationship;
	}

	public void setQuestionAnswerRelationship(
			QuestionAnswerRelationship questionAnswerRelationship) {
		this.questionAnswerRelationship = questionAnswerRelationship;
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	

	

	
	public String toString()
	{
		
		return "CUSTOMETEST: "+this.customeTestId+"Test Taker ID = "+this.testTakerId;
	}

	public CustomeTestQuestion getCustomeTestQuestion() {
		return customeTestQuestion;
	}

	public void setCustomeTestQuestion(CustomeTestQuestion customeTestQuestion) {
		this.customeTestQuestion = customeTestQuestion;
	}

	public Integer getTestTakerId() {
		return testTakerId;
	}

	public void setTestTakerId(Integer testTakerId) {
		this.testTakerId = testTakerId;
	}

	public List<CustomeTestQuestion> getCustomeTestQuestionsList() {
		return customeTestQuestionsList;
	}

	public void setCustomeTestQuestionsList(List<CustomeTestQuestion> custometestQuestionsList) {
		this.customeTestQuestionsList = custometestQuestionsList;
	}

	public Integer getCorrectAnswerSize() {
		return correctAnswerSize;
	}

	public void setCorrectAnswerSize(Integer correctAnswerSize) {
		this.correctAnswerSize = correctAnswerSize;
	}

	public TestTaker getTestTaker() {
		return testTaker;
	}

	public void setTestTaker(TestTaker testTaker) {
		this.testTaker = testTaker;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public Integer getTestResultId() {
		return testResultId;
	}

	public void setTestResultId(Integer testResultId) {
		this.testResultId = testResultId;
	}
	
	
	

}
/*
 * CREATE TABLE `test` ( `testId` int(10) NOT NULL, `questionAnswerId` int(10)
 * NOT NULL, `testResultId` int(10) NOT NULL, PRIMARY KEY (`testId`), KEY
 * `fk_qansRelationshipId` (`questionAnswerId`), KEY `fk_testResultId`
 * (`testResultId`), CONSTRAINT `fk_qansRelationshipId` FOREIGN KEY
 * (`questionAnswerId`) REFERENCES `questionanswerrelationship`
 * (`questionAnswerId`) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT
 * `fk_testResultId` FOREIGN KEY (`testResultId`) REFERENCES `testresult`
 * (`testResultId`) ON DELETE CASCADE ON UPDATE CASCADE ) ENGINE=InnoDB DEFAULT
 * CHARSET=latin1;
 */