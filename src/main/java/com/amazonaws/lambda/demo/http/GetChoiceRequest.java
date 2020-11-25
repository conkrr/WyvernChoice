package com.amazonaws.lambda.demo.http;

public class GetChoiceRequest {
	
	private String choiceID;

	public String getChoiceID() {
		return choiceID;
	}

	public void setChoiceID(String choiceID) {
		this.choiceID = choiceID;
	}

	public GetChoiceRequest(String choiceID) {
		this.choiceID = choiceID;
	}
	
	public GetChoiceRequest() {
		
	}
	
}
