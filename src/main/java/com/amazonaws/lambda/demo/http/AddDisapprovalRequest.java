package com.amazonaws.lambda.demo.http;

public class AddDisapprovalRequest
{
    private String disapprovingUserID;
	private String alternativeID;
	private String choiceID;
	
	public AddDisapprovalRequest() {
		
	}

    public AddDisapprovalRequest(String disapprovingUserID, String alternativeID, String choiceID)
    {
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
        this.disapprovingUserID = disapprovingUserID;
    }

    public void setUsername(String disapprovingUserID) {
    	this.disapprovingUserID = disapprovingUserID;
    }
    
    public String getUsername() {
    	return this.disapprovingUserID;
    }

    public void setDisapprovingUserID(String disapprovingUserID) {
        this.disapprovingUserID = disapprovingUserID;
    }

    public String getDisapprovingUserID() {
        return this.disapprovingUserID;
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

}
