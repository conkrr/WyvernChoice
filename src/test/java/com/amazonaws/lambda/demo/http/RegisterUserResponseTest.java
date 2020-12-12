package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

class RegisterUserResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		


		User u = new User("897", "12345", "King Roland" );

		RegisterUserResponse rur = new RegisterUserResponse(u);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(rur.toString());


		//String rep = rur.toString();
		System.out.println(actualRep.toString());
		assertEquals(actualRep.get("userID").asText(), "King Roland");
		assertEquals(actualRep.get("password").asText(), "12345");
		assertEquals(actualRep.get("choiceID").asText(), "897");
		assertEquals(actualRep.get("statusCode").asText(), "200");


		rur = new RegisterUserResponse(400, "oops that's an error");
		rep = new ObjectMapper();
		actualRep = rep.readTree(rur.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "oops that's an error");


	}

}
