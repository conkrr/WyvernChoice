package com.amazonaws.lambda.demo.http;

public class AddApprovalRequest {

    private String approvingUser;
    private String approvingUserID;
    private String alternativeID;
    private String choiceID;

    public AddApprovalRequest() {

    }

    public AddApprovalRequest(String approvingUser, String approvingUserID, String alternativeID, String choiceID)
    {
        this.approvingUser = approvingUser;
        this.alternativeID = alternativeID;
        this.choiceID = choiceID;
        this.approvingUserID = approvingUserID;
    }

    public void setUsername(String approvingUser) {
        this.approvingUser = approvingUser;
    }

    public String getUsername() {
        return this.approvingUser;
    }

    public void setApprovingUserID(String approvingUserID) {
        this.approvingUserID = approvingUserID;
    }
    public String getApprovingUserID() {
        return this.approvingUserID;
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
