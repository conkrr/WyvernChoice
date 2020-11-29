package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class AddDisapprovalResponseTest {

	@Test
	void test() {
		DisapprovalGsonCompatible u = new DisapprovalGsonCompatible("Tommy", "1s4h7");
		AddDisapprovalResponse adr = new AddDisapprovalResponse(u);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(adr.toString());
		assertEquals(actualRep.get("disapprovingUser").asText(), "Tommy");
		assertEquals(actualRep.get("alternativeID").asText(), "1s4h7");
		
		adr = new AddDisapprovalResponse(400, "whoop I dropped my third chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(adr.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my third chocolate bar");
	}

}
