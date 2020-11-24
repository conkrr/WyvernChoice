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
		boolean exists = dao.getChoice(id) != null; //changed logic here, exists implies true = does exist..
		Choice choice = new Choice (id, description);
		if (!exists) {
			return dao.addChoice(choice);
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
			if (createChoice(req.id, req.description)) {
				response = new CreateChoiceResponse(req.id);
			} else {
				response = new CreateChoiceResponse(req.id, 422);
			}
		} catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create choice: " + req.id + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
