package com.amazonaws.lambda.demo.http;

public class AddDisapprovalResponse
{

	public String disapprovingUser;
	public String alternativeID;
	public String choiceID;
	public int statusCode;
	public String error;
	
    public AddDisapprovalResponse(String disapprovingUser, String alternativeID, String choiceID, int statusCode,
    		String error)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = statusCode;
    	this.error = error;
    }
    
    public AddDisapprovalResponse(String disapprovingUser, String alternativeID, String choiceID,
    		String error)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = 200;
    	this.error = error;
    }
    
    public AddDisapprovalResponse(String disapprovingUser, String alternativeID, String choiceID,
    		int statusCode)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = statusCode;
    	this.error = "";
    }
    
    public AddDisapprovalResponse(String disapprovingUser, String alternativeID, String choiceID)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    	this.statusCode = 200;
    	this.error = "";
    }
    
    public String toString() {
		return "Response(" + error + ")";
	}

    /*
        TODO: For Geoff to finish
    */


}
