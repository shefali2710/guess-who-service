package com.microservices.guesswhoservice.bean;

public class AuthorizeUserResponse {

	public AuthorizeUserResponse() {
	}

	private AuthorizedUser user;

	public AuthorizedUser getUser() {
		return user;
	}

	public void setUser(AuthorizedUser user) {
		this.user = user;
	}
}
