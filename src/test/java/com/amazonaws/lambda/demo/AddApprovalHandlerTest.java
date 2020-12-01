package com.amazonaws.lambda.demo;


import java.io.IOException;

import com.amazonaws.lambda.demo.http.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class AddApprovalHandlerTest extends LambdaTest {

    //************************* THIS CLASS IS JUST AN OUTLINE *************************


    AddApprovalResponse testSuccessInput(String incoming) throws IOException
    {
        AddApprovalHandler  handler = new AddApprovalHandler();
        AddApprovalRequest  req = new Gson().fromJson(incoming, AddApprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff
        AddApprovalResponse resp = handler.handleRequest(req, createContext("addapproval"));

        //Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        AddApprovalHandler  handler = new AddApprovalHandler();
        AddApprovalRequest req = new Gson().fromJson(incoming, AddApprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff

        AddApprovalResponse resp = handler.handleRequest(req, createContext("addapproval"));
        //Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException { //TODO: might be able to remove this entirely

        AddApprovalResponse resp = testSuccessInput(incoming);

        //return new Gson().toJson(resp.approvalGson); //TODO: fix

        return null; //TODO: return actual result
    }

    // NOTE: this proliferates large number of constants! Be mindful\
    @Test
    public void testAddApproval() {

      /*
        Create parameters for AddApprovalRequest
      */

        AddApprovalRequest req = new AddApprovalRequest(/* add parameters here*/);
        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this
    }

    //Test fresh approval:  no response -> approval
    //Test switching:  disapproval -> Approval
    //Test duplicate: Approval -> Approval


}
