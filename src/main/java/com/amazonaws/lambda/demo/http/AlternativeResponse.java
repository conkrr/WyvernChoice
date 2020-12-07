package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Alternative;

public class AlternativeResponse {

	private String id;
	private String description;
	//private String isChosen;
	private OpinionResponse opinions; 
	


	public AlternativeResponse(String id, String description, OpinionResponse opinions) {
		this.id = id;
		this.description = description;
		this.opinions = opinions;
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



	public AlternativeResponse() {
		
	}
	
	public static List<AlternativeResponse> getResponseForm(List<Alternative> alts){
		
		ArrayList<AlternativeResponse> resp = new ArrayList<AlternativeResponse>();
		for (Alternative a : alts) {
			resp.add(new AlternativeResponse(a.getAlternativeID(), a.getName(),
					new OpinionResponse(a.getAlternativeID(), a.getApprovals(), a.getDisapprovals(), "", 200)));
		}
		return resp;
	}
	
	
}
