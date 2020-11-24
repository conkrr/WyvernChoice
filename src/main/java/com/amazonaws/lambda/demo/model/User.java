package com.amazonaws.lambda.demo.model;


public class User {

	public final String choiceId;
	public final String name;
	public final String password;
	public final String userId;
	
	public User(String choiceId, String name, String password, String userId) {
		this.choiceId = choiceId;
		this.name = name;
		this.password = password;
		this.userId = userId;
	}
	public User(String choiceId, String name, String userId) {
		this.choiceId = choiceId;
		this.name = name;
		this.password = null;
		this.userId = userId;
	}
	
	
	
	
}
