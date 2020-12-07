package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class DeleteChoicesResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		DeleteChoicesResponse dcr = new DeleteChoicesResponse(24);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(dcr.toString());
		assertEquals(actualRep.get("time").asText(), "24.0");
		
		dcr = new DeleteChoicesResponse(400, "whoop I dropped my chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(dcr.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my chocolate bar");
	}

}
