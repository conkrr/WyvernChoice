package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveApprovalRequestTest {

	@Test
	void test() {
		RemoveApprovalRequestTest rar = new RemoveApprovalRequestTest();
		rar.setApprovingUser("Billy");
		assertEquals("Billy", rar.approvingUser);
		
		rar.setAlternativeID("12345");
		assertEquals("12345", rar.alternativeID);
		
		rar = new RemoveApprovalRequestTest("Billy", "12345");
		assertEquals("Billy", rar.approvingUser);
		assertEquals("12345", rar.alternativeID);
	}

}
