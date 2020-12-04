package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddApprovalRequestTest {

	@Test
	void test() {
		AddApprovalRequest aar = new AddApprovalRequest();
		aar.setUsername("Billy");
		assertEquals("Billy", aar.getUsername());
		
		aar.setAlternativeID("12345");
		assertEquals("12345", aar.getAlternativeID());
		
		aar.setChoiceID("7654");
		assertEquals("7654", aar.getChoiceID());
		
		aar = new AddApprovalRequest("Billy", "9000","12345", "7654");
		assertEquals("Billy", aar.getUsername());
		assertEquals("9000", aar.getApprovingUserID());
		assertEquals("12345", aar.getAlternativeID());
		assertEquals("7654", aar.getChoiceID());
	}

}
