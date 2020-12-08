package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Feedback;

public class CreateFeedbackResponse {
	private Feedback feedback;
	private int statusCode;
	private String error;
	
	public CreateFeedbackResponse(Feedback feedback) {
		this.feedback = feedback;
		this.statusCode = 200;
		this.error = "";
	}
	
	public CreateFeedbackResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "{ \"feedbackUser\": \"" + feedback.user + "\", \"feedbackText\": \"" + feedback.text + "\", \"feedbackTimestamp\": \"" + feedback.timestamp + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
}
