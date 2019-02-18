package com.microservices.guesswhoservice.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserQuestionId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Questions questions;

	@ManyToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
}
