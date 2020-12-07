package com.amazonaws.lambda.demo.http;

import java.util.List;

import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Choice;

public class GetChoiceResponse {
	public String error;
	public int statusCode;
	public String name;
	public String choiceID;
	public int numParticipants;
	public String creationDate;
	public boolean isFinalized;
	public List<AlternativeResponse> listofAlternatives;
	
	public GetChoiceResponse (String s, int code) {
		this.error = s;
		this.statusCode = code;
	}
	
	public GetChoiceResponse (Choice c) {
		this.error = "";
		this.statusCode = 200;
		this.name = c.description;
		this.choiceID = c.id;
		this.numParticipants = c.maxParticipants;
		this.creationDate = c.creationDate.toString();
		this.isFinalized = c.isFinalized;
		this.listofAlternatives = AlternativeResponse.getResponseForm(c.alternatives);
	}
	
	
	public GetChoiceResponse(String error, int statusCode, Choice c) {
		this.error = error;
		this.statusCode = statusCode;
		this.name = c.description;
		this.choiceID = c.id;
		this.numParticipants = c.maxParticipants;
		this.creationDate = c.creationDate.toString();
		this.isFinalized = c.isFinalized;
		this.listofAlternatives = AlternativeResponse.getResponseForm(c.alternatives);
	}



	// 200 means success
	public GetChoiceResponse (String s) {
		this.error = s;
		this.statusCode = 200;
	}
	
		
	public GetChoiceResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			return "{ \"choice\": \"" + choiceID + "\", \"statusCode\": \"" + statusCode + "\" }";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
}
