package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FinalizeChoiceRequestTest {

	@Test
	void test() {
		FinalizeChoiceRequest fcr = new FinalizeChoiceRequest();
		fcr.setChoiceID("7654");
		assertEquals("7654", fcr.getChoiceID());
		
		fcr.setAlternativeID("12345");
		assertEquals("12345", fcr.getAlternativeID());
		
		fcr.setIsFinalized(false);
		assertEquals(false, fcr.getIsFinalized());
		
		fcr = new FinalizeChoiceRequest("7654", "12345", false);
		assertEquals("7654", fcr.getChoiceID());
		assertEquals("12345", fcr.getAlternativeID());
		assertEquals(false, fcr.getIsFinalized());
		
	}

}
