package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class FinalizeChoiceHandlerTest  extends LambdaTest
{



    FinalizeChoiceResponse testSuccessInput(String incoming) throws IOException {
        FinalizeChoiceHandler handler = new FinalizeChoiceHandler();
        FinalizeChoiceRequest req = new Gson().fromJson(incoming, FinalizeChoiceRequest.class);
        FinalizeChoiceResponse resp = handler.handleRequest(req, createContext("finalize"));
        Assert.assertEquals(200, resp.statusCode);
        Assert.assertEquals(true, resp.isFinalized);
        return resp;
    }



    String getJsonResponse(String incoming) throws IOException {

        FinalizeChoiceResponse resp = testSuccessInput(incoming);

        return new Gson().toJson(resp);
    }

    @Test
    public void testFinalizeChoice() {

        //create dummy choice
        CreateChoiceRequest req = new CreateChoiceRequest( "JunitTestFinalize",  null,  5, Arrays.asList("JU_Finalize1", "JU_Finalize2", "JU_Finalize3", "JU_Finalize4"));
        CreateChoiceHandler handler = new CreateChoiceHandler();
        GetChoiceResponse resp = handler.handleRequest(req, createContext("create"));



        FinalizeChoiceRequest ccr = new FinalizeChoiceRequest(resp.choiceID,resp.listofAlternatives.get(0).getId(),true );
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
            System.out.println(jsonResp);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1);
    }


}
