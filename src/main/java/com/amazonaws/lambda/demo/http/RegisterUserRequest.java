package com.amazonaws.lambda.demo.http;

public class RegisterUserRequest {
	String username;
	String password;
	String choiceID;
	
	public String getUsername() {return username;}
	public void setUsername(String user) {this.username = user;}
	
	public String getPassword() {return password;}
	public void setPassword(String pw) {this.password = pw;}
	
	public String getChoiceID() {return choiceID;}
	public void setChoiceID(String id) {this.choiceID = id;}
	
	public String toString() {return "{ \"username\": " + username + ", \"password\": " + password + ", \"choiceID\": " + choiceID + " }";}
	
	public RegisterUserRequest(String username, String password, String choiceID) {
		this.username = username;
		this.password = password;
		this.choiceID = choiceID;
	}
	
	public RegisterUserRequest() {
		
	}
}
