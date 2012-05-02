package com.array.testprep.model;

/**
 * date: Sep-04-2008
 * 
 * @author technovista
 * 
 */
@SuppressWarnings("serial")
public class TestTaker implements ServiceDO {

	private Integer testTakerId;
	private String emailId;
	private String password;
	private String isAdmin;
	private String firstName;
	private String lastName;
	private String newPassword;
//	private Integer testTypeId;
//	private Test testType;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getTestTakerId() {
		return testTakerId;
	}

	public void setTestTakerId(Integer testTakerId) {
		this.testTakerId = testTakerId;
	}
public String toString()
{
 return "";	
}
//	public Integer getTestTypeId() {
//		return testTypeId;
//	}
//
//	public void setTestTypeId(Integer testTypeId) {
//		this.testTypeId = testTypeId;
//	}
//
//	public Test getTestType() {
//		return testType;
//	}
//
//	public void setTestType(Test testType) {
//		this.testType = testType;
//	}

}
/*
 * 
 * 
 * CREATE TABLE `testtaker` ( `testTakerId` int(10) unsigned NOT NULL
 * auto_increment, `emailId` varchar(80) NOT NULL default '', `password`
 * varchar(80) default '', `isAdmin` varchar(10) character set latin1 collate
 * latin1_bin default '', `firstName` varchar(80) NOT NULL default '',
 * `lastName` varchar(80) default '', PRIMARY KEY (`testTakerId`), UNIQUE KEY
 * `emailId` (`emailId`)
 */