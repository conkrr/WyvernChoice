package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class RegisterUserRequestTest {

	@Test
	void test() {
		RegisterUserRequest rur = new RegisterUserRequest();
		rur.setLoggedInUser("King Roland");
		assertEquals("King Roland", rur.loggedInUser);
		
		rur.setPassword("12345");
		assertEquals("12345", rur.password);
		
		rur = new RegisterUserRequest("King Roland", "12345");
		assertEquals("King Roland", rur.loggedInUser);
		assertEquals("12345", rur.password);
	}

}
