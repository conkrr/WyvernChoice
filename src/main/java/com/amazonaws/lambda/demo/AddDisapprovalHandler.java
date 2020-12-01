package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Disapproval;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddDisapprovalHandler implements RequestHandler<AddDisapprovalRequest , AddDisapprovalResponse >
{


    //************************* THIS CLASS IS JUST AN OUTLINE *************************


    LambdaLogger logger;


    boolean disapprovalDAOHelper(AddDisapprovalRequest request) throws Exception {
        // if (logger != null) { logger.log("in AddApproval"); }

        //disapprovalsDAO dao = new disapprovalsDAO(logger); // TODO: Create ApprovalsDAO object

        // STEP 1: Check if this user has already disapproved, if so, do nothing return false
        // Step 2: If not, add the disapproval TODO: however Jason figures out he wants to approach this


        // disapprovalsDAO.addDisapproval(/*altID,*/); // TODO: this too

        return false; //TODO: change to actual result

    }

    @Override
    public AddDisapprovalResponse handleRequest(AddDisapprovalRequest request, Context context) {  // So much of this is subject to change :(
        logger = context.getLogger();

        AddDisapprovalResponse response = new AddDisapprovalResponse("disapprovingUser", "alternativeID",  "choiceID"); //Final version wont need to initialize this, this is so it compiles
        try {

            boolean addDisapprovalSuccess = disapprovalDAOHelper(request);

            if (addDisapprovalSuccess) {

                //DisapprovalGsonCompatible a_Gson = new  DisapprovalGsonCompatible(request); //TODO: Jason fix this
                //response = new AddDisapprovalResponse(a_Gson);

            } else {
                //response = new AddDisapprovalResponse(request.getDisapprovingUser() + " :  " + request.getAlternativeID(), 422); //null
            }
        } catch (Exception e) {
            response = new AddDisapprovalResponse("disapprovingUser", "alternativeID",  "choiceID"); //   new AddDisapprovalResponse("Unable to add disapproval: " + request.getDisapprovingUser() + ": altID: "  + request.getAlternativeID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }


}
