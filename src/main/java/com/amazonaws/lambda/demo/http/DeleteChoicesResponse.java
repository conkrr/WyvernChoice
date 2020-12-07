package com.amazonaws.lambda.demo.http;

public class DeleteChoicesResponse {
	public float time;
	public int statusCode;
	public String error;
	
	public DeleteChoicesResponse (float time, int statusCode) {
		this.time = time;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 means success
	public DeleteChoicesResponse (float time, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.time = time;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "DeleteResponse(" + time + ")";
		} else {
			return "ErrorResult(" + time + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	
	}
}
