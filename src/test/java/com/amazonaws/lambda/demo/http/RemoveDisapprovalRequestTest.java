package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveDisapprovalRequestTest {

	@Test
	void test() {
		RemoveDisapprovalRequest rdr = new RemoveDisapprovalRequest();
		rdr.setDisapprovingUser("Billy");
		assertEquals("Billy", rdr.getDisappearingUser());
		
		rdr.setAlternativeID("12345");
		assertEquals("12345", rdr.getAlternativeID());
		
		rdr = new RemoveDisapprovalRequest("Billy", "12345", "6789");
		assertEquals("Billy", rdr.getDisappearingUser());
		assertEquals("12345", rdr.getAlternativeID());
		assertEquals("6789", rdr.getChoiceID());
	}

}
