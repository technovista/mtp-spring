package com.array.testprep.model;

import java.util.List;

public class CustomeTestQuestion implements ServiceDO{
	private Integer customeTestQuestionId;
	private Integer customeTestId;
	
	private String question;
	private Integer questionId;
	private Integer correctAnswerId;
	
	private Integer numOfChoice;
	private List<AnswerBank> choice; //List of choices related to 1 question
	private String selectedAnswer;
	private Integer correctAnswerSize;
	private QuestionBank questionBank;
	
	private CustomeTest customeTest;
	
	
	public CustomeTest getCustomeTest() {
		return customeTest;
	}
	public void setCustomeTest(CustomeTest customeTest) {
		this.customeTest = customeTest;
	}
	public Integer getCustomeTestQuestionId() {
		return customeTestQuestionId;
	}
	public void setCustomeTestQuestionId(Integer customTestQuestionId) {
		this.customeTestQuestionId = customTestQuestionId;
	}
	public Integer getCustomeTestId() {
		return customeTestId;
	}
	public void setCustomeTestId(Integer customeTestId) {
		this.customeTestId = customeTestId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getCorrectAnswerId() {
		return correctAnswerId;
	}
	public void setCorrectAnswerId(Integer correctAnswerId) {
		this.correctAnswerId = correctAnswerId;
	}
	public Integer getNumOfChoice() {
		return numOfChoice;
	}
	public void setNumOfChoice(Integer numOfChoice) {
		this.numOfChoice = numOfChoice;
	}
	public List<AnswerBank> getChoice() {
		return choice;
	}
	public void setChoice(List<AnswerBank> choice) {
		this.choice = choice;
	}
	public String getSelectedAnswer() {
		return selectedAnswer;
	}
	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	public Integer getCorrectAnswerSize() {
		return correctAnswerSize;
	}
	public void setCorrectAnswerSize(Integer correctAnswerSize) {
		this.correctAnswerSize = correctAnswerSize;
	}
	public QuestionBank getQuestionBank() {
		return questionBank;
	}
	public void setQuestionBank(QuestionBank questionBank) {
		this.questionBank = questionBank;
	}
	public CustomeTest getTest() {
		return customeTest;
	}
	public void setTest(CustomeTest customeTest) {
		this.customeTest = customeTest;
	}
public String toString()
{
	return "CustomeTestQuestion="+this.question+" : "+this.correctAnswerId+"  Selected Anser :: "+this.selectedAnswer;
}
	
	
}
