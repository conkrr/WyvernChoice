package com.amazonaws.lambda.demo.model;


public class User {

	public final String choiceId;
	public final String username;
	public final String password;
	public final String userId;
	
	public User(String choiceId, String name, String password, String userId) {
		this.choiceId = choiceId;
		this.username = name;
		this.password = password;
		this.userId = userId;
	}
	public User(String choiceId, String name, String userId) {
		this.choiceId = choiceId;
		this.username = name;
		this.password = null;
		this.userId = userId;
	}
	
	
	
	
}
