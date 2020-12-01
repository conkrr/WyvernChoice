package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddDisapprovalRequestTest {

	@Test
	void test() {
		AddDisapprovalRequest adr = new AddDisapprovalRequest();
		adr.setUsername("Billy");
		assertEquals("Billy", adr.getUsername());
		
		adr.setAlternativeID("12345");
		assertEquals("12345", adr.getAlternativeID());
		
		adr.setChoiceID("7654");
		assertEquals("7654", adr.getChoiceID());
		
		adr = new AddDisapprovalRequest("Billy", "9000","12345", "7654");
		assertEquals("Billy", adr.getUsername());
		assertEquals("12345", adr.getAlternativeID());
		assertEquals("7654", adr.getChoiceID());
	}

}
