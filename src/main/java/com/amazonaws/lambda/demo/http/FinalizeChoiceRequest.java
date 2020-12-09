package com.amazonaws.lambda.demo.http;

public class FinalizeChoiceRequest {
	String choiceID;
	String alternativeID;
	boolean isFinalized;
	
	public String getChoiceID() {
		return choiceID;
	}
	
	public void setChoiceID(String id) {
		this.choiceID = id;;
	}
	
	public String getAlternativeID() {
		return alternativeID;
	}
	
	public void setAlternativeID(String id) {
		this.alternativeID = id;
	}
	
	
	public boolean getIsFinalized() {
		return isFinalized;
	}
	
	public void setIsFinalized(boolean f) {
		this.isFinalized = f;
	}
	
	
	public FinalizeChoiceRequest() {
		
	}
	
	public FinalizeChoiceRequest(String choiceID, String alternativeID, boolean isFinalized) {
		this.choiceID = choiceID;
		this.alternativeID = alternativeID;
		this.isFinalized = isFinalized;
	}
}
