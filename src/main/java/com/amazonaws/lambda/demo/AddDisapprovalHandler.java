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

public class AddDisapprovalHandler implements RequestHandler<AddDisapprovalRequest, OpinionResponse> {


    //************************* THIS CLASS IS JUST AN OUTLINE *************************

    LambdaLogger logger;

    private Disapproval createDisapproval(AddDisapprovalRequest req) {
        Timestamp disapprovalDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Disapproval disapproval = new Disapproval(req.getAlternativeID(), req.getDisapprovingUserID(), disapprovalDate, req.getUsername(), req.getChoiceID());
        return disapproval;

    }

    boolean disapprovalDAOHelper(Disapproval disapproval) throws Exception {
        // if (logger != null) { logger.log("in AddApproval"); }

        DisapprovalsDAO disapprovalsDAO = new DisapprovalsDAO(logger);
       // List<Disapproval> disapprovalsList = disapprovalsDAO.get(disapproval.getAlternativeId());

        boolean exists = false; //disapprovalsList.stream().anyMatch(a -> a.getUserId().equals(disapproval.getUserId())); // this is either very cool or very bad

        logger.log("Does this disapproval exist in the database already? " + exists);
        if (!exists) {

            disapprovalsDAO.insert(disapproval);
        }
        return !exists;

    }

    @Override
    public OpinionResponse handleRequest(AddDisapprovalRequest request, Context context) {  // So much of this is subject to change :(
        logger = context.getLogger();

        OpinionResponse response; //Final version wont need to initialize this, this is so it compiles
        try {

        	Disapproval a = createDisapproval(request);
            boolean addApprovalSuccess = disapprovalDAOHelper(a);

            ApprovalsDAO apvDao = new ApprovalsDAO(logger);
            DisapprovalsDAO disDao = new DisapprovalsDAO(logger);
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
            response = new OpinionResponse("Unable to add disapproval for User: " + request.getUsername() + ", altID: " + request.getAlternativeID() + "(error: " + e.getMessage() + ")", 400);
        }

        return response;
    }


}
