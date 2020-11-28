package com.amazonaws.lambda.demo.http;

public class DeleteChoiceRequest {

	String id;
	
	
	
	public String getId() {
		return id;
	}


	public String toString() {
		return "Delete(" + id + ")";
	}



	public DeleteChoiceRequest(String id) {
		this.id = id;
	}
	public DeleteChoiceRequest() {
		
	}
	
}
