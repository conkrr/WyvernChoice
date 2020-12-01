package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveApprovalRequestTest {

	@Test
	void test() {
		RemoveApprovalRequest rar = new RemoveApprovalRequest();
		rar.setUser("Billy");
		assertEquals("Billy", rar.getUser());
		
		rar.setAlternativeID("12345");
		assertEquals("12345", rar.getAlternativeID());
		
		rar = new RemoveApprovalRequest("Billy", "12345", "6789");
		assertEquals("Billy", rar.getUser());
		assertEquals("12345", rar.getAlternativeID());
		assertEquals("6789", rar.getChoiceID());
	}

}
