package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
//import java.util.List;

public class Approval {
//	public int approvalCount;
//	public final String alternativeID;
//	public List<String> approvalUsers;
//	
//	public Approval(int approvalCount, String alternativeID, List<String> approvalUsers) {
//		this.approvalCount = approvalCount;
//		this.alternativeID = alternativeID;
//		this.approvalUsers = approvalUsers;
//	}
	
	private final String alternativeId;
	private final String userId;
	private final Timestamp timestamp;
	private final String userName;	
	public Approval(String alternativeId, String userId, Timestamp timestamp, String userName) {
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
