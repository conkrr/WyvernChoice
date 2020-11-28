package com.amazonaws.lambda.demo.model;

import java.util.List;

public class Disapproval {
	public int disapprovalCount;
	public final String alternativeID;
	public List<User> disapprovalUsers;
	
	public Disapproval(int disapprovalCount, String alternativeID, List<User> disapprovalUsers) {
		this.disapprovalCount = disapprovalCount;
		this.alternativeID = alternativeID;
		this.disapprovalUsers = disapprovalUsers;
	}
}
