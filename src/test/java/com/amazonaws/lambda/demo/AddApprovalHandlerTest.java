package com.amazonaws.lambda.demo;


import java.io.IOException;

import java.sql.Timestamp;
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



    AddApprovalResponse testSuccessInput(String incoming) throws IOException
    {
        AddApprovalHandler  handler = new AddApprovalHandler();
        AddApprovalRequest  req = new Gson().fromJson(incoming, AddApprovalRequest.class);


        AddApprovalResponse resp = handler.handleRequest(req, createContext("addapproval"));

        Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        AddApprovalHandler  handler = new AddApprovalHandler();
        AddApprovalRequest req = new Gson().fromJson(incoming, AddApprovalRequest.class);

        AddApprovalResponse resp = handler.handleRequest(req, createContext("addapproval"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException
    {
        AddApprovalResponse resp = testSuccessInput(incoming);

        return new Gson().toJson(resp);
    }


    @Test
    public void testAddApproval() { //Test fresh approval:  no response -> approval  /TODO: currently broken

//        String approvingUser = "ShaneTest";
//        String userId = "141307d5-101d-4fc2-b1d0-0685bdd8ab9c"; //shane ipad
//        String alternativeID = "05f62ea0-98dc-4699-8d66-a7075195e1a5"; //ipad 1
//        String choiceID = "936219db-1f89-4d71-b3ab-cb58011d821e";
        String userId = UUID.randomUUID().toString();
        String alternativeID = UUID.randomUUID().toString();
        String choiceID = UUID.randomUUID().toString();
		String approvingUser = "";

		Random random = new Random();
		for(int i = 0; i < random.nextInt(8)+3; i++) {
			approvingUser += (char) random.nextInt(91) + 65;
		}



        AddApprovalRequest req = new AddApprovalRequest(approvingUser,userId,alternativeID,choiceID);

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


    @Test
    public void testAddApprovalInvalidInput() {  //Test fresh approval:  no response -> approval

        String approvingUser = "not a user";
        String alternativeID = "not an id that exists"; //ipad 1
        String choiceID = "not a choice that exists"; //ipad
        String userId = "not a use that exists"; //shane ipad


        /*



        AddApprovalRequest req = new AddApprovalRequest(approvingUser,userId,alternativeID,choiceID);

        String SAMPLE_INPUT_STRING = new Gson().toJson(req);
        String jsonResp;

		try {
			testFailInput(SAMPLE_INPUT_STRING,400);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}

        //TODO: Delete approval

        Assert.assertEquals(1, 1); // should probably refactor this

        */
    }


    //Test switching:  disapproval -> Approval
    //Test duplicate: Approval -> Approval



    /*



            List<Approval> approvalsList = null;
                try {

                approvalsList = approvalsDAO.get(req.getAlternativeID());
            } catch (Exception e) {
                System.out.println("Alt id canot be found");
                e.printStackTrace();
            }

            boolean exists = approvalsList.stream().anyMatch(a -> a.getUserId().equals(userId)); // this is either very cool or very bad

                if (exists) //should be true
            {
                System.out.println("DOES EXIST!!!!");
                try
                {
                    approvalsDAO.delete(alternativeID);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
     */

}
