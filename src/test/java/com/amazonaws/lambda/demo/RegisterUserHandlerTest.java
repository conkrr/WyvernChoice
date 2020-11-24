package com.amazonaws.lambda.demo;


import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.RegisterUserRequest;
import com.amazonaws.lambda.demo.http.RegisterUserResponse;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class RegisterUserHandlerTest extends LambdaTest{

	/**
	 * Helper method that creates a context that supports logging so you can test lambda functions
	 * in JUnit without worrying about the logger anymore.
	 * 
	 * @param apiCall      An arbitrary string to identify which API is being called.
	 * @return
	 */
	Context createContext(String apiCall) {
		TestContext ctx = new TestContext();
		ctx.setFunctionName(apiCall);
		return ctx;
	}
	
	void testSuccessInput(String incoming, String outgoing) throws IOException {
		RegisterUserHandler handler = new RegisterUserHandler();
		RegisterUserRequest req = new Gson().fromJson(incoming, RegisterUserRequest.class);
		RegisterUserResponse response = handler.handleRequest(req, createContext("register"));

		//result is not good, maybe return a string saying that the user was registered?
		Assert.assertEquals(outgoing, response.loggedInUser); //change to something else???
		Assert.assertEquals(200, response.statusCode);
	}

	void testFailInput(String incoming, String outgoing) throws IOException {
		RegisterUserHandler handler = new RegisterUserHandler();
		RegisterUserRequest req = new Gson().fromJson(incoming, RegisterUserRequest.class);

		RegisterUserResponse response = handler.handleRequest(req, createContext("register"));

		Assert.assertEquals(400, response.statusCode);
		Assert.assertEquals(outgoing, response.error);
	}
	
	@Test
	public void testRegistration() {//Test successful registration with password
		//Create test choice
		ChoicesDAO dao = new ChoicesDAO();
		String choiceID = "12";
		Choice c = new Choice(choiceID,"Test Choice 2");

		try {
			dao.addChoice(c);
		} catch (Exception e) {
			e.printStackTrace(); 	//couldnt add
		}


		RegisterUserRequest rur = new RegisterUserRequest("CornFlakes", "1234");
		rur.setChoiceID(choiceID);

		RegisterUserResponse response = new RegisterUserResponse("CornFlakes", "1234",choiceID,200);

		String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
		String SAMPLE_RESPONSE_STRING = response.loggedInUser; //change to something else???

		try {
			testSuccessInput(SAMPLE_INPUT_STRING,SAMPLE_RESPONSE_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}

		try {
			dao.deleteChoice(c);
		} catch (Exception e) {
			e.printStackTrace(); //couldnt delete
		}

	}
	
	@Test
	public void testNoPasswordRegistration() { //Test successful registration without password
		//Create test choice
		ChoicesDAO dao = new ChoicesDAO();
		String choiceID = "11";
		Choice c = new Choice(choiceID,"Test Choice 1");

		try {
			dao.addChoice(c);
		} catch (Exception e) {
			e.printStackTrace();//couldnt add
		}

		RegisterUserRequest rur = new RegisterUserRequest("Cheerios" ,null);
		rur.setChoiceID(choiceID);

		RegisterUserResponse response = new RegisterUserResponse("Cheerios", "No password was given.",choiceID,200);

		String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
		String SAMPLE_RESPONSE_STRING = response.loggedInUser; //change to something else???

		try {
			testSuccessInput(SAMPLE_INPUT_STRING,SAMPLE_RESPONSE_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}

		try {
			dao.deleteChoice(c);
		} catch (Exception e) {
			e.printStackTrace(); //couldnt delete
		}
	}
	
	@Test
	public void testInvalidIDLength() //Test non-valid choice ID length
	{
		RegisterUserRequest rur = new RegisterUserRequest("Lucky Charms" ,null);
		rur.setChoiceID("100000000000000000000000");

		RegisterUserResponse response = new RegisterUserResponse(400,"ChoiceID is not valid or does not exist");

		String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
		String SAMPLE_RESPONSE_STRING = response.error;

		try {
			testFailInput(SAMPLE_INPUT_STRING,SAMPLE_RESPONSE_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}

	}

	@Test
	public void testInvalidChoice() //Test non-existent choice ID
	{
		RegisterUserRequest rur = new RegisterUserRequest("Frosted Flakes" ,null);
		rur.setChoiceID("20000a");

		RegisterUserResponse response = new RegisterUserResponse(400,"ChoiceID is not valid or does not exist");

		String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
		String SAMPLE_RESPONSE_STRING = response.error;

		try {
			testFailInput(SAMPLE_INPUT_STRING,SAMPLE_RESPONSE_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}

	}


}
