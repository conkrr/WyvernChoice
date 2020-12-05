package com.amazonaws.lambda.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.demo.http.ChoiceGsonCompatible;
import com.amazonaws.lambda.demo.http.GetChoiceRequest;
import com.amazonaws.lambda.demo.http.GetChoiceResponse;
import com.amazonaws.lambda.demo.http.GetListOfChoicesRequest;
import com.amazonaws.lambda.demo.http.GetListOfChoicesResponse;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class GetListOfChoicesHandler implements RequestHandler<GetListOfChoicesRequest, GetListOfChoicesResponse>{

		LambdaLogger logger;
		
		Choice getChoiceViaDAO(String id) throws Exception { 
			logger.log("GetChoiceHandler::getChoiceDAO()");
			ChoicesDAO dao = new ChoicesDAO(logger);
			Choice choice = dao.get(id);
			return choice;
		}
		

		@Override 
		public GetListOfChoicesResponse handleRequest(GetListOfChoicesRequest req, Context context)  {
			logger = context.getLogger();
			logger.log("GetChoiceHandler::handleRequest()");

			GetListOfChoicesResponse response = null;
			try {
				ChoicesDAO dao = new ChoicesDAO(logger);
				List<Choice> choices = dao.getAll();
				//logger.log("After running getChoiceViaDAO()...choice is null:  " + (choice == null));
				if (!choices.isEmpty()) {
					response = new GetListOfChoicesResponse(choices, 200, "");
				} else {
					response = new GetListOfChoicesResponse(choices, 422, "");
				}
			} catch (Exception e) {
				response = new GetListOfChoicesResponse(400, "Unable to get all choices: " + "(" + e.getMessage() + ")");
			}

			return response;
		}
	}
