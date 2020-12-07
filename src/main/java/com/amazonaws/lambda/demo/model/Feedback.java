package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;

public class Feedback {
	public final String user;
	public final String text;
	public final Timestamp timestamp;
	public final String alternativeID;
	
	public Feedback(String user, String text, Timestamp timestamp, String alternativeID) {
		this.user = user;
		this.text = text;
		this.timestamp = timestamp;
		this.alternativeID = alternativeID;
	}
}
