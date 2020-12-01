package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Approval;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class RemoveApprovalHandler  implements RequestHandler<RemoveApprovalRequest, RemoveApprovalResponse>
{

    //************************* THIS CLASS IS JUST AN OUTLINE *************************

    LambdaLogger logger;


    boolean approvalDAOHelper(RemoveApprovalRequest request) throws Exception {
        // if (logger != null) { logger.log("in RemoveApproval"); }

        //approvalsDAO dao = new ApprovalsDAO(logger); // TODO: Create ApprovalsDAO object

        // STEP 1: Check if this user has already approved, if so remove
        // Step 2: If not, do nothing TODO: however Jason figures out he wants to approach this


        // approvalsDAO.removeApproval(/*altID, user*/); // TODO: this too

        return false; //TODO: change to actual result

    }

    @Override
    public RemoveApprovalResponse handleRequest(RemoveApprovalRequest request, Context context) { // So much of this is subject to change :(
        logger = context.getLogger();

        RemoveApprovalResponse response = new RemoveApprovalResponse("approvingUser", "alternativeID",  "choiceID"); //Final version wont need to initialize this, this is so it compiles
        try {

            boolean removeApprovalSuccess = approvalDAOHelper(request);

            if (removeApprovalSuccess) {

                //ApprovalGsonCompatible a_Gson = new  ApprovalGsonCompatible(request); //TODO: Jason fix this
                //response = new RemoveApprovalResponse(a_Gson);

            } else {
                //response = new RemoveApprovalResponse(request.getApprovingUser() + " :  " + request.getAlternativeID(), 422); //null
            }
        } catch (Exception e) {
            response = new RemoveApprovalResponse("approvingUser", "alternativeID",  "choiceID"); //   new RemoveApprovalResponse("Unable to remove approval: " + request.getApprovingUser() + ": altID: "  + request.getAlternativeID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }


}
