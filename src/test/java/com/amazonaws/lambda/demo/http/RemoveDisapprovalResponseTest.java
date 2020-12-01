package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class RemoveDisapprovalResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		RemoveDisapprovalResponse rdr = new RemoveDisapprovalResponse("Tommy", "1s4h7", "auio9");
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(rdr.toString());
		assertEquals(actualRep.get("disapprovingUser").asText(), "Tommy");
		assertEquals(actualRep.get("alternativeID").asText(), "1s4h7");
		assertEquals(actualRep.get("choiceID").asText(), "auio9");
		
		rdr = new RemoveDisapprovalResponse(400, "whoop I dropped my chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(rdr.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my chocolate bar");
	}

}
