package com.amazonaws.lambda.demo.model;

public class Feedback {
	public final String user;
	public final String text;
	public final String timestamp;
	public final String alternativeID;
	
	public Feedback(String user, String text, String timestamp, String alternativeID) {
		this.user = user;
		this.text = text;
		this.timestamp = timestamp;
		this.alternativeID = alternativeID;
	}
}
