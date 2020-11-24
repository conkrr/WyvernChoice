package com.amazonaws.lambda.demo.http;

public class RegisterUserResponse {
	public boolean newUser;
	public String loggedInUser;
	public String password;
	public String choiceID;
	public int statusCode;
	public String error;
	
	public RegisterUserResponse(String user, String password, String choiceID, int statusCode) {
		//Implementing newUser might be complicated?
		this.loggedInUser = "" + user;
		if(password != null) {
			this.password = password;
		} else {
			this.password = "No password was given.";
		}
		this.choiceID = "" + choiceID;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public RegisterUserResponse(int statusCode, String errorMessage) {
		this.loggedInUser = "";
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "{ \"loggedInUser\": \"" + loggedInUser + "\", \"password\": \"" + password + "\", \"choiceID\": \"" + choiceID + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
	
	
}
