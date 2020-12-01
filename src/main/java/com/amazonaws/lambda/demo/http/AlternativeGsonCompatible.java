package com.amazonaws.lambda.demo.http;

public class AlternativeGsonCompatible {

	private String id;
	private String description;
	
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
	public AlternativeGsonCompatible(String id, String description) {
		this.id = id;
		this.description = description;
	}
	public AlternativeGsonCompatible() {
		
	}

}
