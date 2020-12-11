package com.amazonaws.lambda.demo.model;


public class User {

	public final String choiceId;
	public final String password;
	public final String userId;
	
	public User(String choiceId, String password, String userId) {
		this.choiceId = choiceId;
		
		this.password = password;
		this.userId = userId;
	}
	public User(String choiceId, String userId) {
		this.choiceId = choiceId;
		
		this.password = null;
		this.userId = userId;
	}
	
	
	
	
}
