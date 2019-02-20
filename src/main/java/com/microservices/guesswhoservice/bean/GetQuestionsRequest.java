package com.microservices.guesswhoservice.bean;

public class GetQuestionsRequest {

	public String userId;
	public String emailId;
	public String testTokenId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTestTokenId() {
		return testTokenId;
	}
	public void setTestTokenId(String testTokenId) {
		this.testTokenId = testTokenId;
	}
}
