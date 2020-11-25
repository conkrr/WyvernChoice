package com.amazonaws.lambda.demo.http;

public class GetChoiceRequest {
	
	private String choiceID;

	public String getChoiceId() {
		return choiceID;
	}

	public void setChoiceId(String choiceID) {
		this.choiceID = choiceID;
	}

	public GetChoiceRequest(String choiceId) {
		this.choiceID = choiceId;
	}
	
	public GetChoiceRequest() {
		
	}
	
}
