package com.amazonaws.lambda.demo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.amazonaws.lambda.demo.http.CreateFeedbackRequest;
import com.amazonaws.lambda.demo.http.CreateFeedbackResponse;
import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.lambda.demo.model.Feedback;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateFeedbackHandler implements RequestHandler<CreateFeedbackRequest, CreateFeedbackResponse>{
	
	LambdaLogger logger;
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	private Feedback createFeedback(String user, String text, String alternativeID){ 
		
		logger.log("initialize stuff");
		//how get alternative ID?
		UUID id = UUID.randomUUID();
		Timestamp creationDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		
		logger.log("create feedback");
		Feedback feedback = new Feedback(user, text, creationDate, alternativeID);
		
		logger.log("return choice");
		return feedback;
		
	}

	@Override
	public CreateFeedbackResponse handleRequest(CreateFeedbackRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("user: " + req.getUser());
		logger.log("text: " + req.getText());
		logger.log("alternativeID: " + req.getAlternativeID());
		
		CreateFeedbackResponse response;
		return null;
	}

}
