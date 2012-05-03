package com.array.testprep.model;

@SuppressWarnings("serial")
public class AnswerBank implements ServiceDO {
	private Integer answerId;
	private String choice;
	
	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	
	
	public String toString()
	{
		return ""+this.answerId;
	}

}
/*
 CREATE TABLE `answerbank` (
  `answerId` int(10) NOT NULL,
  `choice` varchar(80) NOT NULL,
  `image` varchar(80) default NULL,
  PRIMARY KEY  (`answerId`),
  KEY `choice` (`choice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/
