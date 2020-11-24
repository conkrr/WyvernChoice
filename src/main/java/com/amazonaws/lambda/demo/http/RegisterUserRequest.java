package com.amazonaws.lambda.demo.http;

public class RegisterUserRequest {
	String loggedInUser;
	String password;
	String choiceID;
	
	public String getLoggedInUser() {return loggedInUser;}
	public void setLoggedInUser(String user) {this.loggedInUser = user;}
	
	public String getPassword() {return password;}
	public void setPassword(String pw) {this.password = pw;}
	
	public String getChoiceID() {return choiceID;}
	public void setChoiceID(String id) {this.choiceID = id;}
	
	public String toString() {return "{ \"loggedInUser\": " + loggedInUser + ", \"password\": " + password + ", \"choiceID\": " + choiceID + " }";}
	
	public RegisterUserRequest(String loggedInUser, String password, String choiceID) {
		this.loggedInUser = loggedInUser;
		this.password = password;
		this.choiceID = choiceID;
	}
	
	public RegisterUserRequest() {
		
	}
}
