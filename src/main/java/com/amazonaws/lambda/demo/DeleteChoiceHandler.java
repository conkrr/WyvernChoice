package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.DeleteChoiceRequest;
import com.amazonaws.lambda.demo.http.DeleteChoiceResponse;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * No more JSON parsing
 */
public class DeleteChoiceHandler implements RequestHandler<DeleteChoiceRequest,DeleteChoiceResponse> {

	public LambdaLogger logger = null;

	@Override
	public DeleteChoiceResponse handleRequest(DeleteChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		DeleteChoiceResponse response = null;
		logger.log(req.toString());

		ChoicesDAO dao = new ChoicesDAO(logger);

		// MAKE sure that we prevent attempts to delete system constants...
		
		// See how awkward it is to call delete with an object, when you only
		// have one part of its information?
		String id = req.getId();
		try {
			if (dao.delete(id) > 0) {
				response = new DeleteChoiceResponse(req.getId(), 200);
			} else {
				response = new DeleteChoiceResponse(req.getId(), 422, "Unable to delete choice.");
			}
		} catch (Exception e) {
			response = new DeleteChoiceResponse(req.getId(), 403, "Unable to delete choice: " + req.getId() + "(" + e.getMessage() + ")");
		}

		return response;
	}
}
