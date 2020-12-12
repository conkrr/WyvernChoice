package com.amazonaws.lambda.demo;


import java.io.IOException;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;


import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Approval;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class AddApprovalHandlerTest extends LambdaTest {



    OpinionResponse testSuccessInput(String incoming) throws IOException
    {
        AddApprovalHandler  handler = new AddApprovalHandler();
        AddApprovalRequest  req = new Gson().fromJson(incoming, AddApprovalRequest.class);


        OpinionResponse resp = handler.handleRequest(req, createContext("addapproval"));

        System.out.println("opinion response");
        Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        AddApprovalHandler  handler = new AddApprovalHandler();
        AddApprovalRequest req = new Gson().fromJson(incoming, AddApprovalRequest.class);

        OpinionResponse resp = handler.handleRequest(req, createContext("addapproval"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException
    {
    	OpinionResponse resp = testSuccessInput(incoming);

        return new Gson().toJson(resp);
    }


    @Test
    public void testAddApproval() { //Test fresh approval:  no response -> approval



        //creates new choice so this will always pass
        CreateChoiceRequest creq = new CreateChoiceRequest( "JunitTestAddApprovalyee",  null,  5, Arrays.asList("JU_AddApproval1aaa", "JU_AddApproval2aa", "JU_AddApproval3a", "JU_AddApproval4a"));
        CreateChoiceHandler chandler = new CreateChoiceHandler();
        GetChoiceResponse cresp = chandler.handleRequest(creq, createContext("create"));

        String name = "tester boy: "  + UUID.randomUUID().toString().substring(0,10);

        //Create new user
        RegisterUserRequest rreq = new RegisterUserRequest(name,"Testpass", cresp.choiceID);
        RegisterUserHandler rhandler = new RegisterUserHandler();
        RegisterUserResponse rresp = rhandler.handleRequest(rreq, createContext("register"));



        AddApprovalRequest req = new AddApprovalRequest(rresp.userID, rresp.userID, cresp.listofAlternatives.get(0).getId(),cresp.choiceID);
        System.out.println("alt id" + cresp.listofAlternatives.get(0).getId());
        System.out.println("choice id" + cresp.choiceID);
        System.out.println("response" + rresp);

        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;
        try {

            jsonResp = getJsonResponse(SAMPLE_INPUT_STRING);
            System.out.println(jsonResp);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }

        Assert.assertEquals(1, 1); // should probably refactor this

    }



}
