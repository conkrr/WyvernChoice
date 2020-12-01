package com.amazonaws.lambda.demo.http;

public class GetChoiceResponse {
	public ChoiceGsonCompatible choice;
	public String error;
	public int statusCode;
		
	public GetChoiceResponse (ChoiceGsonCompatible c) {
		this.choice = c;
		this.statusCode = 200;
	}
	
	public GetChoiceResponse (String s, int code) {
		this.error = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public GetChoiceResponse (String s) {
		this.error = s;
		this.statusCode = 200;
	}
	
	public String toString() {
		return "Response(" + error + ")";
	}
}
