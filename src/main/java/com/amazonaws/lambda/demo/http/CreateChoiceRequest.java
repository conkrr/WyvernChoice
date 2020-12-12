
package com.amazonaws.lambda.demo.http;

import java.util.List;

public class CreateChoiceRequest {

    private String name;
    private String creatingUserID;
    private int numParticipants;
    private List<String> listofAlternatives;

    
    
    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getCreatingUserID() {
        return creatingUserID;
    }



    public void setCreatingUserID(String creatingUserID) {
        this.creatingUserID = creatingUserID;
    }



    public int getNumParticipants() {
        return numParticipants;
    }



    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }



    public List<String> getListofAlternatives() {
        return listofAlternatives;
    }



    public void setListofAlternatives(List<String> listofAlternatives) {
        this.listofAlternatives = listofAlternatives;
    }

    

    public CreateChoiceRequest(String name, String creatingUserID, int numParticipants,
            List<String> listofAlternatives) {
        this.name = name;
        this.creatingUserID = creatingUserID;
        this.numParticipants = numParticipants;
        this.listofAlternatives = listofAlternatives;
    }



    public CreateChoiceRequest() {

    }

    
}
