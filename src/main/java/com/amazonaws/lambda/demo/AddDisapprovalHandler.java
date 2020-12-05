package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.db.DisapprovalsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Approval;
import com.amazonaws.lambda.demo.model.Disapproval;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class AddDisapprovalHandler implements RequestHandler<AddDisapprovalRequest, AddDisapprovalResponse> {


    //************************* THIS CLASS IS JUST AN OUTLINE *************************

//goo goo gah gah
    LambdaLogger logger;

    private Disapproval createDisapproval(AddDisapprovalRequest req) {
        Timestamp disapprovalDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Disapproval disapproval = new Disapproval(req.getAlternativeID(), req.getDisapprovingUserID(), disapprovalDate, req.getUsername(), req.getChoiceID());
        return disapproval;

    }

    boolean disapprovalDAOHelper(Disapproval disapproval) throws Exception {
        // if (logger != null) { logger.log("in AddApproval"); }

        DisapprovalsDAO disapprovalsDAO = new DisapprovalsDAO(/*logger*/);
       // List<Disapproval> disapprovalsList = disapprovalsDAO.get(disapproval.getAlternativeId());

        boolean exists = false; //disapprovalsList.stream().anyMatch(a -> a.getUserId().equals(disapproval.getUserId())); // this is either very cool or very bad

        logger.log("Does this disapproval exist in the database already? " + exists);
        if (!exists) {

            disapprovalsDAO.insert(disapproval);
        }
        return !exists;

    }

    @Override
    public AddDisapprovalResponse handleRequest(AddDisapprovalRequest request, Context context) {  // So much of this is subject to change :(
        logger = context.getLogger();

        AddDisapprovalResponse response; //Final version wont need to initialize this, this is so it compiles
        try {

            Disapproval d = createDisapproval(request);
            boolean addDisapprovalSuccess = disapprovalDAOHelper(d);

            if (addDisapprovalSuccess)
                response = new AddDisapprovalResponse(d.getUserName(), d.getAlternativeId(), d.getChoiceId());
            else
                response = new AddDisapprovalResponse(d.getUserName(), d.getAlternativeId(), d.getChoiceId(), 422);
        } catch (Exception e) {
            response = new AddDisapprovalResponse(400, "Unable to add disapproval for User: " + request.getUsername() + ", altID: " + request.getAlternativeID() + "(error: " + e.getMessage() + ")");
        }

        return response;
    }


}
