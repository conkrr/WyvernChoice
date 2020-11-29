package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddApprovalRequestTest {

	@Test
	void test() {
		AddApprovalRequest aar = new AddApprovalRequest();
		aar.setApprovingUser("Billy");
		assertEquals("Billy", aar.approvingUser);
		
		aar.setAlternativeID("12345");
		assertEquals("12345", aar.alternativeID);
		
		aar = new AddApprovalRequest("Billy", "12345");
		assertEquals("Billy", aar.approvingUser);
		assertEquals("12345", aar.alternativeID);
	}

}
