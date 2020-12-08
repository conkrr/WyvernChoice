package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Feedback;

public class FeedbackResponse {
	
		public final String alternativeID;
		public final List<FeedBackResponseObject> feedbackObjects;
	
		public class FeedBackResponseObject {
			public final String userName;
			public final String text;
			public final String timestamp;
			
			public FeedBackResponseObject(String userName, String text, String timestamp) {
				this.userName = userName;
				this.text = text;
				this.timestamp = timestamp;
			}
		}

		public FeedbackResponse(Alternative a) {
			this.alternativeID = a.getAlternativeID();
			List<FeedBackResponseObject> feedbackObjects = new ArrayList<FeedBackResponseObject>();
			for(Feedback f : a.getFeedback()) {
				feedbackObjects.add(new FeedBackResponseObject(f.userID, f.text, f.timestamp.toString()));
				
			}
			this.feedbackObjects = feedbackObjects;
		}
		
}
