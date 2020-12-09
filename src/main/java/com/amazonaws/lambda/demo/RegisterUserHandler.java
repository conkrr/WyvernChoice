package com.amazonaws.lambda.demo;

import java.util.UUID;

import com.amazonaws.lambda.db.UsersDAO;
import com.amazonaws.lambda.demo.http.RegisterUserRequest;
import com.amazonaws.lambda.demo.http.RegisterUserResponse;
import com.amazonaws.lambda.demo.http.UserGsonCompatible;
import com.amazonaws.lambda.demo.model.User;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse>{

	
	LambdaLogger logger;
	

	private User createUser(RegisterUserRequest req){ 
		UUID id = UUID.randomUUID();
		User user = new User(req.getChoiceID(), req.getUsername(), req.getPassword(), id.toString());
		return user;
		
	}
	
	@Override 
	public RegisterUserResponse handleRequest(RegisterUserRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());
		RegisterUserResponse response;
		User u = createUser(req);
		UsersDAO dao = new UsersDAO(logger);		
		try {
			boolean isOpen = dao.isChoiceOpen(u.choiceId);
			
			if(isOpen) {
				boolean exists = dao.addUser(u);
					if(!exists) {
						UserGsonCompatible uGson = new UserGsonCompatible(u);
						response = new RegisterUserResponse(uGson);
					} else {
						response = new RegisterUserResponse(422, "Unable to create user \"" + req.getUsername() + "\" -- User already exists");
					}
			} else {
				response = new RegisterUserResponse(422, "Unable to create user \"" + req.getUsername() + "\" -- Choice already full");
			}
		} catch (Exception e) {
			response = new RegisterUserResponse(400, "Unable to create user: " + req.getUsername() + "(" + e.getMessage() + ")");
		}

		return response;
	}
	
	
	
//	@Override
//	public RegisterUserResponse handleRequest(RegisterUserRequest input, Context context) {
//		logger = context.getLogger();
//		logger.log("Loading Java Lambda handler of CalculatorHandler");
//		logger.log(input.toString());
//		
//		boolean fail = false;
//		String failMessage = "";
//		
//		String loggedInUser = "";
//		//Am I gonna have to do more here?
//		//What rules do we have for user and password?
//		loggedInUser = input.getLoggedInUser();
//		
//		
//		String password = "";
//		if(input.getPassword() != null) {
//			password = input.getPassword();
//		}
//		
//		String choiceID = "";
//		choiceID = input.getChoiceID();
//		//Check query to see if choiceID is valid     TODO!!!
//
//		if (isValidChoiceID(choiceID) == false)
//		{
//			fail = true;
//			failMessage = "ChoiceID is not valid or does not exist";
//
//		}
//
//
//		RegisterUserResponse response;
//		if(fail) {
//			response = new RegisterUserResponse(400, failMessage);
//		} else {
//			response = new RegisterUserResponse(loggedInUser, password, choiceID, 200);
//		}
//		
//		return response;
//		
//	}
//
//
//
//	private boolean isValidChoiceID(String choiceID) //checks if the id exists and is valid
//	{
//		ChoicesDAO choicesDAO = new ChoicesDAO();
//		Choice c = null;
//		if (choiceID.length() > 16) //TODO update to what the datebase says
//		{
//			return  false;
//
//		}
//
//		try
//		{
//			 c = choicesDAO.getChoice(choiceID);   //if id already exists
//
//		} catch (Exception e) {
//			return false; //doesnt exist --> dont register a user to a choice that doesnt exist
//		}
//
//		return c != null; //if c != null then the choice exists
//	}

}
