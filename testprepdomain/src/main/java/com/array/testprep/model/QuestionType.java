package com.array.testprep.model;

public class QuestionType implements ServiceDO {
	private Integer questionTypeId;
	
	private Test test;
	
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public Integer getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(Integer questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	private String questionType;
	private Integer testId;
	
}
