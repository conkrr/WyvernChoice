package com.amazonaws.lambda.demo;


import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import com.amazonaws.lambda.demo.http.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class AddDisapprovalHandlerTest extends LambdaTest
{
//************************* THIS CLASS IS JUST AN OUTLINE *************************

	OpinionResponse testSuccessInput(String incoming) throws IOException
    {
        AddDisapprovalHandler  handler = new AddDisapprovalHandler();
        AddDisapprovalRequest  req = new Gson().fromJson(incoming, AddDisapprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff
        OpinionResponse resp = handler.handleRequest(req, createContext("adddisapproval"));

        //Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        AddDisapprovalHandler  handler = new AddDisapprovalHandler();
        AddDisapprovalRequest req = new Gson().fromJson(incoming, AddDisapprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff

        OpinionResponse resp = handler.handleRequest(req, createContext("adddisapproval"));
        //Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException { //TODO: might be able to remove this entirely

    	OpinionResponse resp = testSuccessInput(incoming);

        //return new Gson().toJson(resp.disapprovalGson); //TODO: fix

        return null; //TODO: return actual result
    }

    // NOTE: this proliferates large number of constants! Be mindful\
    @Test
    public void testAddDisapproval() {

      /*
        Create parameters for AddDisapprovalRequest
      */

        String userId = UUID.randomUUID().toString();
        String alternativeID = UUID.randomUUID().toString();
        String choiceID = UUID.randomUUID().toString();
        String approvingUser = "";

        Random random = new Random();
        for(int i = 0; i < random.nextInt(8)+3; i++) {
            approvingUser += (char) random.nextInt(91) + 65;
        }



        AddDisapprovalRequest req = new AddDisapprovalRequest(userId,alternativeID,choiceID);
        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this
    }

    @Test
    public void testAddDisapprovalOnApproval() {


        String userId = UUID.randomUUID().toString();
        String alternativeID = UUID.randomUUID().toString();
        String choiceID = UUID.randomUUID().toString();
        String approvingUser = "";
        Random random = new Random();
        for(int i = 0; i < random.nextInt(8)+3; i++) {
            approvingUser += (char) random.nextInt(91) + 65;
        }

        //Add approval
        AddApprovalRequest areq = new AddApprovalRequest(userId,alternativeID,choiceID);

        AddApprovalHandler  ahandler = new AddApprovalHandler();
        OpinionResponse resp = ahandler.handleRequest(areq, createContext("addapproval"));

        //add disapproval

        AddDisapprovalRequest req = new AddDisapprovalRequest(userId,alternativeID,choiceID);
        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this
    }

    //Test fresh disapproval:  no response -> disapproval
    //Test switching:  disdisapproval -> Disapproval
    //Test duplicate: Disapproval -> Disapproval


}
