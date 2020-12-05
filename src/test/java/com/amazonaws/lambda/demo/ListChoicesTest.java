package com.amazonaws.lambda.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.amazonaws.lambda.demo.http.GetListOfChoicesResponse;
import com.amazonaws.lambda.demo.model.Choice;

class ListChoicesTest  extends LambdaTest{

	@Test
	void test(){
		GetListOfChoicesHandler handler = new GetListOfChoicesHandler();

        GetListOfChoicesResponse resp = handler.handleRequest(null, createContext("list"));
        
        for (Choice c : resp.listOfChoices) {
        	System.out.println("found choice " + c);
        }
        assertEquals(200, resp.statusCode);
	}

}