package com.amazonaws.lambda.demo.http;

public class AddDisapprovalResponse
{

	public String disapprovingUserID;
	public String alternativeID;
	public String choiceID;
	public int approvals;
	public int disapprovals;
	public int statusCode;
	public String error;
	
	public AddDisapprovalResponse(String disapprovingUserID, String alternativeID, String choiceID, int approvals, int disapprovals, int statusCode)
    {
    	this.disapprovingUserID = disapprovingUserID;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.approvals = approvals;
    	this.disapprovals = disapprovals;
    	this.statusCode = statusCode;
    	this.error = "";
    }
    
    public AddDisapprovalResponse(String disapprovingUserID, String alternativeID, String choiceID, int approvals, int disapprovals)
    {
    	this.disapprovingUserID = disapprovingUserID;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.approvals = approvals;
    	this.disapprovals = disapprovals;
    	this.statusCode = 200;
    	this.error = "";
    }
    
    public AddDisapprovalResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}

	public String toString() {
		if(statusCode == 200) {
			return "{ \"disapprovingUserID\": \"" + disapprovingUserID + "\", \"alternativeID\": \"" + alternativeID + "\", \"choiceID\": \"" + choiceID + "\", \"approvals\": \"" + approvals + "\", \"disapprovals\": \"" + disapprovals + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}

    /*
        TODO: For Geoff to finish
    */


}
