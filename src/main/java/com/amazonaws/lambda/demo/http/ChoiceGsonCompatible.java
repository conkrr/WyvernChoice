package com.amazonaws.lambda.demo.http;

import java.util.List;

import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Choice;

public class ChoiceGsonCompatible {
	 String name;
	 String choiceID;
	 int numParticipants;
	 String completionDate;
	 boolean isFinalized;
	 List<Alternative> listofAlternatives;
	public String getName() {
		return name;
	}
	public String getChoiceID() {
		return choiceID;
	}
	public int getNumParticipants() {
		return numParticipants;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public boolean isFinalized() {
		return isFinalized;
	}
	public List<Alternative> getListofAlternatives() {
		return listofAlternatives;
	}
	public ChoiceGsonCompatible(String name, String choiceID, int numParticipants, String completionDate,
			boolean isFinalized, List<Alternative> listofAlternatives) {
		this.name = name;
		this.choiceID = choiceID;
		this.numParticipants = numParticipants;
		this.completionDate = completionDate;
		this.isFinalized = isFinalized;
		this.listofAlternatives = listofAlternatives;
	}
	 
	public ChoiceGsonCompatible(Choice c) {
		this.name = c.description;
		this.choiceID = c.id;
		this.numParticipants = c.maxParticipants;
		this.completionDate = null;
		this.isFinalized = c.isFinalized;
		this.listofAlternatives = c.alternatives;
	}
	 
	 
}