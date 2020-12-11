package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.User;

public class RegisterUserResponse {


	public String password;
	public String choiceID;
	public String userID;
	public int status;
	public String error;
	
	public RegisterUserResponse(User u) {
		this.password = u.password;
		this.choiceID = u.choiceId;
		this.userID = u.userId;
		this.status = 200;
		this.error = "";
	}
	
	public RegisterUserResponse(String password, String choiceID, String userID, int status, String error) {
		this.password = password;
		this.choiceID = choiceID;
		this.userID = userID;
		this.status = status;
		this.error = error;
	}

	public RegisterUserResponse(String password, String choiceID, String userID) {
		this.password = password;
		this.choiceID = choiceID;
		this.userID = userID;
		this.status = 200;
		this.error = "";
	}
	
	public RegisterUserResponse(int statusCode, String errorMessage) {
		this.status = statusCode;
		this.error = errorMessage;
	}
	
	public RegisterUserResponse () {
		this.status = 200;
	}
	
	public String toString() {
		if(status == 200) {
			return "{ \"username\": \"" + userID + "\", \"password\": \"" + password + "\", \"choiceID\": \"" + choiceID + "\", \"statusCode\": \"" + status +"\" }";
		} else {
			return "{ \"statusCode\": \"" + status + "\", \"error\": \"" + error + "\" }";
		}
	}
	
	
}
