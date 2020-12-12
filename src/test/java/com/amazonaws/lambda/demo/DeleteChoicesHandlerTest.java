package com.amazonaws.lambda.demo;
import java.io.IOException;
import com.amazonaws.lambda.demo.http.*;
import org.junit.Assert;
import org.junit.Test;
import com.google.gson.Gson;

public class DeleteChoicesHandlerTest  extends LambdaTest
{
    DeleteChoicesResponse testSuccessInput(String incoming) throws IOException {
        DeleteChoicesHandler handler = new DeleteChoicesHandler();
        DeleteChoicesRequest req = new Gson().fromJson(incoming, DeleteChoicesRequest.class);
        DeleteChoicesResponse resp = handler.handleRequest(req, createContext("admin"));
        Assert.assertEquals(200, resp.statusCode);
        return resp;
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        DeleteChoicesHandler handler = new DeleteChoicesHandler();
        DeleteChoicesRequest req = new Gson().fromJson(incoming, DeleteChoicesRequest.class);

        DeleteChoicesResponse resp = handler.handleRequest(req, createContext("admin"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

    String getJsonResponse(String incoming) throws IOException {

        DeleteChoicesResponse resp = testSuccessInput(incoming);

        return new Gson().toJson(resp);
    }

    @Test
    public void testDeleteChoices()
    {

        float deleteTime = 3;  //in days ex: 2.5


        DeleteChoicesRequest ccr = new DeleteChoicesRequest(deleteTime);
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
