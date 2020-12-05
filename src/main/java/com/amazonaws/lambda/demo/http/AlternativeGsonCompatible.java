package com.amazonaws.lambda.demo.http;

import java.util.List;

public class AlternativeGsonCompatible {

	private String id;
	private String description;
	private OpinionResponse opinions; 
	


	public AlternativeGsonCompatible(String id, String description, OpinionResponse opinions) {
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



	public AlternativeGsonCompatible() {
		
	}

}
