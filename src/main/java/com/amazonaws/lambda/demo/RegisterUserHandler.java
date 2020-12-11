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
		
		User user = new User(req.getChoiceID(), req.getPassword(), req.getUsername());
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
						
						response = new RegisterUserResponse(u);
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

}
