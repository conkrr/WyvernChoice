package com.amazonaws.lambda.demo.http;

public class FinalizeChoiceResponse {
	public String choiceID;
	public String alternativeID;
	public boolean isFinalized;
	public int statusCode;
	public String error;
	

	// 200 means success
	public FinalizeChoiceResponse(String choiceID, String alternativeID, boolean isFinalized, int statusCode, String error) {
		this.choiceID = choiceID;
		this.alternativeID = alternativeID;
		this.isFinalized = isFinalized;
		this.statusCode = statusCode;
		this.error = error;
	}
	
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "FinalChoiceResponse(choiceID= " + choiceID + ", alternativeID= " + alternativeID + ", isFinalized?= " + isFinalized + ")";
		} else {
			return "ErrorResult(choiceID= " + choiceID + ", alternativeID= " + alternativeID + ", isFinalized?= " + isFinalized + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
	
}
