package com.amazonaws.lambda.demo;


import java.io.IOException;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;


import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Approval;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class AddApprovalHandlerTest extends LambdaTest {



    OpinionResponse testSuccessInput(String incoming) throws IOException
    {
        AddApprovalHandler  handler = new AddApprovalHandler();
        AddApprovalRequest  req = new Gson().fromJson(incoming, AddApprovalRequest.class);


        OpinionResponse resp = handler.handleRequest(req, createContext("addapproval"));

        Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        AddApprovalHandler  handler = new AddApprovalHandler();
        AddApprovalRequest req = new Gson().fromJson(incoming, AddApprovalRequest.class);

        OpinionResponse resp = handler.handleRequest(req, createContext("addapproval"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException
    {
    	OpinionResponse resp = testSuccessInput(incoming);

        return new Gson().toJson(resp);
    }


    @Test
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



}
