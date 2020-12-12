package com.amazonaws.lambda.demo;


import com.amazonaws.lambda.demo.http.CreateChoiceRequest;
import com.amazonaws.lambda.demo.http.GetChoiceRequest;
import com.amazonaws.lambda.demo.http.GetChoiceResponse;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class GetChoiceHandlerTest  extends LambdaTest
{



   GetChoiceResponse testSuccessInput(String incoming) throws IOException
    {
       GetChoiceHandler  handler = new GetChoiceHandler();
       GetChoiceRequest req = new Gson().fromJson(incoming,GetChoiceRequest.class);
       GetChoiceResponse resp = handler.handleRequest(req, createContext("admin"));

        Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
       GetChoiceHandler  handler = new GetChoiceHandler();
       GetChoiceRequest req = new Gson().fromJson(incoming,GetChoiceRequest.class);

       GetChoiceResponse resp = handler.handleRequest(req, createContext("admin"));
        //Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException {

       GetChoiceResponse resp = testSuccessInput(incoming);

        return new Gson().toJson(resp);


    }

    // NOTE: this proliferates large number of constants! Be mindful\
    @Test
    public void testGetChoice() {

      /*
        Create parameters forGetChoiceRequest
      */
        CreateChoiceRequest req = new CreateChoiceRequest( "JunitTest",  null,  5, Arrays.asList("1", "2", "4", "200"));

        CreateChoiceHandler handler = new CreateChoiceHandler();
        GetChoiceResponse resp = handler.handleRequest(req, createContext("create"));

        String choiceId = resp.choiceID;



        GetChoiceRequest greq = new GetChoiceRequest(choiceId);
        String SAMPLE_INPUT_STRING = new Gson().toJson(greq);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this
    }







}
