package com.amazonaws.lambda.demo.model;

import java.util.List;

public class Alternative {
	private final String name;
	private final String choiceID;
	private final String alternativeID;
	//public int Approvals;
	//public int Disapprovals;
	private final List<Approval> approvals;
	private final List<Disapproval> disapprovals;
	private final List<Feedback> feedback;
	private final boolean isChosen;
//	public final List<Feedback> listofFeedback;
	public Alternative(String name, String choiceID, String alternativeID, List<Approval> approvals,List<Disapproval> disapprovals,List<Feedback> feedback, boolean isChosen) {
		this.name = name;
		this.choiceID = choiceID;
		this.alternativeID = alternativeID;
		this.approvals = approvals;
		this.disapprovals = disapprovals;
		this.feedback = feedback;
		this.isChosen = isChosen;
	}
	
	public String getName() {
		return name;
	}
	
	public String getChoiceID() {
		return choiceID;
	}
	public String getAlternativeID() {
		return alternativeID;
	}
	public List<Approval> getApprovals() {
		return approvals;
	}
	public List<Disapproval> getDisapprovals() {
		return disapprovals;
	}
	public List<Feedback> getFeedback() {
		return feedback;
	}
	public boolean isChosen() {
		return isChosen;
	}
	
	
	
	
}
