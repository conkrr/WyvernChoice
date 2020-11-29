package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class RemoveApprovalResponseTest {

	@Test
	void test() {
		ApprovalGsonCompatible u = new ApprovalGsonCompatible("Tommy", "1s4h7");
		RemoveApprovalResponse rar = new RemoveApprovalResponse(u);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(rar.toString());
		assertEquals(actualRep.get("approvingUser").asText(), "Tommy");
		assertEquals(actualRep.get("alternativeID").asText(), "1s4h7");
		
		rar = new RemoveApprovalResponse(400, "whoop I dropped my second chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(rar.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my second chocolate bar");
	}

}
