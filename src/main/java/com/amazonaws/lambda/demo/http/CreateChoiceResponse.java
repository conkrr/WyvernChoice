package com.amazonaws.lambda.demo.http;

public class CreateChoiceResponse {
	public ChoiceGsonCompatible choice;
	public String error;
	public int statusCode;
		
	public CreateChoiceResponse (ChoiceGsonCompatible c) {
		this.choice = c;
		this.statusCode = 200;
	}
	
	public CreateChoiceResponse (String s, int code) {
		this.error = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public CreateChoiceResponse (String s) {
		this.error = s;
		this.statusCode = 200;
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}
}