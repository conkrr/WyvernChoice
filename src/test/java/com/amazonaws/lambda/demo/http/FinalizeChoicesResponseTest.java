package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class FinalizeChoicesResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		FinalizeChoiceResponse fcr = new FinalizeChoiceResponse("6789", "12345", false);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(fcr.toString());
		assertEquals(actualRep.get("choiceID").asText(), "6789");
		assertEquals(actualRep.get("alternativeID").asText(), "12345");
		assertEquals(actualRep.get("isFinalized").asText(), "false");
		
		fcr = new FinalizeChoiceResponse(400, "whoop I dropped my chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(fcr.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my chocolate bar");
	}

}
