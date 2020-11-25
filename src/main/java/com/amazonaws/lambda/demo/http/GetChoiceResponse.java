package com.amazonaws.lambda.demo.http;

public class GetChoiceResponse {
	public ChoiceGsonCompatible choice;
	public String response;
	public int httpCode;
		
	public GetChoiceResponse (ChoiceGsonCompatible c) {
		this.choice = c;
		this.httpCode = 200;
	}
	
	public GetChoiceResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public GetChoiceResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
