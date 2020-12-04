package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetChoiceRequestTest {

	@Test
	void test() {
		GetChoiceRequest gcr = new GetChoiceRequest();
		gcr.setChoiceID("12345");
		assertEquals("12345", gcr.getChoiceID());
		
		gcr = new GetChoiceRequest("12345");
		assertEquals("12345", gcr.getChoiceID());
		
	}

}
