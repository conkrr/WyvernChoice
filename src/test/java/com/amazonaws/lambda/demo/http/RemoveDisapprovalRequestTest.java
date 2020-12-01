package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveDisapprovalRequestTest {

	@Test
	void test() {
		RemoveDisapprovalRequest rdr = new RemoveDisapprovalRequest();
		rdr.setDisapprovingUser("Billy");
		assertEquals("Billy", rdr.disapprovingUser);
		
		rdr.setAlternativeID("12345");
		assertEquals("12345", rdr.alternativeID);
		
		rdr = new RemoveDisapprovalRequest("Billy", "12345");
		assertEquals("Billy", rdr.disapprovingUser);
		assertEquals("12345", rdr.alternativeID);
	}

}
