package com.amazonaws.lambda.demo;


import java.io.IOException;

import com.amazonaws.lambda.demo.http.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class AddDisapprovalHandlerTest extends LambdaTest
{
//************************* THIS CLASS IS JUST AN OUTLINE *************************

    AddDisapprovalResponse testSuccessInput(String incoming) throws IOException
    {
        AddDisapprovalHandler  handler = new AddDisapprovalHandler();
        AddDisapprovalRequest  req = new Gson().fromJson(incoming, AddDisapprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff
        AddDisapprovalResponse resp = handler.handleRequest(req, createContext("adddisapproval"));

        //Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        AddDisapprovalHandler  handler = new AddDisapprovalHandler();
        AddDisapprovalRequest req = new Gson().fromJson(incoming, AddDisapprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff

        AddDisapprovalResponse resp = handler.handleRequest(req, createContext("adddisapproval"));
        //Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException { //TODO: might be able to remove this entirely

        AddDisapprovalResponse resp = testSuccessInput(incoming);

        //return new Gson().toJson(resp.disapprovalGson); //TODO: fix

        return null; //TODO: return actual result
    }

    // NOTE: this proliferates large number of constants! Be mindful\
    @Test
    public void testAddDisapproval() {

      /*
        Create parameters for AddDisapprovalRequest
      */

        AddDisapprovalRequest req = new AddDisapprovalRequest(/* add parameters here*/);
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
