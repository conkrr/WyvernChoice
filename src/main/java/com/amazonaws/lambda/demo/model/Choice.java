package com.amazonaws.lambda.demo.model;

public class Choice {
	
	public final String id;
	public final String description;
//	public final String creationDate;
//	public final boolean isFinalized;
//	public final ArrayList<String> alternatives;
//	public Choice(String id, String description, String creationDate, boolean isFinalized) {
//		this.id = id;
//		this.description = description;
//		this.creationDate = creationDate;
//		this.isFinalized = isFinalized;
//	}	
	public Choice(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	
	
}
