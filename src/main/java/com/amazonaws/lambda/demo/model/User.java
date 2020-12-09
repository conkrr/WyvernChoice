package com.amazonaws.lambda.demo.model;


public class User {

	public final String choiceId;
	public final String password;
	public final String userId;
	
	public User(String userId, String choiceId, String password) {
		this.choiceId = choiceId;
		this.password = password;
		this.userId = userId;
	}
	public User(String userId, String choiceId) {
		this.choiceId = choiceId;
		this.password = null;
		this.userId = userId;
	}
	
	
	
	
}
