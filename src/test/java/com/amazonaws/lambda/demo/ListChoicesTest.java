package com.amazonaws.lambda.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.amazonaws.lambda.demo.http.AddDisapprovalRequest;
import com.amazonaws.lambda.demo.http.GetListOfChoicesRequest;
import org.junit.jupiter.api.Test;

import com.amazonaws.lambda.demo.http.GetListOfChoicesResponse;
import com.amazonaws.lambda.demo.model.Choice;

class ListChoicesTest  extends LambdaTest{

	@Test
	void test(){
		GetListOfChoicesHandler handler = new GetListOfChoicesHandler();


		GetListOfChoicesRequest req = new GetListOfChoicesRequest();

        GetListOfChoicesResponse resp = handler.handleRequest(req, createContext("admin"));
        
        for (Choice c : resp.listOfChoices) {
        	System.out.println("found choice: " + c.description);
        }
        assertEquals(200, resp.statusCode);
	}

}