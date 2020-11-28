
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
//
//
//package com.amazonaws.lambda.demo.http;
//
//import java.util.List;
//
//public class CreateChoiceRequest {
//	//String id;
//	String description;
//	String creatingUserId;
//	List<String> alternatives;
//	int maxParticipants;
////	public String getId() {
////		return id;
////	}
//	
////	public String toString() {
////		return String.format("Create(%s,%s,%s,%d)", description, creatingUserId, alternatives.toString(), maxParticipants);
////	}
//	
//	public String getDescription() {
//		return description;
//	}
//
//	public String getCreatingUserId() {
//		return creatingUserId;
//	}
//
//	public List<String> getAlternatives() {
//		return alternatives;
//	}
//
//	public int getMaxParticipants() {
//		return maxParticipants;
//	}
//	
//	public CreateChoiceRequest(String description, String creatingUserId, List<String> alternatives,
//			int maxParticipants) {
//		this.description = description;
//		this.creatingUserId = creatingUserId;
//		this.alternatives = alternatives;
//		this.maxParticipants = maxParticipants;
//	}
//
//	public CreateChoiceRequest() {
//
//	}
//
//	
//}
