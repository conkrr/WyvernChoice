package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveDisapprovalRequestTest {

	@Test
	void test() {
		RemoveDisapprovalRequest rdr = new RemoveDisapprovalRequest();
		rdr.setUsername("Billy");
		assertEquals("Billy", rdr.getUsername());
		
		rdr.setAlternativeID("12345");
		assertEquals("12345", rdr.getAlternativeID());
		
		rdr = new RemoveDisapprovalRequest("Billy", "9000","12345", "6789");
		assertEquals("Billy", rdr.getUsername());
		assertEquals("12345", rdr.getAlternativeID());
		assertEquals("6789", rdr.getChoiceID());
	}

}
