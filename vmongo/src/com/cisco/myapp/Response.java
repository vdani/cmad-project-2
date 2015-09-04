package com.cisco.myapp;

import java.util.Date;

public class Response {

	//This enum should be used by different classes to fetch this DO based on conditions
	public enum FetchAttributes {
		questionId, createdOn
	}

	public enum FormAttributes {
		response, responseList
	}

	private int rid;
	private int qid;
	private int uid;
	private String response;
	

	public int getrid() {
		return rid;
	}

	public int getqid() {
		return qid;
	}
	
	public int getuid() {
		return uid;
	}
	
	public void setuid(int uId) {
		this.uid = uId;
	}
	
	public void setrid(int rId) {
		this.rid = rId;
	}

	public void setqid(int qId) {
		this.qid = qId;
	}
	
	
	public String getresponse() {
		return response;
	}
	
	public void setresponse(String response) {
		this.response = response;
	}
	
}
