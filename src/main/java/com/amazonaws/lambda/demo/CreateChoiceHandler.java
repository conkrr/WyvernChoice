package com.amazonaws.lambda.demo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.CreateChoiceRequest;
import com.amazonaws.lambda.demo.http.CreateChoiceResponse;
import com.amazonaws.lambda.demo.http.ChoiceGsonCompatible;
import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;



public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {

	LambdaLogger logger;
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	private Choice createChoiceHelper(String description, List<String> alternativeDescs, String creatingUserID,
			int maxParticipants){ 
		
		UUID id = UUID.randomUUID();
		Timestamp creationDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
		for(int i = 0; i < alternativeDescs.size(); i++) {
			alternatives.add(new Alternative(alternativeDescs.get(i), UUID.randomUUID().toString(), false));
		}
		Choice choice = new Choice (id.toString(), description, creationDate, creatingUserID, false, alternatives,
					maxParticipants, 1);
		return choice;
		
	}
	
	boolean createChoice(String description, List<String> alternativeDescs, String creatingUserID,
			int maxParticipants) throws Exception { 
		if (logger != null) { logger.log("in createChoice"); }
		ChoicesDAO dao = new ChoicesDAO(logger);
		Choice choice = createChoiceHelper(description, alternativeDescs, creatingUserID,
				maxParticipants);
		// check if present

		boolean exists = dao.getChoice(choice.id.toString()) != null;
		logger.log("exist == " + exists);
		if (!exists) {
			
			dao.addChoice(choice);
		}
		return !exists;
		
	}
	

	@Override 
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		CreateChoiceResponse response;
		try {
			boolean createChoiceBoolean = createChoice(req.getDescription(), req.getAlternatives(), req.getCreatingUserId(), req.getMaxParticipants());
			if (createChoiceBoolean) {
				//response = new CreateChoiceResponse(req.getDescription());
				ChoiceGsonCompatible c = new ChoiceGsonCompatible(createChoiceHelper(req.getDescription(), req.getAlternatives(), req.getCreatingUserId(), req.getMaxParticipants()));
				response = new CreateChoiceResponse(c);
			} else {
				response = new CreateChoiceResponse(req.getDescription(), 422);
			}
		} catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create choice: " + req.getDescription() + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
