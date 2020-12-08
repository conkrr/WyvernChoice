package com.amazonaws.lambda.demo.http;

import java.util.List;

public class CreateFeedbackRequest {
	private String user;
	private String text;
	private String alternativeID;
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String u) {
		this.user = u;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String t) {
		this.text = t;
	}
	
	public String getAlternativeID() {
		return alternativeID;
	}
	
	public void setAlternativeID(String id) {
		this.alternativeID = id;
	}
	
	public CreateFeedbackRequest(String user, String text, String alternativeID) {
        this.user = user;
        this.text = text;
        this.alternativeID = alternativeID;
    }

    public CreateFeedbackRequest() {

    }

}
