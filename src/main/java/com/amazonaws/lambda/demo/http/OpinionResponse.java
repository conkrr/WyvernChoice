package com.amazonaws.lambda.demo.http;

import java.util.List;

public class OpinionResponse {

	public int approvals;
	public int disapprovals;
	public List<String> users;
	public String error;
	
	public int statusCode;

	public OpinionResponse(int approvals, int disapprovals, List<String> users, String error, int statusCode) {
		this.approvals = approvals;
		this.disapprovals = disapprovals;
		this.users = users;
		this.error = error;
		this.statusCode = statusCode;
	}
	
	public OpinionResponse(String error, int statusCode) {
		this.error = error;
		this.statusCode = statusCode;
	}
	
	
	
	
}
