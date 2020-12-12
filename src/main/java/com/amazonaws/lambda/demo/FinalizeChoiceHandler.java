package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.FinalizeChoiceRequest;
import com.amazonaws.lambda.demo.http.FinalizeChoiceResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FinalizeChoiceHandler implements RequestHandler<FinalizeChoiceRequest, FinalizeChoiceResponse> {

	LambdaLogger logger;
	
	
	@Override
	public FinalizeChoiceResponse handleRequest(FinalizeChoiceRequest req, Context context) {

		logger = context.getLogger();
		FinalizeChoiceResponse response;
		ChoicesDAO dao = new ChoicesDAO(logger);		
		try {
			dao.finalize(req.getChoiceID(), (req.getAlternativeID()));
			response = new FinalizeChoiceResponse(req.getChoiceID(),req.getAlternativeID(), true);
		} catch (Exception e) {
			response = new FinalizeChoiceResponse(400, "Unable to finalize choice: " + e.toString());
		}

		return response;
	}

}
