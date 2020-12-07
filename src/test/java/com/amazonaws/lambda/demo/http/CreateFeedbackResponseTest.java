package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import com.amazonaws.lambda.demo.model.Feedback;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class CreateFeedbackResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		Timestamp creationDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
		Feedback testFeedback = new Feedback("Billy", "chocolate", creationDate, "12345");
		CreateFeedbackResponse cfr = new CreateFeedbackResponse(testFeedback);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(cfr.toString());
		assertEquals(actualRep.get("feedbackUser").asText(), "Billy");
		assertEquals(actualRep.get("feedbackText").asText(), "chocolate");
		assertEquals(actualRep.get("feedbackTimestamp").asText(), creationDate.toString());
		assertEquals(actualRep.get("statusCode").asText(), "200");
		
		cfr = new CreateFeedbackResponse(400, "whoop I dropped my chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(cfr.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "whoop I dropped my chocolate bar");
	}

}
