package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
//import java.util.List;

public class Disapproval extends Opinion {

	public Disapproval(String alternativeId, String userId, Timestamp timestamp, String userName, String choiceId) {
		super(alternativeId, userId, timestamp, userName, choiceId);

	}

}
