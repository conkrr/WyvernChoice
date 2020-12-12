package com.amazonaws.lambda.demo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.amazonaws.lambda.db.AlternativesDAO;
import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.db.ChoicesDAO;
import com.amazonaws.lambda.db.DisapprovalsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Approval;
import com.amazonaws.lambda.demo.model.Disapproval;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class RemoveApprovalHandler  implements RequestHandler<RemoveApprovalRequest, OpinionResponse>
{

	 LambdaLogger logger;

	    private Approval createApproval(RemoveApprovalRequest req) {
	        Timestamp approvalDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

	        Approval approval = new Approval(req.getAlternativeID(), req.getApprovingUserID(), approvalDate, req.getUsername(), req.getChoiceID());
	        return approval;

	    }



	    @Override
	    public OpinionResponse handleRequest(RemoveApprovalRequest request, Context context) { // So much of this is subject to change :(
	        logger = context.getLogger();

	        OpinionResponse response; //Final version wont need to initialize this, this is so it compiles
	        try {

	            Approval a = createApproval(request);
	             
	            ApprovalsDAO apvDao = new ApprovalsDAO(logger);
	            DisapprovalsDAO disDao = new DisapprovalsDAO(logger);
	            List<Approval> appList = apvDao.get(a.getAlternativeId());;
	            List<Disapproval> disList = disDao.get(a.getAlternativeId());;          
	            
	            AlternativesDAO altDAO = new AlternativesDAO(logger);
	            ChoicesDAO choDAO = new ChoicesDAO(logger);
	           boolean isFinalized =  choDAO.get(altDAO.getChoiceID(a.getAlternativeId())).isFinalized;
	       
	           if(!isFinalized) {
	        	   
	        	   boolean exists = apvDao.insertRemoveApproval(a);
	        	   appList = apvDao.get(a.getAlternativeId());
	        	   disList = disDao.get(a.getAlternativeId());
	        	   if (!exists)
	                   response = new OpinionResponse(a.getAlternativeId(), appList, disList, "", 200);
	               else
	               	response = new OpinionResponse(a.getAlternativeId(), appList, disList, "already cleared approvals", 422);
	           } else {
	        		response = new OpinionResponse(a.getAlternativeId(), appList, disList, "cannot remove approval -- Choice has already been finalized", 422);
	           }

	        } catch (Exception e) {
	            response = new OpinionResponse("Unable to add approval for User: " + request.getUsername() + ", altID: " + request.getAlternativeID() + "(error: " + e.getMessage() + ")", 400);
	        }

	        return response;
	    }
}
