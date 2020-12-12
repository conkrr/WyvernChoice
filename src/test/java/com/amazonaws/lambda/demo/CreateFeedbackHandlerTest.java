package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CreateFeedbackHandlerTest extends LambdaTest
{

    FeedbackResponse testSuccessInput(String incoming) throws IOException {
        CreateFeedbackHandler handler = new CreateFeedbackHandler();
        CreateFeedbackRequest req = new Gson().fromJson(incoming, CreateFeedbackRequest.class);
        FeedbackResponse resp = handler.handleRequest(req, createContext("feedback"));

        System.out.print("resp" + resp);
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

        System.out.println("is final ? "+ resp.isFinalized);
        String name = "tester lad: "  + UUID.randomUUID().toString().substring(0,10);

        //Create new user
        RegisterUserRequest rreq = new RegisterUserRequest(name,"Testpass", resp.choiceID);
        RegisterUserHandler rhandler = new RegisterUserHandler();
        RegisterUserResponse rresp = rhandler.handleRequest(rreq, createContext("register"));



        CreateFeedbackRequest ccr = new CreateFeedbackRequest(resp.name,"nah bro thats whack", resp.listofAlternatives.get(0).getId());
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
