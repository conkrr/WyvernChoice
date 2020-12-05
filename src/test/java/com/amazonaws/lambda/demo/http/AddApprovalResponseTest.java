package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class AddApprovalResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		AddApprovalResponse aar = new AddApprovalResponse("Tommy", "1s4h7", "auio9", 3, 2);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(aar.toString());
		assertEquals(actualRep.get("approvingUser").asText(), "Tommy");
		assertEquals(actualRep.get("alternativeID").asText(), "1s4h7");
		assertEquals(actualRep.get("choiceID").asText(), "auio9");
		assertEquals(actualRep.get("approvals").asText(), "3");
		assertEquals(actualRep.get("disapprovals").asText(), "2");
		
		aar = new AddApprovalResponse(400, "whoop I dropped my chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(aar.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my chocolate bar");
	}

}
