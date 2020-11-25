package com.amazonaws.lambda.demo.http;

public class GetChoiceRequest {
	
	private String choiceId;

	public String getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}

	public GetChoiceRequest(String choiceId) {
		this.choiceId = choiceId;
	}
	
	public GetChoiceRequest() {
		
	}
	
}
