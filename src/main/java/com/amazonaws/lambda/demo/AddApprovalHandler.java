package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Approval;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class AddApprovalHandler implements RequestHandler<AddApprovalRequest, AddApprovalResponse> {



    //************************* THIS CLASS IS JUST AN OUTLINE *************************

    LambdaLogger logger;

    boolean approvalDAOHelper(AddApprovalRequest request) throws Exception {
        // if (logger != null) { logger.log("in AddApproval"); }

        //approvalsDAO dao = new ApprovalsDAO(logger); // TODO: Create ApprovalsDAO object

        // STEP 1: Check if this user has already approved, if so, do nothing return false
        // Step 2: If not, add the approval TODO: however Jason figures out he wants to approach this


        // approvalsDAO.addApproval(/*altID,*/); // TODO: this too

        return false; //TODO: change to actual result

    }

    @Override
    public AddApprovalResponse handleRequest(AddApprovalRequest request, Context context) { // So much of this is subject to change :(
        logger = context.getLogger();

        AddApprovalResponse response = new AddApprovalResponse("approvingUser", "alternativeID",  "choiceID"); //Final version wont need to initialize this, this is so it compiles
        try {

            boolean addApprovalSuccess = approvalDAOHelper(request);

            if (addApprovalSuccess) {

                //ApprovalGsonCompatible a_Gson = new  ApprovalGsonCompatible(request); //TODO: Jason fix this
                //response = new AddApprovalResponse(a_Gson);

            } else {
                //response = new AddApprovalResponse(request.getApprovingUser() + " :  " + request.getAlternativeID(), 422); //null
            }
        } catch (Exception e) {
            response = new AddApprovalResponse("approvingUser", "alternativeID",  "choiceID"); //   new AddApprovalResponse("Unable to add approval: " + request.getApprovingUser() + ": altID: "  + request.getAlternativeID() + "(" + e.getMessage() + ")", 400);
        }

        return response;
    }

}
