package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Approval;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;


public class AddApprovalHandler implements RequestHandler<AddApprovalRequest, AddApprovalResponse> {

    LambdaLogger logger;

    private Approval createApproval(AddApprovalRequest req) {
        Timestamp approvalDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Approval approval = new Approval(req.getAlternativeID(), req.getApprovingUserID(), approvalDate, req.getUsername(), req.getChoiceID());
        return approval;

    }


    boolean approvalDAOHelper(Approval approval) throws Exception {
        // if (logger != null) { logger.log("in AddApproval"); }

        ApprovalsDAO approvalsDAO = new ApprovalsDAO(/*logger*/);
        //List<Approval> approvalsList = approvalsDAO.get(approval.getAlternativeId());


        boolean exists = false; //approvalsList.stream().anyMatch(a -> a.getUserId().equals(approval.getUserId())); // this is either very cool or very bad

        logger.log("Does this approval exist in the database already? " + exists);
        if (!exists) {

            approvalsDAO.insert(approval);
        }
        return !exists;

    }

    @Override
    public AddApprovalResponse handleRequest(AddApprovalRequest request, Context context) { // So much of this is subject to change :(
        logger = context.getLogger();

        AddApprovalResponse response; //Final version wont need to initialize this, this is so it compiles
        try {

            Approval a = createApproval(request);

            boolean addApprovalSuccess = approvalDAOHelper(a);

            //Need to return list of approvals for given alternative ID instead of returning the same approval
            
            if (addApprovalSuccess)
                response = new AddApprovalResponse(a.getUserName(), a.getAlternativeId(), a.getChoiceId());
            else
                response = new AddApprovalResponse(a.getUserName(), a.getAlternativeId(), a.getChoiceId(), 422);

        } catch (Exception e) {
            response = new AddApprovalResponse(400, "Unable to add approval for User: " + request.getUsername() + ", altID: " + request.getAlternativeID() + "(error: " + e.getMessage() + ")");
        }

        return response;
    }

}
