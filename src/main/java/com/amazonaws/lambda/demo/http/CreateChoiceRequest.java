package com.amazonaws.lambda.demo.http;

public class CreateChoiceRequest {
	String id;
	String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public String toString() {
		return "Create(" + id + "," + description + ")";
	}
	
	public CreateChoiceRequest(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	public CreateChoiceRequest() {

	}

	
}
