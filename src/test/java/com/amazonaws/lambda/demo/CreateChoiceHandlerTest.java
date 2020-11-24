package com.amazonaws.lambda.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.CreateChoiceRequest;
import com.amazonaws.lambda.demo.http.CreateChoiceResponse;
import com.amazonaws.lambda.demo.http.DeleteChoiceRequest;
import com.amazonaws.lambda.demo.http.DeleteChoiceResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateChoiceHandlerTest extends LambdaTest {

	CreateChoiceResponse testSuccessInput(String incoming) throws IOException {
    	CreateChoiceHandler handler = new CreateChoiceHandler();
    	CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);
        CreateChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
        return resp;
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateChoiceHandler handler = new CreateChoiceHandler();
    	CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);

    	CreateChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

    String getJsonResponse(String incoming) throws IOException {
    	
        CreateChoiceResponse resp = testSuccessInput(incoming);
        
       return new Gson().toJson(resp.choice);  
    }
    // NOTE: this proliferates large number of constants! Be mindful\
    //Also this fails, throws a 400
    @Test
    public void testCreateChoice() {

    	String description = "What should we have for dinner?";
    	String creatingUserId ="01";
    	List<String> alternatives = Arrays.asList("Enchiladas", "Tacos", "Soup");
    	int maxParticipants = 4;
    	
    	CreateChoiceRequest ccr = new CreateChoiceRequest(description, creatingUserId, alternatives,maxParticipants);
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        String jsonResp;
        try {
        	//testSuccessInput(SAMPLE_INPUT_STRING);
        	jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
        
        //DeleteChoiceRequest dcr = new DeleteChoiceRequest(var);
        //DeleteChoiceResponse d_resp = new DeleteChoiceHandler().handleRequest(dcr, createContext("delete"));
        //Assert.assertEquals(var, d_resp.id);
        Assert.assertEquals(1, 1);
    }
    
    
//    @Test
//    public void testGarbageInput() {
//    	String SAMPLE_INPUT_STRING = "{\"sdsd\": \"e3\", \"hgfgdfgdfg\": \"this is not a number\"}";
//        
//        try {
//        	testFailInput(SAMPLE_INPUT_STRING, 400);
//        } catch (IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//        }
//    }
    
//    // overwrites into it
//    @Test
//    public void testCreateSystemChoice() {
//    	// create constant
//    	int rndNum = (int)(990*(Math.random()));
//        CreateChoiceRequest csr = new CreateChoiceRequest("to-delete-again" + rndNum, 9.29837, true);
//        
//        CreateChoiceHandler createHandler = new CreateChoiceHandler();
//        CreateChoiceResponse resp = createHandler.handleRequest(csr, createContext("create"));
//        Assert.assertEquals(200, resp.httpCode);
//        
//        // clean up 
//        createHandler.deleteSystemChoice("to-delete-again" + rndNum);
//    }
}
