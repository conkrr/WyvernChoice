package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class RegisterUserRequestTest {

	@Test
	void test() {
		RegisterUserRequest rur = new RegisterUserRequest();
		rur.setUsername("King Roland");
		assertEquals("King Roland", rur.username);
		
		rur.setPassword("12345");
		assertEquals("12345", rur.password);
		
		rur = new RegisterUserRequest("King Roland", "12345", "superduperID");
		assertEquals("King Roland", rur.username);
		assertEquals("12345", rur.password);
		assertEquals("superduperID", rur.choiceID);
	}

}
