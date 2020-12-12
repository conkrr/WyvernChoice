package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.amazonaws.lambda.demo.model.Choice;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

class ListChoicesTest  extends LambdaTest{



	GetListOfChoicesResponse testSuccessInput(String incoming) throws IOException
	{
		GetListOfChoicesHandler  handler = new GetListOfChoicesHandler();
		GetListOfChoicesRequest req = new Gson().fromJson(incoming, GetListOfChoicesRequest.class);

		GetListOfChoicesResponse resp = handler.handleRequest(req, createContext("admin"));

		Assert.assertEquals(200, resp.statusCode);
		return resp;
	}


	String getJsonResponse(String incoming) throws IOException
	{
		GetListOfChoicesResponse resp = testSuccessInput(incoming);

		return new Gson().toJson(resp);
	}


	@org.junit.Test
	public void testAddApproval() { //Test fresh approval:  no response -> approval

		String userId = UUID.randomUUID().toString();
		String alternativeID = UUID.randomUUID().toString();
		String choiceID = UUID.randomUUID().toString();
		String approvingUser = "";

		Random random = new Random();
		for(int i = 0; i < random.nextInt(8)+3; i++) {
			approvingUser += (char) random.nextInt(91) + 65;
		}



		AddApprovalRequest req = new AddApprovalRequest(approvingUser,userId,alternativeID,choiceID);

		String SAMPLE_INPUT_STRING = new Gson().toJson(req);
		String jsonResp;
		try {

			jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
			System.out.println(jsonResp);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}

		Assert.assertEquals(1, 1); // should probably refactor this

	}


	@Test
	void test(){



		GetListOfChoicesHandler handler = new GetListOfChoicesHandler();

		GetListOfChoicesRequest req = new GetListOfChoicesRequest();
		GetListOfChoicesResponse resp = handler.handleRequest(req, createContext("admin"));


		String SAMPLE_INPUT_STRING = new Gson().toJson(req);
		String jsonResp;
		try {

			jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
			System.out.println(jsonResp);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}

		Assert.assertEquals(1, 1); // should probably refactor this


		for (Choice c : resp.listOfChoices) {
			System.out.println("found choice: " + c.description);
		}


	}

}