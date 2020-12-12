package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.AlternativesDAO;
import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.db.ChoicesDAO;
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
import java.util.Iterator;
import java.util.List;


public class AddApprovalHandler implements RequestHandler<AddApprovalRequest, OpinionResponse> {

    LambdaLogger logger;

    private Approval createApproval(AddApprovalRequest req) {
        Timestamp approvalDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Approval approval = new Approval(req.getAlternativeID(), req.getApprovingUserID(), approvalDate, req.getUsername(), req.getChoiceID());
        return approval;

    }



    @Override
    public OpinionResponse handleRequest(AddApprovalRequest request, Context context) { // So much of this is subject to change :(
        logger = context.getLogger();

        OpinionResponse response; //Final version wont need to initialize this, this is so it compiles
        try {

            Approval a = createApproval(request);
             
            ApprovalsDAO apvDao = new ApprovalsDAO(logger);
            DisapprovalsDAO disDao = new DisapprovalsDAO(logger);
            List<Approval> appList = apvDao.get(a.getAlternativeId());
            List<Disapproval> disList = disDao.get(a.getAlternativeId());          
            
            AlternativesDAO altDAO = new AlternativesDAO(logger);
            ChoicesDAO choDAO = new ChoicesDAO(logger);
           boolean isFinalized =  choDAO.get(altDAO.getChoiceID(a.getAlternativeId())).isFinalized;
       
           if(!isFinalized) {
        	   
        	   boolean exists = apvDao.insert(a);
        	   
        	   if (!exists)
                   response = new OpinionResponse(a.getAlternativeId(), appList, disList, "", 200);
               else
               	response = new OpinionResponse(a.getAlternativeId(), appList, disList, "already exists", 422);
           } else {
        		response = new OpinionResponse(a.getAlternativeId(), appList, disList, "cannot add approval -- Choice has already been finalized", 422);
           }

        } catch (Exception e) {
            response = new OpinionResponse("Unable to add approval for User: " + request.getUsername() + ", altID: " + request.getAlternativeID() + "(error: " + e.getMessage() + ")", 400);
        }

        return response;
    }

}
