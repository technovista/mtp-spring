package com.array.testprep.model;

import java.util.List;

@SuppressWarnings("serial")
public class TestObjective implements ServiceDO {
	private Integer testObjectiveId;
	private String testObjectiveType;
	private Integer testId;
	private Test test;
	private List<Integer> selectedIds;

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestObjectiveType() {
		return testObjectiveType;
	}

	public void setTestObjectiveType(String testObjective) {
		this.testObjectiveType = testObjective;
	}

	public Integer getTestObjectiveId() {
		return testObjectiveId;
	}

	public void setTestObjectiveId(Integer testObjectiveId) {
		this.testObjectiveId = testObjectiveId;
	}
	public String toString()
	{
		return ""+this.testObjectiveId+ "- "+this.testObjectiveType;
	}

	public List<Integer> getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(List<Integer> selectedIds) {
		this.selectedIds = selectedIds;
	}
}
/*
 * CREATE TABLE `questiontype` ( `typeId` int(10) NOT NULL, `type` varchar(80)
 * default NULL, `testTypeId` int(10) default NULL, PRIMARY KEY (`typeId`), KEY
 * `testTypeId` (`testTypeId`), CONSTRAINT `testTypeId` FOREIGN KEY
 * (`testTypeId`) REFERENCES `testtype` (`testTypeId`) ON DELETE CASCADE ON
 * UPDATE CASCADE ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 * 
 */
