package com.array.testprep.model;

@SuppressWarnings("serial")
public class Test implements ServiceDO, Comparable<Test>{

	private Integer testId; // scjp,,
	private String testName;
	private int passingPercentage;
	private int totalNumberOfQuestion;
	private String testType;// java sap .NET

	public String getTestType() {
		return testType;
	}

	public void setTestType(String parentTestType) {
		this.testType = parentTestType;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getPassingPercentage() {
		return passingPercentage;
	}

	public void setPassingPercentage(int passingPercentage) {
		this.passingPercentage = passingPercentage;
	}

	public int getTotalNumberOfQuestion() {
		return totalNumberOfQuestion;
	}

	public void setTotalNumberOfQuestion(int totalNumberOfQuestion) {
		this.totalNumberOfQuestion = totalNumberOfQuestion;
	}
	public int compareTo(Test o) {
		
		return this.testName.compareTo(o.testName);
	} 
	public String toString()
	{
		return "TEST::"+this.testName;
	}
}
/*
 * CREATE TABLE `testtype` ( `testId` int(10) NOT NULL auto_increment,
 * `testName` varchar(80) NOT NULL, `passingPercentage` int(10) default NULL,
 * `totalNumberOfQuestion` int(10) default NULL, PRIMARY KEY (`testId`) )
 * ENGINE=InnoDB DEFAULT CHARSET=latin1;
 */
