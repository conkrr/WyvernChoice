package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddDisapprovalRequestTest {

	@Test
	void test() {
		AddDisapprovalRequest adr = new AddDisapprovalRequest();
		adr.setDisapprovingUser("Billy");
		assertEquals("Billy", adr.disapprovingUser);
		
		adr.setAlternativeID("12345");
		assertEquals("12345", adr.alternativeID);
		
		adr = new AddDisapprovalRequest("Billy", "12345");
		assertEquals("Billy", adr.disapprovingUser);
		assertEquals("12345", adr.alternativeID);
	}

}
