package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveApprovalRequestTest {

	@Test
	void test() {
		RemoveApprovalRequest rar = new RemoveApprovalRequest();
		rar.setUsername("Billy");
		assertEquals("Billy", rar.getUsername());
		
		rar.setAlternativeID("12345");
		assertEquals("12345", rar.getAlternativeID());
		
		rar = new RemoveApprovalRequest("Billy", "9000","12345", "6789");
		assertEquals("Billy", rar.getUsername());
		assertEquals("12345", rar.getAlternativeID());
		assertEquals("6789", rar.getChoiceID());
	}

}
