package com.microservices.guesswhoservice.bean;


public class AuthorizeUserRequest {

	private String EmailID;
	private String Token;
	
	
	public AuthorizeUserRequest(String emailId,String token) {
		
		this.EmailID = emailId;
		
		this.Token = token;
		
	}
	public String getEmailId() {
		return EmailID;
	}
	public void setEmailId(String emailId) {
		this.EmailID = emailId;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		this.Token = token;
	}
	
	
	
}
