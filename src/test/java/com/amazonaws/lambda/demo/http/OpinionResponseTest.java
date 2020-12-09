package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class OpinionResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		
		List<String> approvers = new ArrayList<String>();
		List<String> disapprovers = new ArrayList<String>();
		OpinionResponse or = new OpinionResponse("12345", 2, 2, approvers, disapprovers, "error", 200);
		ObjectMapper rep = new ObjectMapper();
		
		assertEquals("12345", or.alternativeID);
		assertEquals(2, or.approvals);
		assertEquals(2, or.disapprovals);
		
		//or = new OpinionResponse("12345", approvers, disapprovers, "error", 200);
		
		or = new OpinionResponse("whoop I dropped my chocolate bar", 400);
		rep = new ObjectMapper();
		assertEquals("whoop I dropped my chocolate bar", or.error);
		assertEquals(400, or.statusCode);

		/*
		actualRep = rep.readTree(or.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my chocolate bar");
		*/
	}

}
