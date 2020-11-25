package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.User;

public class UserGsonCompatible {
	public boolean newUser;
	public String username;
	public String password;
	public String choiceID;
	
	public UserGsonCompatible(boolean newUser, String username, String password, String choiceID) {
		this.newUser = newUser;
		this.username = username;
		this.password = password;
		this.choiceID = choiceID;
	}
	
	public UserGsonCompatible(boolean newUser, String username, String choiceID) {
		this.newUser = newUser;
		this.username = username;
		this.password = null;
		this.choiceID = choiceID;
	}
	public UserGsonCompatible(User u) {
		this.newUser = true;
		this.username = u.name;
		this.password = u.password;
		this.choiceID = u.choiceId;
	}
	
	
	
}
