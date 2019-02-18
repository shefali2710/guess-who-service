package com.microservices.guesswhoservice.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "primaryKey.user",cascade = CascadeType.ALL)
	private Set<UserQuestion> userQuestion = new HashSet<UserQuestion>();

	public User(String emailId, String testTokenId) {
		this.emailId = emailId;
		this.testTokenId = testTokenId;
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
	
	public void addQuestions(UserQuestion question) {
		this.userQuestion.add(question);
	}
	
	public void addUserQuestiom(UserQuestion userQuestion) {
        this.userQuestion.add(userQuestion);
    } 

	public Set<UserQuestion> getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(Set<UserQuestion> userQuestion) {
		this.userQuestion = userQuestion;
	}

	@Override
	public String toString() {
		return "User [emailId=" + emailId + ", testTokenId=" + testTokenId + "]";
	}

}
