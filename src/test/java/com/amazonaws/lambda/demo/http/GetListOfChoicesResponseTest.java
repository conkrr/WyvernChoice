package com.amazonaws.lambda.demo.http;

import static org.junit.Assert.assertEquals;
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

class GetListOfChoicesResponseTest {

	@Test
	void test() throws JsonProcessingException, IOException {
		Timestamp creationDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
		List<Alternative> alternatives = new ArrayList<Alternative>();
		Choice testChoice1 = new Choice("12345", "Pickled oreos", creationDate, "userID", false, alternatives, 9, 4);
		Choice testChoice2 = new Choice("6789", "Pickled fish", creationDate, "userID2", false, alternatives, 4, 9);
		
		ArrayList<Choice> choiceList = new ArrayList<Choice>();
		choiceList.add(testChoice1);
		choiceList.add(testChoice2);
		
		GetListOfChoicesResponse glocr = new GetListOfChoicesResponse(choiceList);
		ObjectMapper rep = new ObjectMapper();
		JsonNode actualRep = rep.readTree(glocr.toString());
		assertEquals(actualRep.get("choice0ID").asText(), "12345");
		assertEquals(actualRep.get("choice0Description").asText(), "Pickled oreos");
		assertEquals(actualRep.get("choice0CreationDate").asText(), creationDate.toString());
		assertEquals(actualRep.get("choice0IsFinalized").asText(), "false");
		
		assertEquals(actualRep.get("choice1ID").asText(), "6789");
		assertEquals(actualRep.get("choice1Description").asText(), "Pickled fish");
		assertEquals(actualRep.get("choice1CreationDate").asText(), creationDate.toString());
		assertEquals(actualRep.get("choice1IsFinalized").asText(), "false");
		
		glocr = new GetListOfChoicesResponse(400, "oops that's an error");
		rep = new ObjectMapper();
		actualRep = rep.readTree(glocr.toString());
		assertEquals(actualRep.get("statusCode").asText(), "400");
		assertEquals(actualRep.get("error").asText(), "oops that's an error");
		
	}

}
