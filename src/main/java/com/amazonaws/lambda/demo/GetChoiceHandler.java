package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.ChoiceGsonCompatible;
import com.amazonaws.lambda.demo.http.GetChoiceRequest;
import com.amazonaws.lambda.demo.http.GetChoiceResponse;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetChoiceHandler implements RequestHandler<GetChoiceRequest, GetChoiceResponse>{

		LambdaLogger logger;
		
		Choice getChoiceViaDAO(String id) throws Exception { 
			logger.log("GetChoiceHandler::getChoiceDAO()");
			ChoicesDAO dao = new ChoicesDAO(logger);
			Choice choice = dao.getChoice(id);
			return choice;
		}
		

		@Override 
		public GetChoiceResponse handleRequest(GetChoiceRequest req, Context context)  {
			logger = context.getLogger();
			logger.log("GetChoiceHandler::handleRequest()");

			GetChoiceResponse response;
			try {
				Choice choice = getChoiceViaDAO(req.getChoiceId());
				
				if (choice != null) {
					//response = new GetChoiceResponse(req.getDescription());
					ChoiceGsonCompatible cGson = new ChoiceGsonCompatible(choice);
					response = new GetChoiceResponse(cGson);
				} else {
					response = new GetChoiceResponse(req.getChoiceId(), 422);
				}
			} catch (Exception e) {
				response = new GetChoiceResponse("Unable to get choice: " + req.getChoiceId() + "(" + e.getMessage() + ")", 400);
			}

			return response;
		}

	
}
