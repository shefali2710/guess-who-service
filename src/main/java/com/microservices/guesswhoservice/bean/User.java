package com.microservices.guesswhoservice.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "User")
@Table(name = "T_USER")
public class User {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "Email_Id")
	private String emailId;

	@Column(name = "Token")
	private String testTokenId;
	
	@Column(name = "User_generated_Id")
	private int userGeneratedId;

	public User(String emailId, String testTokenId, int userGeneratedId) {
		this.emailId = emailId;
		this.testTokenId = testTokenId;
		this.userGeneratedId = userGeneratedId;
		
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getUserGeneratedId() {
		return userGeneratedId;
	}

	public void setUserGeneratedId(int userGeneratedId) {
		this.userGeneratedId = userGeneratedId;
	}

	@Override
	public String toString() {
		return "User [emailId=" + emailId + ", testTokenId=" + testTokenId + "]";
	}

}
