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
    	String choiceId ="ebdd6759-675d-412f-9a06-4f0c73373297";
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
//	@Test
//	public void testNoPasswordRegistration() { //Test successful registration without password
//		//Create test choice
//		ChoicesDAO dao = new ChoicesDAO();
//		String choiceID = "11";
//		Choice c = new Choice(choiceID,"Test Choice 1");
//
//		try {
//			dao.addChoice(c);
//		} catch (Exception e) {
//			e.printStackTrace();//couldnt add
//		}
//
//		RegisterUserRequest rur = new RegisterUserRequest("Cheerios" ,null);
//		rur.setChoiceID(choiceID);
//
//		RegisterUserResponse response = new RegisterUserResponse("Cheerios", "No password was given.",choiceID,200);
//
//		String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
//		String SAMPLE_RESPONSE_STRING = response.loggedInUser; //change to something else???
//
//		try {
//			testSuccessInput(SAMPLE_INPUT_STRING,SAMPLE_RESPONSE_STRING);
//		} catch (IOException ioe) {
//			Assert.fail("Invalid:" + ioe.getMessage());
//		}
//
//		try {
//			dao.deleteChoice(c);
//		} catch (Exception e) {
//			e.printStackTrace(); //couldnt delete
//		}
//	}
//	
//	@Test
//	public void testInvalidIDLength() //Test non-valid choice ID length
//	{
//		RegisterUserRequest rur = new RegisterUserRequest("Lucky Charms" ,null);
//		rur.setChoiceID("100000000000000000000000");
//
//		RegisterUserResponse response = new RegisterUserResponse(400,"ChoiceID is not valid or does not exist");
//
//		String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
//		String SAMPLE_RESPONSE_STRING = response.error;
//
//		try {
//			testFailInput(SAMPLE_INPUT_STRING,SAMPLE_RESPONSE_STRING);
//		} catch (IOException ioe) {
//			Assert.fail("Invalid:" + ioe.getMessage());
//		}
//
//	}
//
//	@Test
//	public void testInvalidChoice() //Test non-existent choice ID
//	{
//		RegisterUserRequest rur = new RegisterUserRequest("Frosted Flakes" ,null);
//		rur.setChoiceID("20000a");
//
//		RegisterUserResponse response = new RegisterUserResponse(400,"ChoiceID is not valid or does not exist");
//
//		String SAMPLE_INPUT_STRING = new Gson().toJson(rur);
//		String SAMPLE_RESPONSE_STRING = response.error;
//
//		try {
//			testFailInput(SAMPLE_INPUT_STRING,SAMPLE_RESPONSE_STRING);
//		} catch (IOException ioe) {
//			Assert.fail("Invalid:" + ioe.getMessage());
//		}
//
//	}


}
