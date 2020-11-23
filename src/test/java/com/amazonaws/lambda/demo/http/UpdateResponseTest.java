package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.amazonaws.lambda.demo.model.Choice;

class UpdateResponseTest {

	@Test
	void test() {
		//Fill in testChoice later when Jason does things
		Choice testChoice = new Choice("123", "What are we eating?");
		UpdateResponse ur = new UpdateResponse(testChoice, 200);
		String rep = ur.toString();
		assertTrue(rep.startsWith("Choice"));
		
		ur = new UpdateResponse(400, "I farted");
		rep = ur.toString();
		assertTrue(rep.startsWith("Error"));
	}

}
