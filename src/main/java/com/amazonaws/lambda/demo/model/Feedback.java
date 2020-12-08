package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;

public class Feedback {
	public final String userID;
	//public final String userName;
	public final String text;
	public final Timestamp timestamp;
	public final String alternativeID;
	
	public Feedback(String userID, String text, Timestamp timestamp, String alternativeID) {
		this.userID = userID;
		this.text = text;
		this.timestamp = timestamp;
		this.alternativeID = alternativeID;
	}
}
