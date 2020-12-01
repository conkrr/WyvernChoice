package com.amazonaws.lambda.demo.http;

public class RemoveDisapprovalResponse
{

	public String disapprovingUser;
	public String alternativeID;
	public String choiceID;
	public int statusCode;
	public String error;
	
    public RemoveDisapprovalResponse(String disapprovingUser, String alternativeID, String choiceID, int statusCode,
    		String error)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = statusCode;
    	this.error = error;
    }
    
    public RemoveDisapprovalResponse(String disapprovingUser, String alternativeID, String choiceID,
    		String error)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = 200;
    	this.error = error;
    }
    
    public RemoveDisapprovalResponse(String disapprovingUser, String alternativeID, String choiceID,
    		int statusCode)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = statusCode;
    	this.error = "";
    }
    
    public RemoveDisapprovalResponse(String disapprovingUser, String alternativeID, String choiceID)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = 200;
    	this.error = "";
    }
    
    public RemoveDisapprovalResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}

	public String toString() {
		if(statusCode == 200) {
			return "{ \"disapprovingUser\": \"" + disapprovingUser + "\", \"alternativeID\": \"" + alternativeID + "\", \"choiceID\": \"" + choiceID + "\", \"statusCode\": \"" + statusCode +"\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}

    /*
        TODO: For Geoff to finish
    */

}
