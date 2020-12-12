package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.CreateChoiceRequest;
import com.amazonaws.lambda.demo.http.CreateFeedbackRequest;
import com.amazonaws.lambda.demo.http.FeedbackResponse;
import com.amazonaws.lambda.demo.http.GetChoiceResponse;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CreateFeedbackHandlerTest extends LambdaTest
{

    FeedbackResponse testSuccessInput(String incoming) throws IOException {
        CreateFeedbackHandler handler = new CreateFeedbackHandler();
        CreateFeedbackRequest req = new Gson().fromJson(incoming, CreateFeedbackRequest.class);
        FeedbackResponse resp = handler.handleRequest(req, createContext("feedback"));
        Assert.assertEquals(200, resp.status);
        return resp;
    }



    String getJsonResponse(String incoming) throws IOException {

        FeedbackResponse resp = testSuccessInput(incoming);

        return new Gson().toJson(resp);
    }

    @Test
    public void testAddFeedback() {

        //create dummy choice
        CreateChoiceRequest req = new CreateChoiceRequest( "JunitTestFeedback",  null,  5, Arrays.asList("JU_Feedback1", "JU_Feedback2", "JU_Feedback3", "20JU_Feedback4"));
        CreateChoiceHandler handler = new CreateChoiceHandler();
        GetChoiceResponse resp = handler.handleRequest(req, createContext("create"));



        CreateFeedbackRequest ccr = new CreateFeedbackRequest("Donald","Yuuge", resp.listofAlternatives.get(0).getId());
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
