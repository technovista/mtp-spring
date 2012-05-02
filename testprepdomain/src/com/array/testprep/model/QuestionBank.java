package com.array.testprep.model;

import java.util.List;



@SuppressWarnings("serial")
public class QuestionBank implements ServiceDO {
	private Integer questionId;
	private String question;
	
	private Integer challengeLevel;
	//private Integer correctAnswerId;
	private String reference;
	private String explanation;
	//private Integer numOfChoice;
	//private String correctChoices;
	//private Integer numOfCorrectAnswers;
	private String choices;
	private List<QuestionAnswerRelationship> questionAnswerRelationship;
	public List<QuestionAnswerRelationship> getQuestionAnswerRelationship() {
		return questionAnswerRelationship;
	}

	public void setQuestionAnswerRelationship(
			List<QuestionAnswerRelationship> questionAnswerRelationship) {
		this.questionAnswerRelationship = questionAnswerRelationship;
	}

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}



	private Integer testObjectiveId;
	private Integer questionTypeId;
	private QuestionType questionType;
	private TestObjective testObjective;
	
	
	
	public TestObjective getTestObjective() {
		return testObjective;
	}

	public void setTestObjective(TestObjective testObjective) {
		this.testObjective = testObjective;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(Integer questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public Integer getTestObjectiveId() {
		return testObjectiveId;
	}

	public void setTestObjectiveId(Integer testObjectiveId) {
		this.testObjectiveId = testObjectiveId;
	}



	private AnswerBank answerBank;
	
	public AnswerBank getAnswerBank() {
		return answerBank;
	}

	public void setAnswerBank(AnswerBank answerBank) {
		this.answerBank = answerBank;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	

	public Integer getChallengeLevel() {
		return challengeLevel;
	}

	public void setChallengeLevel(Integer challengeLevel) {
		this.challengeLevel = challengeLevel;
	}

	

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	

	
	public String toString()
	{
		return "QuestionBank:"+this.testObjectiveId+" QAR Size= "+this.getQuestionAnswerRelationship().size();
	}
	
}

