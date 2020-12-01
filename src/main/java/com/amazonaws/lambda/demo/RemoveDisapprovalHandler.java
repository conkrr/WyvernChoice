package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Disapproval;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RemoveDisapprovalHandler implements RequestHandler<RemoveDisapprovalRequest , RemoveDisapprovalResponse >
{

//************************* THIS CLASS IS JUST AN OUTLINE *************************


    LambdaLogger logger;


    boolean disapprovalDAOHelper(RemoveDisapprovalRequest request) throws Exception {
        // if (logger != null) { logger.log("in RemoveApproval"); }

        //disapprovalsDAO dao = new disapprovalsDAO(logger); // TODO: Create ApprovalsDAO object

        // STEP 1: Check if this user has already disapproved, if so remove
        // Step 2: If not, do nothing TODO: however Jason figures out he wants to approach this


        // disapprovalsDAO.removeDisapproval(/*altID,user*/); // TODO: this too

        return false; //TODO: change to actual result

    }

    @Override
    public RemoveDisapprovalResponse handleRequest(RemoveDisapprovalRequest request, Context context) {  // So much of this is subject to change :(
        logger = context.getLogger();

        RemoveDisapprovalResponse response = new RemoveDisapprovalResponse(); //Final version wont need to initialize this, this is so it compiles
        try {

            boolean removeDisapprovalSuccess = disapprovalDAOHelper(request);

            if (removeDisapprovalSuccess) {

                //DisapprovalGsonCompatible a_Gson = new  DisapprovalGsonCompatible(request); //TODO: Jason fix this
                //response = new RemoveDisapprovalResponse(a_Gson);

            } else {
                //response = new RemoveDisapprovalResponse(request.getDisapprovingUser() + " :  " + request.getAlternativeID(), 422); //null
            }
        } catch (Exception e) {
            response = new RemoveDisapprovalResponse(); //   new RemoveDisapprovalResponse("Unable to remove disapproval: " + request.getDisapprovingUser() + ": altID: "  + request.getAlternativeID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }


}
