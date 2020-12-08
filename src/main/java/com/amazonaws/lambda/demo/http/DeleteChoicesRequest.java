package com.amazonaws.lambda.demo.http;

public class DeleteChoicesRequest {

	float time;
	
	public float getTime() {
		return time;
	}
	
	public void setTime(float t) {
		this.time = t;
	}
	
	public DeleteChoicesRequest() {
	
	}
	
	public DeleteChoicesRequest(float time) {
		this.time = time;
	}
	
}
