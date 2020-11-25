package com.amazonaws.lambda.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.RegisterUserRequest;
import com.amazonaws.lambda.demo.http.RegisterUserResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;


class RegisterUserHandlerTest {

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
	
	void testInput(String incoming, String outgoing) throws IOException {
		RegisterUserHandler handler = new RegisterUserHandler();
		RegisterUserRequest req = new Gson().fromJson(incoming, RegisterUserRequest.class);
		RegisterUserResponse response = handler.handleRequest(req, createContext("compute"));

		//result is not good, maybe return a string saying that the user was registered?
		Assert.assertEquals(outgoing, response.toString());
		Assert.assertEquals(200, response.statusCode);
	}

	void testFailInput(String incoming, String outgoing) throws IOException {
		RegisterUserHandler handler = new RegisterUserHandler();
		RegisterUserRequest req = new Gson().fromJson(incoming, RegisterUserRequest.class);

		RegisterUserResponse response = handler.handleRequest(req, createContext("compute"));

		Assert.assertEquals(400, response.statusCode);
	}
	
	@Test
	public void testRegistration() {
		String SAMPLE_INPUT_STRING = "{\"Username\": \"Cornflakes\", \"Password\": \"1234\", \"ChoiceID\": \"1234\"}";
		
		
	}
	
	@Test
	public void testNoPasswordRegistration() {
		
	}
	
	@Test
	public void testInvalidID() {
		
	}
	//Test successful registration with password
	//Test successful registration without password
	//Test non-valid choice ID

}
