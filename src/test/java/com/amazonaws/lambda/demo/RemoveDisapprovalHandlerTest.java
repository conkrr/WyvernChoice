package com.amazonaws.lambda.demo;


import java.io.IOException;

import com.amazonaws.lambda.demo.http.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class RemoveDisapprovalHandlerTest extends LambdaTest
{

    //************************* THIS CLASS IS JUST AN OUTLINE *************************

    RemoveDisapprovalResponse testSuccessInput(String incoming) throws IOException
    {
        RemoveDisapprovalHandler  handler = new RemoveDisapprovalHandler();
        RemoveDisapprovalRequest  req = new Gson().fromJson(incoming, RemoveDisapprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff
        RemoveDisapprovalResponse resp = handler.handleRequest(req, createContext("removedisapproval"));

        //Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        RemoveDisapprovalHandler  handler = new RemoveDisapprovalHandler();
        RemoveDisapprovalRequest req = new Gson().fromJson(incoming, RemoveDisapprovalRequest.class); //TODO: Fix after we remove GSON compatible stuff

        RemoveDisapprovalResponse resp = handler.handleRequest(req, createContext("removedisapproval"));
        //Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException { //TODO: might be able to remove this entirely

        RemoveDisapprovalResponse resp = testSuccessInput(incoming);

        //return new Gson().toJson(resp.disapprovalGson); //TODO: fix

        return null; //TODO: return actual result
    }

    // NOTE: this proliferates large number of constants! Be mindful\
    @Test
    public void testRemoveDisapproval() {

      /*
        Create parameters for RemoveDisapprovalRequest
      */

        RemoveDisapprovalRequest req = new RemoveDisapprovalRequest(/* add parameters here*/);
        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this
    }

    //TODO: Test removing an Disapproval that isnt there

    //TODO: removing an Disapproval normally

}
