package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.DeleteChoicesRequest;
import com.amazonaws.lambda.demo.http.DeleteChoicesResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteChoicesHandler implements RequestHandler<DeleteChoicesRequest, DeleteChoicesResponse> {

	public LambdaLogger logger;
	@Override
	public DeleteChoicesResponse handleRequest(DeleteChoicesRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		DeleteChoicesResponse response = null;
		logger.log(req.toString());

		ChoicesDAO dao = new ChoicesDAO(logger);

		float deleteTime = req.getTime();
		//if choices exist before the time you're given
			//delete choices successfully
		//else
			//say no choices exist (too complex?)
		//catch
			//If an error were to occur for any reason, it goes here
		try {
			//if(dao.deleteChoices(deleteTime))
			response = new DeleteChoicesResponse(req.getTime());
			//else 
			//response = new DeleteChoicesResponse(422, "Unable to delete constants over " + req.getTime() + " days old.");
			
		} catch (Exception e) {
			response = new DeleteChoicesResponse(422, "Exception thrown. Unable to delete constants over " + req.getTime() + " days old.");
		}

		return response;
	}

}
