package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Alternative;

public class AlternativeResponse {

	private String id;
	private String description;
	private boolean isChosen;
	private OpinionResponse opinions;
	private FeedbackResponse feedback;

	public AlternativeResponse(String id, String description, boolean isChosen, OpinionResponse opinions,
			FeedbackResponse feedback) {
		this.id = id;
		this.description = description;
		this.isChosen = isChosen;
		this.opinions = opinions;
		this.feedback = feedback;
	}


	public boolean getIsChosen() {
		return isChosen;
	}
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public OpinionResponse getOpinions() {
		return opinions;
	}
	public FeedbackResponse getFeedback() {
		return feedback;
	}
	public AlternativeResponse() {
		
	}
	
	public static List<AlternativeResponse> getResponseForm(List<Alternative> alts){
		
		ArrayList<AlternativeResponse> resp = new ArrayList<AlternativeResponse>();
		for (Alternative a : alts) {
			resp.add(new AlternativeResponse(a.getAlternativeID(), a.getName(), a.isChosen(),
					new OpinionResponse(a.getAlternativeID(), a.getApprovals(), a.getDisapprovals(), "", 200),
					new FeedbackResponse(a)));
		}
		return resp;
	}
	
	
}
