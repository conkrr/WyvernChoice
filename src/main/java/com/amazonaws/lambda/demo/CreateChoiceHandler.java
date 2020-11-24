package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.CreateChoiceRequest;
import com.amazonaws.lambda.demo.http.CreateChoiceResponse;
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
	boolean createChoice(String id, String description) throws Exception { 
		if (logger != null) { logger.log("in createChoice"); }
		ChoicesDAO dao = new ChoicesDAO();
		
		// check if present
		boolean exists = dao.getChoice(id) == null;
		Choice choice = new Choice (id, description);
		if (exists) {
			boolean addChoiceBoolean =  dao.addChoice(choice);
			return addChoiceBoolean;
		} else {
			return false;
		}
	}
	
	
	@Override 
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		CreateChoiceResponse response;
		try {
			boolean createChoiceBoolean = createChoice(req.getId(), req.getDescription());
			if (createChoiceBoolean) {
				response = new CreateChoiceResponse(req.getId());
			} else {
				response = new CreateChoiceResponse(req.getId(), 422);
			}
		} catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create choice: " + req.getId() + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
