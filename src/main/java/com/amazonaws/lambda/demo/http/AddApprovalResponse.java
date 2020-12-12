package com.amazonaws.lambda.demo.http;

public class AddApprovalResponse
{

	public String approvingUser;
	public String alternativeID;
	public String choiceID;
	public int approvals;
	public int disapprovals;
	public int statusCode;
	public String error;
	
	//For database
	public AddApprovalResponse(String approvingUser, String alternativeID, String choiceID, int approvals, int disapprovals, int statusCode) {
		this.approvingUser = approvingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.approvals = approvals;
    	this.disapprovals = disapprovals;
    	this.statusCode = statusCode;
    	this.error = "";
	}
	
	public AddApprovalResponse(String approvingUser, String alternativeID, String choiceID, int approvals, int disapprovals) {
		this.approvingUser = approvingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.approvals = approvals;
    	this.disapprovals = disapprovals;
    	this.statusCode = 200;
    	this.error = "";
	}
	
	public AddApprovalResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	

	public String toString() {
		if(statusCode == 200) {
			return "{ \"approvingUser\": \"" + approvingUser + "\", \"alternativeID\": \"" + alternativeID + "\", \"choiceID\": \"" + choiceID + "\", \"approvals\": \"" + approvals + "\", \"disapprovals\": \"" + disapprovals + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}


}
