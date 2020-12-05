package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Choice;

public class GetListOfChoicesResponse {
	public List<Choice> listOfChoices;
	public int statusCode;
	public String error;
	
	public GetListOfChoicesResponse() {
	}
	
	public GetListOfChoicesResponse(List<Choice> choices, int statusCode, String error) {
		this.listOfChoices = choices;
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public GetListOfChoicesResponse(ArrayList<Choice> choices) {
		this.listOfChoices = choices;
		this.statusCode = 200;
		this.error = "";
	}
	
	public GetListOfChoicesResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			String output = "{ \"choice0ID\" : \"" + listOfChoices.get(0).id + "\" , \"choice0Description\": \"" + listOfChoices.get(0).description + "\" , \"choice0CreationDate\": \"" + listOfChoices.get(0).creationDate + "\" , \"choice0IsFinalized\": \""+ listOfChoices.get(0).isFinalized + "\"";
			if(listOfChoices.size() > 1) {
				for(int i = 1; i < listOfChoices.size(); i++) {
					output = output + ", \"choice" + i + "ID\" : \"" + listOfChoices.get(i).id+ "\" , \"choice" + i + "Description\" : \"" + listOfChoices.get(i).description + "\", \"choice" + i + "CreationDate\" : \"" + listOfChoices.get(i).creationDate + "\" , \"choice" + i + "IsFinalized\": \""+ listOfChoices.get(i).isFinalized + "\"";
				}
			}
			return output + "}";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
}
