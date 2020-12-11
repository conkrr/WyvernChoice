package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.User;

public class UserGsonCompatible {
	public boolean newUser;
	public String password;
	public String choiceID;
	public String userID;
	
	public UserGsonCompatible(boolean newUser, String username, String password, String choiceID) {
		this.newUser = newUser;
		this.password = password;
		this.choiceID = choiceID;
	}
	
	public UserGsonCompatible(boolean newUser, String username, String choiceID) {
		this.newUser = newUser;
		this.password = null;
		this.choiceID = choiceID;
	}
	public UserGsonCompatible(User u) {
		this.newUser = true;
	
		this.password = u.password;
		this.choiceID = u.choiceId;
		this.userID = u.userId;
		
	}
	
	
	
}
