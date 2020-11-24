package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Choice;

public class UpdateResponse {
	//Return a choice as the response
	public Choice choice;
	public int statusCode;
	public String error;
	
	/**
	 * Prints all the elements of a choice as an error message.
	 * @param choice
	 * @return
	 */
	
	//Update when choice is updated
	public String printChoice(Choice choice) {
		return "{ \"id\": \"" + choice.id + "\", \"description\": \"" + choice.description + "\", \"statusCode\" : \"" + statusCode + "\" }";
	}
	
	//Success
	public UpdateResponse(Choice choice, int statusCode) {
		this.choice = choice;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	//Failure
	public UpdateResponse(int statusCode, String errorMessage) {
		//Should we null Choice upon failure?
		//this.choice = printChoice(choice);
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if(statusCode == 200) {
			//Though how do we return choice?
			return printChoice(choice);
		} else {
			return "{ \"statusCode\": \"" + statusCode + "\", \"error\": \"" + error + "\" }";
		}
	}
	
	//How do list
}
