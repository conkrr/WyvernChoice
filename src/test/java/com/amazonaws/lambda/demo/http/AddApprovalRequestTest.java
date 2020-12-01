package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddApprovalRequestTest {

	@Test
	void test() {
		AddApprovalRequest aar = new AddApprovalRequest();
		aar.setApprovingUser("Billy");
		assertEquals("Billy", aar.getUser());
		
		aar.setAlternativeID("12345");
		assertEquals("12345", aar.alternativeID);
		
		aar.setChoiceID("678910");
		assertEquals("678910", aar.choiceID);
		
		aar = new AddApprovalRequest("Billy", "12345", "678910");
		assertEquals("Billy", aar.approvingUser);
		assertEquals("12345", aar.alternativeID);
		assertEquals("678910", aar.choiceID);
	}

}
