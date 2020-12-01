package com.amazonaws.lambda.demo;


import java.io.IOException;

import com.amazonaws.lambda.demo.http.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class RemoveApprovalHandlerTest extends LambdaTest {

//************************* THIS CLASS IS JUST AN OUTLINE *************************


    RemoveApprovalResponse testSuccessInput(String incoming) throws IOException
    {
        RemoveApprovalHandler  handler = new RemoveApprovalHandler();
        RemoveApprovalRequest  req = new Gson().fromJson(incoming, RemoveApprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff
        RemoveApprovalResponse resp = handler.handleRequest(req, createContext("removeapproval"));

        //Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        RemoveApprovalHandler  handler = new RemoveApprovalHandler();
        RemoveApprovalRequest req = new Gson().fromJson(incoming, RemoveApprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff

        RemoveApprovalResponse resp = handler.handleRequest(req, createContext("removeapproval"));
        //Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException { //TODO: might be able to remove this entirely

        RemoveApprovalResponse resp = testSuccessInput(incoming);

        //return new Gson().toJson(resp.approvalGson); //TODO: fix

        return null; //TODO: return actual result
    }

    // NOTE: this proliferates large number of constants! Be mindful\
    @Test
    public void testRemoveApproval() {

      /*
        Create parameters for RemoveApprovalRequest
      */

        RemoveApprovalRequest req = new RemoveApprovalRequest(/* add parameters here*/);
        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this
    }

    //TODO: Test removing an approval that isnt there
    //TODO: test removing an approval normally



}
