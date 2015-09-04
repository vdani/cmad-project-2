
package com.cisco.myapp;

import java.util.Date;

public class Question {

	private Integer qid;
	private Integer uid;
	private String question;
	
	public Integer getqid() {
		return qid;
	}
	
	public Integer getuid() {
		return uid;
	}
	
	public void setuid(Integer uId) {
		this.uid = uId;	
	}
	
	public void setqid(Integer qId) {
		this.qid = qId;
	}
	
	public String getquestion() {
		return question;
	}
	
	public void setquestion(String question) {
		this.question = question;
	}
}
