package com.amazonaws.lambda.demo;


import com.amazonaws.lambda.demo.http.GetChoiceRequest;
import com.amazonaws.lambda.demo.http.GetChoiceResponse;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GetChoiceHandlerTest  extends LambdaTest
{



//************************* THIS CLASS IS JUST AN OUTLINE *************************


   GetChoiceResponse testSuccessInput(String incoming) throws IOException
    {
       GetChoiceHandler  handler = new GetChoiceHandler();
       GetChoiceRequest req = new Gson().fromJson(incoming,GetChoiceRequest.class); //TODO: Fix after we remove GSON compatible stuff
       GetChoiceResponse resp = handler.handleRequest(req, createContext("admin/{param  }"/*TODO*/)); //TODO: look up in the lectures how to test this...cuz this isnt right

        //Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
       GetChoiceHandler  handler = new GetChoiceHandler();
       GetChoiceRequest req = new Gson().fromJson(incoming,GetChoiceRequest.class); //TODO: Fix after we remove GSON compatible stuff

       GetChoiceResponse resp = handler.handleRequest(req, createContext("removedisapproval"));
        //Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException { //TODO: might be able to remove this entirely

       GetChoiceResponse resp = testSuccessInput(incoming);

        //return new Gson().toJson(resp.deletChoiceGson); //TODO: fix

        return null; //TODO: return actual result
    }

    // NOTE: this proliferates large number of constants! Be mindful\
    @Test
    public void testDeleteChoice() {

      /*
        Create parameters forGetChoiceRequest
      */

       GetChoiceRequest req = new GetChoiceRequest(/* add parameters here*/);
        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this
    }

    //TODO: get choice that doesnt exist





}
