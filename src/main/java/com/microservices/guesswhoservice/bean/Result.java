package com.microservices.guesswhoservice.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Result {

	@Id
	@GeneratedValue
	private int id;
	private int questionId;
	private String tokenId;
	private int userId;
	private boolean answerStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(boolean answerStatus) {
		this.answerStatus = answerStatus;
	}

}
