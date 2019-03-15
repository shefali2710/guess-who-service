package com.microservices.guesswhoservice.bean;

import java.util.Date;

public class AuthorizedUser {

	private int userID;
	private String emailID;
	private Date modifiedDate;
	private String token;
	private String userName;

	public AuthorizedUser(String emailId, String token) {
		this.emailID = emailId;
		this.token = token;
	}

	public AuthorizedUser() {

	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
