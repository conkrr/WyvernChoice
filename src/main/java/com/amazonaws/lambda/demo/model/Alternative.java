package com.amazonaws.lambda.demo.model;

import java.util.List;

public class Alternative {
	public final String name;
	public final String alternativeID;
	public int Approvals;
	public int Disapprovals;
	public boolean isChosen;
	public List<Feedback> ListofFeedback;
	
	
	
	
	public Alternative(String name, String alternativeID, int Approvals, int Disapprovals, boolean isChosen,List<Feedback> ListofFeedback) {
		this.name = name;
		this.alternativeID = alternativeID;
		this.Approvals = Approvals;
		this.Disapprovals = Disapprovals;
		this.isChosen = isChosen;
		this.ListofFeedback = ListofFeedback;
	}
	
	public Alternative(String name, String alternativeID, boolean isChosen) {
		this.name = name;
		this.alternativeID = alternativeID;
		this.isChosen = isChosen;
	}
	
}
