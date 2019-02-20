package com.microservices.guesswhoservice.bean;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "UserQuestion")
@Table(name = "user_question")
@AssociationOverrides({ @AssociationOverride(name = "primaryKey.user", joinColumns = @JoinColumn(name = "USER_ID")),
		@AssociationOverride(name = "primaryKey.question", joinColumns = @JoinColumn(name = "QUESTION_ID")) })
public class UserQuestion {

	private UserQuestionId primaryKey = new UserQuestionId();

	@Column(name = "answer_status")
	@JsonIgnore
	private boolean answerStatus;

	@Column(name = "token_id")
	@JsonIgnore
	private String tokenId;
	
	@Column(name = "user_generated_id")
	@JsonIgnore
	private String userGeneratedId;

	@EmbeddedId
	@JsonIgnore
	public UserQuestionId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(UserQuestionId primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Transient
	@JsonIgnore
	public User getUser() {
		return getPrimaryKey().getUser();
	}

	public void setUser(User user) {
		getPrimaryKey().setUser(user);
	}

	@Transient
	@JsonIgnore
	public Questions getQuestion() {
		return getPrimaryKey().getQuestions();
	}

	public void setQuestion(Questions questions) {
		getPrimaryKey().setQuestions(questions);
	}

	public boolean isAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(boolean answerStatus) {
		this.answerStatus = answerStatus;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserGeneratedId() {
		return userGeneratedId;
	}

	public void setUserGeneratedId(String userGeneratedId) {
		this.userGeneratedId = userGeneratedId;
	}
	

}
