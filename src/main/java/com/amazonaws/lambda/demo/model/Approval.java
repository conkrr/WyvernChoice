package com.amazonaws.lambda.demo.model;

import java.util.List;

public class Approval {
	public int approvalCount;
	public final String alternativeID;
	public List<User> approvalUsers;
	
	public Approval(int approvalCount, String alternativeID, List<User> approvalUsers) {
		this.approvalCount = approvalCount;
		this.alternativeID = alternativeID;
		this.approvalUsers = approvalUsers;
	}

}
