package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.DeleteChoicesRequest;
import com.amazonaws.lambda.demo.http.DeleteChoicesResponse;
import com.amazonaws.lambda.demo.http.GetChoiceResponse;
import com.amazonaws.lambda.demo.http.RegisterUserRequest;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DeleteChoicesHandler implements RequestHandler<DeleteChoicesRequest, DeleteChoicesResponse> {

	public LambdaLogger logger;



	private boolean deleteChoices(DeleteChoicesRequest req) throws Exception {
		logger.log("IN DELETE CHOICES");

		ChoicesDAO dao = new ChoicesDAO(logger);

		float daysOld = req.getTime(); //this will be in days -> example: 2.5 days


		long currentTime =Calendar.getInstance().getTimeInMillis(); //the current date in milliseconds (time since 1970)
		long deleteTimeMillis = Math.round(daysOld * 24 * 60 * 60 * 1000); //converts days -> to milliseconds
		long deleteOlderThanTime =  currentTime - deleteTimeMillis; //the delete DATE in milliseconds (time since 1970)

		List<Choice> choices = dao.getAll();



		List<Choice> deletedChoices = new ArrayList<Choice>();


		int number =0;

		if (choices == null)
		{
			logger.log("IN DELETE CHOICES:: CHOiCES NULL");
			return false;
		}

		for (Choice c:  choices	 )
		{
			if (c.creationDate.getTime() <= deleteOlderThanTime)
			{
				number += dao.delete(c.id);
			//	deletedChoices.add(c);  //for testing

			}

		}

		System.out.println("Number of choices to delete " + deletedChoices.size());
		for (Choice c:  deletedChoices)
		{
			System.out.printf("Choice desc: %s | choice ID: %s | creationDate: %d", c.description,c.id,c.creationDate.getTime());
			System.out.println();
			logger.log(String.format("Choice desc: %s | choice ID: %s | creationDate: %d", c.description,c.id,c.creationDate.getTime()));
		}


		//System.out.print("Number of rows affected: " + number);
		return true;

	}



	@Override
	public DeleteChoicesResponse handleRequest(DeleteChoicesRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		DeleteChoicesResponse response = null;
		try {


			boolean deleteSuccess = deleteChoices(req);

			if (deleteSuccess) {

				response = new DeleteChoicesResponse(req.getTime());
			} else {
				response = new DeleteChoicesResponse(422, "Unable to delete constants over " + req.getTime() + " days old.");
			}


		} catch (Exception e) {
			response = new DeleteChoicesResponse(400,"Unable to delete constants over " + req.getTime() + "(" + e.getMessage() + ")");

		}

		return response;
	}

}
