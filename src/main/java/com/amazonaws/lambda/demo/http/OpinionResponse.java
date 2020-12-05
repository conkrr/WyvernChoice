package com.amazonaws.lambda.demo.http;

import java.util.List;

public class OpinionResponse {

	public String alternativeID;
	public int approvals;
	public int disapprovals;
	public List<String> approvalUsers;
	public List<String> disapprovalUsers;
	public String error;
	
	public int statusCode;
	
	

	public OpinionResponse(String alternativeID, int approvals, int disapprovals, List<String> approvalUsers,
			List<String> disapprovalUsers, String error, int statusCode) {
		this.alternativeID = alternativeID;
		this.approvals = approvals;
		this.disapprovals = disapprovals;
		this.approvalUsers = approvalUsers;
		this.disapprovalUsers = disapprovalUsers;
		this.error = error;
		this.statusCode = statusCode;
	}



	public OpinionResponse(String error, int statusCode) {
		this.error = error;
		this.statusCode = statusCode;
	}
	
	
	
	
}
