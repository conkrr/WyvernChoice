package com.amazonaws.lambda.demo.http;

public class FinalizeChoiceResponse {
	public String choiceID;
	public String alternativeID;
	public boolean isFinalized;
	public int statusCode;
	public String error;
	

	// statusCode 200 means success
	public FinalizeChoiceResponse(String choiceID, String alternativeID, boolean isFinalized) {
		this.choiceID = choiceID;
		this.alternativeID = alternativeID;
		this.isFinalized = isFinalized;
		this.statusCode = 200;
	}
	
	public FinalizeChoiceResponse(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}
	
	
	public String toString() {
		if(statusCode == 200) {
			return "{ \"choiceID\": \"" + choiceID + "\", \"alternativeID\": \"" + alternativeID + "\", \"isFinalized\": \"" + isFinalized + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
	
}
