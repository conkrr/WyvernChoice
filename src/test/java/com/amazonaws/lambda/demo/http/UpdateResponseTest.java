package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.amazonaws.lambda.demo.model.Choice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class UpdateResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		//Fill in testChoice later when Jason does things
		
		Choice testChoice = new Choice("123", "What are we eating?");
		UpdateResponse ur = new UpdateResponse(testChoice, 200);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(ur.toString());
		//String rep = rur.toString();
		assertEquals(actualRep.get("id").asText(), "123");
		assertEquals(actualRep.get("description").asText(), "What are we eating?");
		assertEquals(actualRep.get("statusCode").asText(), "200");
		
		ur = new UpdateResponse(400, "oop");
		rep = new ObjectMapper();
		actualRep = rep.readTree(ur.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "oop");
		
	}

}
