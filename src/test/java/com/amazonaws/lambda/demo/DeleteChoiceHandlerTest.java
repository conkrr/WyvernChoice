package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.DeleteChoiceResponse;
import com.amazonaws.lambda.demo.http.DeleteChoiceRequest;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DeleteChoiceHandlerTest  extends LambdaTest
{

//************************* THIS CLASS IS JUST AN OUTLINE *************************


    DeleteChoiceResponse testSuccessInput(String incoming) throws IOException
    {
        DeleteChoiceHandler  handler = new DeleteChoiceHandler();
        DeleteChoiceRequest req = new Gson().fromJson(incoming, DeleteChoiceRequest.class); //TODO: Fix after we remove GSON compatible stuff
        DeleteChoiceResponse resp = handler.handleRequest(req, createContext("admin/{param  }"/*TODO*/)); //TODO: look up in the lectures how to test this...cuz this isnt right

        //Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        DeleteChoiceHandler  handler = new DeleteChoiceHandler();
        DeleteChoiceRequest req = new Gson().fromJson(incoming, DeleteChoiceRequest.class); //TODO: Fix after we remove GSON compatible stuff

        DeleteChoiceResponse resp = handler.handleRequest(req, createContext("removedisapproval"));
        //Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException { //TODO: might be able to remove this entirely

        DeleteChoiceResponse resp = testSuccessInput(incoming);

        //return new Gson().toJson(resp.deletChoiceGson); //TODO: fix

        return null; //TODO: return actual result
    }

    // NOTE: this proliferates large number of constants! Be mindful\
    @Test
    public void testDeleteChoice() {

      /*
        Create parameters for DeleteChoiceRequest
      */

        DeleteChoiceRequest req = new DeleteChoiceRequest(/* add parameters here*/);
        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this
    }

    //TODO: TEst delete choice that doesnt exist


    
    
}
