package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
//import java.util.List;

public class Approval extends Opinion{

	public Approval(String alternativeId, String userId, Timestamp timestamp, String userName, String choiceId) {
		super(alternativeId, userId, timestamp, userName, choiceId);
		// TODO Auto-generated constructor stub
	}
	
}
