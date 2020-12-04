package com.amazonaws.lambda.demo.http;

public class AddApprovalResponse
{

	public String approvingUser;
	public String alternativeID;
	public String choiceID;
	public int statusCode;
	public String error;
	
	public AddApprovalResponse(String approvingUser, String alternativeID, String choiceID) {
		this.approvingUser = approvingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = 200;
    	this.error = "";
	}
	
	public AddApprovalResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	

	public String toString() {
		if(statusCode == 200) {
			return "{ \"approvingUser\": \"" + approvingUser + "\", \"alternativeID\": \"" + alternativeID + "\", \"choiceID\": \"" + choiceID + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}

    /*
        TODO: For Geoff to finish
    */

}
