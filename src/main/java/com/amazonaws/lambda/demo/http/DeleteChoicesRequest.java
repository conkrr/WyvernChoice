package com.amazonaws.lambda.demo.http;

public class DeleteChoicesRequest {

	float time;
	
	public float getTime() {
		return time;
	}
	
	public String toString() {
		return "DeleteChoices(" + time + ")";
	}
	
	public DeleteChoicesRequest() {
		
	}
	
	public DeleteChoicesRequest(float time) {
		this.time = time;
	}
}
