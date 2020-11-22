package com.amazonaws.lambda.demo.http;

public class RegisterUserResponse {
	public boolean newUser;
	public String loggedInUser;
	public String password;
	public int statusCode;
	public String error;
	
	public RegisterUserResponse(String user, String password, int statusCode) {
		//Implementing newUser might be complicated?
		this.loggedInUser = "" + user;
		if(password != null) {
			this.password = password;
		} else {
			this.password = "No password was given.";
		}
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
			return "Username: " + loggedInUser + " Password: " + password; 
		} else {
			return "Error(" + statusCode + ", err=" + error + ")";
		}
	}
	
	
}
