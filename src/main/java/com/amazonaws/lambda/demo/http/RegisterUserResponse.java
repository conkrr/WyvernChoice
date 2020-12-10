package com.amazonaws.lambda.demo.http;

public class RegisterUserResponse {

	public UserGsonCompatible userGson;
	
	public int status;
	public String error;
	
	public RegisterUserResponse(UserGsonCompatible u) {
		this.userGson = u;
		this.status = 200;
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
			return "{ \"username\": \"" + userGson.username + "\", \"password\": \"" + userGson.password + "\", \"choiceID\": \"" + userGson.choiceID + "\", \"statusCode\": \"" + status +"\" }";
		} else {
			return "{ \"statusCode\": \"" + status + "\", \"error\": \"" + error + "\" }";
		}
	}
	
	
}
