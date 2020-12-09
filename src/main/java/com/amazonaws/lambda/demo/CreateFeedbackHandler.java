package com.amazonaws.lambda.demo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.amazonaws.lambda.db.FeedbackDAO;
import com.amazonaws.lambda.demo.http.CreateFeedbackRequest;
import com.amazonaws.lambda.demo.http.CreateFeedbackResponse;
import com.amazonaws.lambda.demo.http.FeedbackResponse;
import com.amazonaws.lambda.demo.http.OpinionResponse;
import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.lambda.demo.model.Feedback;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateFeedbackHandler implements RequestHandler<CreateFeedbackRequest, FeedbackResponse>{
	
	LambdaLogger logger;
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	private Feedback createFeedback(CreateFeedbackRequest req){ 
		
		logger.log("CreateFeedbackHandler::createFeedback -- Start");
		
		Timestamp creationDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
		Feedback feedback = new Feedback(req.getUser(), req.getText(), creationDate, req.getAlternativeID());
		
		
		logger.log("CreateFeedbackHandler::createFeedback -- Start");
		return feedback;
		
	}

	@Override
	public FeedbackResponse handleRequest(CreateFeedbackRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("user: " + req.getUser());
		logger.log("text: " + req.getText());
		logger.log("alternativeID: " + req.getAlternativeID());
		FeedbackResponse response = null;
		FeedbackDAO dao = new FeedbackDAO(logger);
		try {
			boolean alreadyExists = dao.insert(createFeedback(req));

			if (alreadyExists)
                response = new FeedbackResponse(200, "");
            else
            	response = new FeedbackResponse(422, "");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new FeedbackResponse(400, " " + e.toString());
			
		}
		
		return response;
	}

}
