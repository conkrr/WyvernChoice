package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.lambda.demo.model.Approval;
import com.amazonaws.lambda.demo.model.Disapproval;
import com.amazonaws.lambda.demo.model.Opinion;

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


	public OpinionResponse(String alternativeID, List<Approval> approvalsList,
			List<Disapproval> disapprovalsList, String error, int statusCode) {
		this.alternativeID = alternativeID;
		this.error = error;
		this.statusCode = statusCode;
		
		this.approvals = approvalsList.size();
		this.disapprovals = disapprovalsList.size();
		this.approvalUsers = getOpinionUsers(new ArrayList<Opinion>(approvalsList));
		this.disapprovalUsers = getOpinionUsers(new ArrayList<Opinion>(disapprovalsList));
		
		
	}

	public OpinionResponse(String error, int statusCode) {
		this.error = error;
		this.statusCode = statusCode;
	}
	
	private List<String> getOpinionUsers(List<Opinion> approvals) {
		List<String> nameList = new ArrayList<String>();
		Iterator<Opinion> iterator = approvals.iterator();
		while(iterator.hasNext()){
		  nameList.add(iterator.next().getUserName());
		}
		return nameList;
	}


	
}
