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
import com.amazonaws.lambda.demo.model.Approval;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.lambda.demo.model.Opinion;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;



public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {

	LambdaLogger logger;
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	private Choice createChoice(String description, List<String> alternativeDescs, String creatingUserID,
			int maxParticipants){ 
		
		logger.log("initialize stuff");
		UUID choiceID = UUID.randomUUID();
		Timestamp creationDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		logger.log("make alternatives");
		logger.log("alternatives: " + (alternativeDescs==null));
		ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
		for(int i = 0; i < alternativeDescs.size(); i++) {
			UUID alternativeID = UUID.randomUUID();
			List<Opinion> emptyApprovals = new ArrayList<Opinion>();
			
			alternatives.add(new Alternative(alternativeDescs.get(i), choiceID.toString(), alternativeID.toString(), emptyApprovals, false));
		}
		
		logger.log("create choice");
		Choice choice = new Choice (choiceID.toString(), description, creationDate, creatingUserID, false, alternatives,
					maxParticipants, 1);
		
		logger.log("return choice");
		return choice;
		
	}
	
	boolean choiceDAOHelper(Choice choice) throws Exception { 
		if (logger != null) { logger.log("in createChoice"); }
		ChoicesDAO dao = new ChoicesDAO(logger);
				// check if present

		boolean exists = dao.get(choice.id.toString()) != null;
		logger.log("exist == " + exists);
		if (!exists) {
			
			dao.insert(choice);
		}
		return !exists;
		
	}
	

	@Override 
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("name: " + req.getName());
		logger.log("alternatives: " + req.getListofAlternatives());
		logger.log("userID: " + req.getCreatingUserID());
		logger.log("participants: " + req.getNumParticipants());

		CreateChoiceResponse response;
		try {
			logger.log("trying to create choice");
			Choice c = createChoice(req.getName(), req.getListofAlternatives(), req.getCreatingUserID(), req.getNumParticipants());
			logger.log("choice creation worked: " + (c != null));
			logger.log("trying to access DAO");
			boolean createChoiceBoolean = choiceDAOHelper(c);
			
			if (createChoiceBoolean) {
				//response = new CreateChoiceResponse(req.getDescription());
				ChoiceGsonCompatible cGson = new ChoiceGsonCompatible(c);
				response = new CreateChoiceResponse(cGson);
			} else {
				response = new CreateChoiceResponse(req.getName(), 422);
			}
		} catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create choice: " + req.getName() + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
