package com.amazonaws.lambda.demo.http;

public class RegisterUserResponse {

	public UserGsonCompatible userGson;
	
	public int statusCode;
	public String error;
	
	public RegisterUserResponse(UserGsonCompatible u) {
		this.userGson = u;
		this.statusCode = 200;
	}
	
	public RegisterUserResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public RegisterUserResponse () {
		this.statusCode = 200;
	}
	
//	public String toString() {
//		if(statusCode == 200) {
//			return "{ \"loggedInUser\": \"" + loggedInUser + "\", \"password\": \"" + password + "\", \"choiceID\": \"" + choiceID + "\", \"statusCode\": \"" + statusCode +"\" }";
//		} else {
//			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
//		}
//	}
	
	
}
