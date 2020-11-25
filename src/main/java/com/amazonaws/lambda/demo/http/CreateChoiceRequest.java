package com.amazonaws.lambda.demo.http;

import java.util.List;

public class CreateChoiceRequest {
	String id;
	String description;
	String creatingUserId;
	List<String> alternatives;
	int maxParticipants;
//	public String getId() {
//		return id;
//	}
	
//	public String toString() {
//		return String.format("Create(%s,%s,%s,%d)", description, creatingUserId, alternatives.toString(), maxParticipants);
//	}
	
	public String getDescription() {
		return description;
	}

	public String getCreatingUserId() {
		return creatingUserId;
	}

	public List<String> getAlternatives() {
		return alternatives;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}
	
	public CreateChoiceRequest(String description, String creatingUserId, List<String> alternatives,
			int maxParticipants) {
		this.description = description;
		this.creatingUserId = creatingUserId;
		this.alternatives = alternatives;
		this.maxParticipants = maxParticipants;
	}

	public CreateChoiceRequest() {

	}

	public String toString() {
		return "CreateChoice(" + id + "," + description + ")";
	}
	
}
