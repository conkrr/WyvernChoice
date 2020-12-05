package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Choice;

public class GetChoiceResponse {
	public ChoiceGsonCompatible choice;
	public String error;
	public int statusCode;
	
	public GetChoiceResponse (String s, int code) {
		this.error = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public GetChoiceResponse (String s) {
		this.error = s;
		this.statusCode = 200;
	}
	
	public GetChoiceResponse(ChoiceGsonCompatible c) {
		this.choice = c;
    	this.statusCode = 200;
    	this.error = "";
	}
	
	public GetChoiceResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "{ \"choice\": \"" + choice + "\", \"statusCode\": \"" + statusCode + "\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
}
