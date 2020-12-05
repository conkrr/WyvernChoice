package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.db.DisapprovalsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Approval;
import com.amazonaws.lambda.demo.model.Disapproval;
import com.amazonaws.lambda.demo.model.Opinion;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddApprovalHandler implements RequestHandler<AddApprovalRequest, OpinionResponse> {

    LambdaLogger logger;

    private Approval createApproval(AddApprovalRequest req) {
        Timestamp approvalDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Approval approval = new Approval(req.getAlternativeID(), req.getApprovingUserID(), approvalDate, req.getUsername(), req.getChoiceID());
        return approval;

    }


    boolean approvalDAOHelper(Approval approval) throws Exception {
        // if (logger != null) { logger.log("in AddApproval"); }

        ApprovalsDAO approvalsDAO = new ApprovalsDAO(logger);
        //List<Approval> approvalsList = approvalsDAO.get(approval.getAlternativeId());


        boolean exists = false; //approvalsList.stream().anyMatch(a -> a.getUserId().equals(approval.getUserId())); // this is either very cool or very bad

        logger.log("Does this approval exist in the database already? " + exists);
        if (!exists) {

            approvalsDAO.insert(approval);
        }
        return !exists;

    }

    @Override
    public OpinionResponse handleRequest(AddApprovalRequest request, Context context) { // So much of this is subject to change :(
        logger = context.getLogger();

        OpinionResponse response; //Final version wont need to initialize this, this is so it compiles
        try {

            Approval a = createApproval(request);
            boolean addApprovalSuccess = approvalDAOHelper(a);

            ApprovalsDAO apvDao = new ApprovalsDAO(logger);
            DisapprovalsDAO disDao = new DisapprovalsDAO();
            List<Approval> appList = apvDao.get(a.getAlternativeId());
            List<Disapproval> disList = disDao.get(a.getAlternativeId());
            List<Opinion> opList = new ArrayList<Opinion>();
            opList.addAll(appList);
            opList.addAll(disList);
            List<String> users =new ArrayList<String>();
            for(Opinion opn: opList) {
            	users.add(opn.getUserName());
            }
            
            if (addApprovalSuccess)
                response = new OpinionResponse(appList.size(), disList.size(), users, "", 200);
            else
                response = new OpinionResponse(appList.size(), disList.size(), users, "", 422);;

        } catch (Exception e) {
            response = new OpinionResponse("Unable to add approval for User: " + request.getUsername() + ", altID: " + request.getAlternativeID() + "(error: " + e.getMessage() + ")", 400);
        }

        return response;
    }

}
