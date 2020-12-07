package com.amazonaws.lambda.demo.http;

public class FinalizeChoiceRequest {
	String choiceID;
	String alternativeID;
	boolean isFinalized;
	
	public String getChoiceID() {
		return choiceID;
	}
	
	public String getAlternativeID() {
		return alternativeID;
	}
	
	public boolean getIsFinalized() {
		return isFinalized;
	}
	
	public FinalizeChoiceRequest() {
		
	}
	
	public FinalizeChoiceRequest(String choiceID, String alternativeID, boolean isFinalized) {
		this.choiceID = choiceID;
		this.alternativeID = alternativeID;
		this.isFinalized = isFinalized;
	}
}
