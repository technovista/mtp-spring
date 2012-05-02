package com.array.testprep.model;

/**
 * date: Sep-04-2008
 * 
 * @author technovista
 * 
 */
@SuppressWarnings("serial")
public class TestResult implements ServiceDO {
	private Integer testResultId;
	//private Integer testId;
	private Integer correctAnswerSize;

	public Integer getTestResultId() {
		return testResultId;
	}

	public void setTestResultId(Integer testResultId) {
		this.testResultId = testResultId;
	}

	public Integer getCorrectAnswerSize() {
		return correctAnswerSize;
	}

	public void setCorrectAnswerSize(Integer correctAnswerSize) {
		this.correctAnswerSize = correctAnswerSize;
	}

	

}
/**
 * CREATE TABLE `testresult` ( `testResultId` int(10) NOT NULL auto_increment,
 * `correctAnswerSize` int(10) default NULL, PRIMARY KEY (`testResultId`) )
 * ENGINE=InnoDB DEFAULT CHARSET=latin1;
 */

