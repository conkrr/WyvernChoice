package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeleteChoicesRequestTest {

	@Test
	void test() {
		DeleteChoicesRequest dcr = new DeleteChoicesRequest();
		dcr.setTime(24);
		assertEquals(24, dcr.getTime());
		
		
		dcr = new DeleteChoicesRequest(24);
		assertEquals(24, dcr.getTime());

	}

}
