package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddApprovalRequestTest {

	@Test
	void test() {
		AddApprovalRequest aar = new AddApprovalRequest();
		aar.setUser("Billy");
		assertEquals("Billy", aar.getUser());
		
		aar.setAlternativeID("12345");
		assertEquals("12345", aar.getAlternativeID());
		
		aar.setChoiceID("7654");
		assertEquals("7654", aar.getChoiceID());
		
		aar = new AddApprovalRequest("Billy", "12345", "7654");
		assertEquals("Billy", aar.getUser());
		assertEquals("12345", aar.getAlternativeID());
		assertEquals("7654", aar.getChoiceID());
	}

}
