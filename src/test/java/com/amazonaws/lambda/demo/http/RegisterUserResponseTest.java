package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegisterUserResponseTest {

	@Test
	void test() {
		RegisterUserResponse rur = new RegisterUserResponse("King Roland", "12345", 200);
		String rep = rur.toString();
		assertTrue(rep.startsWith("Username:"));
		
		//This is probably kinda dumb
		rur = new RegisterUserResponse(400, "oops that's an error");
		rep = rur.toString();
		assertTrue(rep.startsWith("Error"));
	}

}
