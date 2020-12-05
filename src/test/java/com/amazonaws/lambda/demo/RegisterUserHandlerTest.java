package com.amazonaws.lambda.demo;

//com.amazonaws.lambda.demo.RegisterUserHandlerTest
import com.amazonaws.lambda.demo.http.RegisterUserRequest;
import com.amazonaws.lambda.demo.http.RegisterUserResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class RegisterUserHandlerTest extends LambdaTest{



	RegisterUserResponse testSuccessInput(String incoming) throws IOException {
    	RegisterUserHandler handler = new RegisterUserHandler();
    	RegisterUserRequest req = new Gson().fromJson(incoming, RegisterUserRequest.class);
    	RegisterUserResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
        return resp;
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	RegisterUserHandler handler = new RegisterUserHandler();
    	RegisterUserRequest req = new Gson().fromJson(incoming, RegisterUserRequest.class);

    	RegisterUserResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException {

	    RegisterUserResponse resp = testSuccessInput(incoming);
        
       return new Gson().toJson(resp.userGson);  
    }

    // NOTE: this proliferates large number of constants! Be mindful\
    //Also this fails, throws a 400
    @Test
    public void testRegisterUser() {

    	String name = "meme?";
    	String choiceId ="936219db-1f89-4d71-b3ab-cb58011d821e";
    	String password = "password12";
    	
    	RegisterUserRequest rur = new RegisterUserRequest(name, password, choiceId);
        String SAMPLE_INPUT_STRING = new Gson().toJson(rur);  
        String jsonResp;
        try {
        	
            	jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
      
        Assert.assertEquals(1, 1);
    }

    //TODO: testNoPasswordRegistration
    //TODO: testInvalidIDLength
    //TODO: testInvalidChoice
	@Test
	public void testNoPasswordRegistration() { //Test successful registration without password
        String name = "UserNoPass";
        String choiceId ="936219db-1f89-4d71-b3ab-cb58011d821e";
        String password = "";

        RegisterUserRequest rur = new RegisterUserRequest(name, password, choiceId);
        String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1);
	}
/*

	@Test
	public void testInvalidIDLength() //Test non-valid choice ID length
	{
        String name = "UserPassInvalid";
        String choiceId ="900000000000000000000000000000000000000000";
        String password = "";

        RegisterUserRequest rur = new RegisterUserRequest("Frosted Flakes" ,null, choiceId);

        RegisterUserResponse response = new RegisterUserResponse(400,"ChoiceID is not valid or does not exist");

        String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
        try {
            testFailInput(SAMPLE_INPUT_STRING, response.statusCode);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

	}

	@Test
public void testInvalidChoice() //Test non-existent choice ID
	{

        String name = "UserPassInvalid";
        String choiceId ="not a real choice id";
        String password = "";

		RegisterUserRequest rur = new RegisterUserRequest("Frosted Flakes" ,null, choiceId);

		RegisterUserResponse response = new RegisterUserResponse(400,"ChoiceID is not valid or does not exist");

		String SAMPLE_INPUT_STRING = new Gson().toJson(rur);

	    try {
			testFailInput(SAMPLE_INPUT_STRING, response.statusCode);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}

	}
*/


}
