package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.Choice;

public class GetListOfChoicesResponse {
	public ArrayList<Choice> listOfChoices;
	public int statusCode;
	public String error;
	
	public GetListOfChoicesResponse() {
	}
	
	public GetListOfChoicesResponse(ArrayList<Choice> choices, int statusCode, String error) {
		this.listOfChoices = choices;
		this.statusCode = statusCode;
		this.error = error;
	}
	
	public GetListOfChoicesResponse(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			String output = "{ \" choice0\" : \"" + listOfChoices.get(0) + "\"";
			if(listOfChoices.size() > 1) {
				for(int i = 1; i < listOfChoices.size(); i++) {
					output = output + ", \"choice" + i + "\" : \"" + listOfChoices.get(i)+ "\"";
				}
			}
			return output + "}";
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
}
