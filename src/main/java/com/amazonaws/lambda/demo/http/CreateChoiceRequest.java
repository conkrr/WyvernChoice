package com.amazonaws.lambda.demo.http;

public class CreateChoiceRequest {
	public String id;
	public String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CreateChoiceRequest(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	public CreateChoiceRequest() {

	}

	
}
