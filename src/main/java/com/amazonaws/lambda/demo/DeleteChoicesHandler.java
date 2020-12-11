package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.AlternativesDAO;
import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.db.DisapprovalsDAO;
import com.amazonaws.lambda.db.FeedbackDAO;
import com.amazonaws.lambda.demo.http.DeleteChoicesRequest;
import com.amazonaws.lambda.demo.http.DeleteChoicesResponse;
import com.amazonaws.lambda.demo.http.GetChoiceResponse;
import com.amazonaws.lambda.demo.http.RegisterUserRequest;
import com.amazonaws.lambda.demo.model.Alternative;
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


		ChoicesDAO dao = new ChoicesDAO(logger);
		AlternativesDAO altDAO= new AlternativesDAO(logger);
		FeedbackDAO feedDAO= new FeedbackDAO(logger);
		ApprovalsDAO appDAO= new ApprovalsDAO(logger);
		DisapprovalsDAO disDAO= new DisapprovalsDAO(logger);

		float daysOld = req.getTime(); //this will be in days -> example: 2.5 days
		long currentTime =Calendar.getInstance().getTimeInMillis(); //the current date in milliseconds (time since 1970)
		long deleteTimeMillis = Math.round(daysOld * 24 * 60 * 60 * 1000); //converts days -> to milliseconds
		long deleteOlderThanTime =  currentTime - deleteTimeMillis; //the delete DATE in milliseconds (time since 1970)
		Timestamp timestamp = new Timestamp(deleteOlderThanTime);
		List<Choice> choices = dao.getChoicesOlderThan(timestamp);	
		List<Choice> deletedChoices = new ArrayList<Choice>();
		if (choices == null)
		{
			return false;
		}

		for (Choice c:  choices	 )
		{
			for(Alternative a : c.alternatives) {
				feedDAO.delete(a.getAlternativeID());
				appDAO.delete(a.getAlternativeID());
				disDAO.delete(a.getAlternativeID());
				altDAO.delete(c.id);
			}
			dao.delete(c.id);
		}
			

		System.out.println("Number of choices to delete " + deletedChoices.size());
		for (Choice c:  deletedChoices)
		{
			System.out.printf("Choice desc: %s | choice ID: %s | creationDate: %d", c.description,c.id,c.creationDate.getTime());
			System.out.println();
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
