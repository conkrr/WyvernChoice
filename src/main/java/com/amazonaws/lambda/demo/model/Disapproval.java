package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
//import java.util.List;

public class Disapproval {
//	public int disapprovalCount;
//	public final String alternativeID;
//	public List<User> disapprovalUsers;
//	
//	public Disapproval(int disapprovalCount, String alternativeID, List<User> disapprovalUsers) {
//		this.disapprovalCount = disapprovalCount;
//		this.alternativeID = alternativeID;
//		this.disapprovalUsers = disapprovalUsers;
//	}
	private final String alternativeId;
	private final String userId;
	private final Timestamp timestamp;
	private final String userName;	
	public Disapproval(String alternativeId, String userId, Timestamp timestamp, String userName) {
		this.alternativeId = alternativeId;
		this.userId = userId;
		this.timestamp = timestamp;
		this.userName = userName;
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
}
