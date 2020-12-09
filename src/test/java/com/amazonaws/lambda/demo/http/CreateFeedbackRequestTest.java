package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CreateFeedbackRequestTest {

	@Test
	void test() {
		CreateFeedbackRequest cfr = new CreateFeedbackRequest();
		cfr.setUser("Billy");
		assertEquals("Billy", cfr.getUser());
		
		cfr.setText("bingbong");
		assertEquals("bingbong", cfr.getText());
		
		cfr.setAlternativeID("12345");
		assertEquals("12345", cfr.getAlternativeID());
		
		cfr = new CreateFeedbackRequest("Billy", "bingbong","12345");
		assertEquals("Billy", cfr.getUser());		
		assertEquals("bingbong", cfr.getText());
		assertEquals("12345", cfr.getAlternativeID());
	}

}
