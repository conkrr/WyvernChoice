package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.User;

public class RegisterUserResponse {

	public String userID;
	public String password;
	public String choiceID;
	
	public int statusCode;
	public String error;
	
	public RegisterUserResponse(String userID, String password, String choiceID) {
		this.userID = userID;
		this.password = password;
		this.choiceID = choiceID;
		this.statusCode = 200;
	}
	
	public RegisterUserResponse(User u) {
		this.userID = u.userId;
		this.password = u.password;
		this.choiceID = u.choiceId;
		this.statusCode = 200;
	}
	
	public RegisterUserResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public RegisterUserResponse () {
		this.statusCode = 200;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "{ \"userID\": \"" + userID + "\", \"password\": \"" + password + "\", \"choiceID\": \"" + choiceID + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
	
	
}
