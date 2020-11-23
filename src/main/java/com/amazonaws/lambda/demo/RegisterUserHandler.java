package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.RegisterUserRequest;
import com.amazonaws.lambda.demo.http.RegisterUserResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse>{

	
	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	
	@Override
	public RegisterUserResponse handleRequest(RegisterUserRequest input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CalculatorHandler");
		logger.log(input.toString());
		
		boolean fail = false;
		String failMessage = "";
		
		String loggedInUser = "";
		//Am I gonna have to do more here?
		//What rules do we have for user and password?
		loggedInUser = input.getLoggedInUser();
		
		
		String password = "";
		if(input.getPassword() != null) {
			password = input.getPassword();
		}
		
		String choiceID = "";
		choiceID = input.getChoiceID();
		
		RegisterUserResponse response;
		if(fail) {
			response = new RegisterUserResponse(400, failMessage);
		} else {
			response = new RegisterUserResponse(loggedInUser, password, choiceID, 200);
		}
		
		return response;
		
	}

}
