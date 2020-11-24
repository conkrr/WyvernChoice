package com.amazonaws.lambda.demo.http;

public class DeleteChoiceRequest {

	public String id;
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String toString() {
		return "Delete(" + id + ")";
	}



	public DeleteChoiceRequest(String id) {
		super();
		this.id = id;
	}
	public DeleteChoiceRequest() {
		
	}
	
}
