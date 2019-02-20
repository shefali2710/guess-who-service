package com.microservices.guesswhoservice.bean;

import java.util.List;


public class UserQuestionAnswer {

	
	private int userId;
	private String tokenId;
	private List<Integer> questionId;
	private boolean answerStatus;
	
	public UserQuestionAnswer(int userId, String tokenId, List<Integer> questionId, boolean answerStatus) {
		this.userId = userId;
		this.tokenId = tokenId;
		this.questionId = questionId;
		this.answerStatus = answerStatus;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public List<Integer> getQuestionId() {
		return questionId;
	}
	public void setQuestionId(List<Integer> questionId) {
		this.questionId = questionId;
	}
	public boolean getAnswerStatus() {
		return answerStatus;
	}
	public void setAnswerStatus(boolean answerStatus) {
		this.answerStatus = answerStatus;
	}
	
	
	
}
