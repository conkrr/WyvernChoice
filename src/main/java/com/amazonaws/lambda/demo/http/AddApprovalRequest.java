package com.amazonaws.lambda.demo.http;

public class AddApprovalRequest
{
	private String approvingUser;
	private String alternativeID;
	private String choiceID;

    public AddApprovalRequest() {
    	
    }
    
    public AddApprovalRequest(String approvingUser, String alternativeID, String choiceID) {
    	this.approvingUser = approvingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    }

    
    public void setUser(String approvingUser) {
    	this.approvingUser = approvingUser;
    }
    
    public String getUser() {
    	return this.approvingUser;
    }
    
    public void setAlternativeID(String alternativeID) {
    	this.alternativeID = alternativeID;
    }
    
    public String getAlternativeID() {
    	return this.alternativeID;
    }
    
    public void setChoiceID(String choiceID) {
    	this.choiceID = choiceID;
    }
    
    public String getChoiceID() {
    	return this.choiceID;
    }
    
    /*
        TODO: For Geoff to finish
    */

}
