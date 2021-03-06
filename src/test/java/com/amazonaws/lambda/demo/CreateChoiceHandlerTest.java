package com.amazonaws.lambda.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.lambda.demo.http.*;
import org.junit.Assert;

import org.junit.Test;

import com.google.gson.Gson;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateChoiceHandlerTest extends LambdaTest {

    GetChoiceResponse testSuccessInput(String incoming) throws IOException {
    	CreateChoiceHandler handler = new CreateChoiceHandler();
    	CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);
        GetChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
        return resp;
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateChoiceHandler handler = new CreateChoiceHandler();
    	CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);

    	GetChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException {

        GetChoiceResponse resp = testSuccessInput(incoming);
        
       return new Gson().toJson(resp);
    }

    @Test
    public void testCreateChoice() {

    	String description = "What is 2+2?";
    	String creatingUserId ="03";
    	List<String> alternatives = Arrays.asList("1", "2", "4", "200");
    	int maxParticipants = 4;
    	
    	CreateChoiceRequest ccr = new CreateChoiceRequest(description, creatingUserId, maxParticipants, alternatives);
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        String jsonResp;
        try {
        	
        	jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
            System.out.println(jsonResp);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
      
        Assert.assertEquals(1, 1);
    }



}
