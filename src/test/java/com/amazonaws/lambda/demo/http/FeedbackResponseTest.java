package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import com.amazonaws.lambda.demo.CreateChoiceHandler;
import com.amazonaws.lambda.demo.model.Alternative;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

class FeedbackResponseTest {

	@Test
	void test() throws IOException {

/*
		//FeedbackResponse aar = new FeedbackResponse(new Alternative());
		ObjectMapper rep = new ObjectMapper();

		JsonNode actualRep = rep.readTree(aar.toString());

		System.out.println(actualRep.toString());
		assertEquals(actualRep.get(" ").asText(), " ");


		aar = new FeedbackResponse(400, "whoop I dropped my chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(aar.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my chocolate bar");

 */
	}

}
