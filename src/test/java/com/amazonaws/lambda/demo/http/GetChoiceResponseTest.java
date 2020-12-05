package com.amazonaws.lambda.demo.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Choice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class GetChoiceResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		/*
		List<Alternative> alternatives = new ArrayList<Alternative>();
		List<AlternativeGsonCompatible> fakeAlternatives = new ArrayList<AlternativeGsonCompatible>();
		Timestamp creationDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
		ChoiceGsonCompatible fakeTestChoice = new ChoiceGsonCompatible("fake choice", "12345", 5, "bad completion date", false, fakeAlternatives);
		//Choice testChoice = new Choice("12345", "Pickled oreos", creationDate, "userID", false, alternatives, 9, 4);
		GetChoiceResponse gcr = new GetChoiceResponse(fakeTestChoice);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(gcr.toString());
		
		gcr = new GetChoiceResponse(400, "bye bye chocolate bar");
		rep = new ObjectMapper();
		actualRep = rep.readTree(gcr.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "bye bye chocolate bar");
		*/
	}

}
