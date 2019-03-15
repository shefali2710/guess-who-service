package com.microservices.guesswhoservice.bean;

public class ResultTable {

	private int userId;
	private int questionId;
	private String tokenId;
	private boolean answerStatus;

	public ResultTable() {
	}

	public ResultTable(int userId, int questionId, String tokenId, boolean answerStatus) {
		this.userId = userId;
		this.questionId = questionId;
		this.tokenId = tokenId;
		this.answerStatus = answerStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public boolean getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(boolean answerStatus) {
		this.answerStatus = answerStatus;
	}

}
