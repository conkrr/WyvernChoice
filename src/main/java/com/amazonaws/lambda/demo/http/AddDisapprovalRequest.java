package com.amazonaws.lambda.demo.http;

public class AddDisapprovalRequest
{
	
	private String disapprovingUser;
	private String alternativeID;
	private String choiceID;
	
	public AddDisapprovalRequest() {
		
	}

    public AddDisapprovalRequest(String disapprovingUser, String alternativeID, String choiceID)
    {
    	this.disapprovingUser = disapprovingUser;
    	this.alternativeID = alternativeID;
    	this.choiceID = choiceID;
    }


    public void setDisapprovingUser(String disapprovingUser) {
    	this.disapprovingUser = disapprovingUser;
    }
    
    public String getDisappearingUser() {
    	return this.disapprovingUser;
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
