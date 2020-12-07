package com.amazonaws.lambda.demo.http;

public class DeleteChoicesResponse {
	public float time;
	public int statusCode;
	public String error;
	
	public DeleteChoicesResponse (float time) {
		this.time = time;
		this.statusCode = 200;
		this.error = "";
	}
	
	// 200 means success
	public DeleteChoicesResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "{ \"time\": \"" + time + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
}
