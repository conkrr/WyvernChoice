package com.amazonaws.lambda.demo.http;

public class CreateChoiceResponse {
	public ChoiceGsonCompatible choice;
	public String response;
	public int httpCode;
		
	public CreateChoiceResponse (ChoiceGsonCompatible c) {
		this.choice = c;
		this.httpCode = 200;
		//kek
	}
	
	public CreateChoiceResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public CreateChoiceResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}