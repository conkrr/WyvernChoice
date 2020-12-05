package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.http.AlternativeGsonCompatible;

public class Choice {
	
	public final String id;
	public final String description;
	public final Timestamp creationDate;
	public final String creatingUserId;
	public final boolean isFinalized;
	public final List<Alternative> alternatives;
	public int maxParticipants;
	public int currentParticipants;
	
	public Choice(String id, String description, Timestamp creationDate, String creatingUserId, boolean isFinalized, List<Alternative> alternatives,
			int maxParticipants, int currentParticipants) {
		this.id = id;
		this.description = description;
		this.creationDate = creationDate;
		this.creatingUserId = creatingUserId;
		this.isFinalized = isFinalized;
		this.alternatives = alternatives;
		this.maxParticipants = maxParticipants;
		this.currentParticipants = currentParticipants;
	}
	
	public List<String >getAlternativeIdList(){
		ArrayList<String> ids = new ArrayList<String>(alternatives.size());
		for(Alternative a : alternatives) {
			ids.add(a.getAlternativeID());
		}
		return ids;
	}
	public List<AlternativeGsonCompatible> getAlternativeGsonList(){
		ArrayList<AlternativeGsonCompatible> altsGson = new ArrayList<AlternativeGsonCompatible>(alternatives.size());
		for(Alternative a : alternatives) {
			altsGson.add(new AlternativeGsonCompatible(a.getAlternativeID(), a.getName()));
		}
		return altsGson;
	}

}
