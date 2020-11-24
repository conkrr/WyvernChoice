package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.User;

public class UserGsonCompatible {
	public boolean newUser;
	public String loggedInUser;
	public String password;
	public String choiceID;
	
	public UserGsonCompatible(boolean newUser, String loggedInUser, String password, String choiceID) {
		this.newUser = newUser;
		this.loggedInUser = loggedInUser;
		this.password = password;
		this.choiceID = choiceID;
	}
	
	public UserGsonCompatible(boolean newUser, String loggedInUser, String choiceID) {
		this.newUser = newUser;
		this.loggedInUser = loggedInUser;
		this.password = null;
		this.choiceID = choiceID;
	}
	public UserGsonCompatible(User u) {
		this.newUser = true;
		this.loggedInUser = u.name;
		this.password = u.password;
		this.choiceID = u.choiceId;
	}
	
	
	
}
