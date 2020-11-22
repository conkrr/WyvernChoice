package com.amazonaws.lambda.demo.http;

public class RegisterUserRequest {
	String loggedInUser;
	String password;
	
	public String getLoggedInUser() {return loggedInUser;}
	public void setLoggedInUser(String user) {this.loggedInUser = user;}
	
	public String getPassword() {return password;}
	public void setPassword(String pw) {this.password = pw;}
	
	public String toString() {return "Username: " + loggedInUser + ", Password: " + password;}
	
	public RegisterUserRequest(String loggedInUser, String password) {
		this.loggedInUser = loggedInUser;
		this.password = password;
	}
	
	public RegisterUserRequest() {
		//kek
	}
}
