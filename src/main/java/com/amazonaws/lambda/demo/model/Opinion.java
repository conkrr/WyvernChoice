package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Opinion {
	protected final String alternativeId;
	protected final String choiceId;
	protected final String userId;
	protected final Timestamp timestamp;
	protected final String userName;

	public Opinion(String alternativeId, String userId, Timestamp timestamp, String userName, String choiceId) {
		this.alternativeId = alternativeId;
		this.userId = userId;
		this.timestamp = timestamp;
		this.userName = userName;
		this.choiceId = choiceId;
	}

	public String getAlternativeId() {
		return alternativeId;
	}
	public String getUserId() {
		return userId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public String getUserName() {
		return userName;
	}

	public String getChoiceId() {
		return choiceId;
	}
	
}
