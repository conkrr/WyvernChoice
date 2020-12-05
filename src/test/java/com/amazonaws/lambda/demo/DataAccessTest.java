package com.amazonaws.lambda.demo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.db.ApprovalsDAO;
import com.amazonaws.lambda.demo.http.CreateChoiceRequest;
import com.amazonaws.lambda.demo.model.Approval;
import com.google.gson.Gson;

public class DataAccessTest extends LambdaTest {

	
	
	private Approval generateApproval(String alternativeID) {
		//String alternativeID = UUID.randomUUID().toString();
		String choiceID = UUID.randomUUID().toString();
		String userID = UUID.randomUUID().toString();
		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		int status = 1;
		String name = "";

		Random random = new Random();
		for(int i = 0; i < random.nextInt(8)+3; i++) {
			name += (char) random.nextInt(91) + 65;
		}
		return new Approval(alternativeID, userID, timestamp, name, choiceID);
	}
	
	private List<Approval> generateApprovalList() {
		String alternativeID = UUID.randomUUID().toString();
		ArrayList<Approval> approvalList = new ArrayList<Approval>();
		Random random = new Random();
		for(int i = 0; i < random.nextInt(5)+1; i++) {
			approvalList.add(generateApproval(alternativeID));
		}
		return approvalList;
		
	}

	@Test
	public void testGetNonexistentRow() {

		List<Approval> generatedList = generateApprovalList();
		List<Approval> testList = new ArrayList<Approval>();
		ApprovalsDAO dao = new ApprovalsDAO();

		try {
			testList = dao.get(generatedList.get(0).getAlternativeId());
			
		} catch (Exception e) {
			Assert.fail("Invalid:" + e.getMessage());
		}
		Assert.assertEquals(testList.isEmpty(), true);
		Assert.assertNotEquals(generatedList, testList);
		
	}

	@Test
	public void aaatestInsert() {

		List<Approval> testList = generateApprovalList();
		//List<Approval> testList = new ArrayList<Approval>();
		boolean alreadyExists = true;
		ApprovalsDAO dao = new ApprovalsDAO();

		try {
			alreadyExists = dao.insert(testList.get(0));

		} catch (Exception e) {
			Assert.fail("Invalid:" + e.getMessage());
		}
		Assert.assertFalse(alreadyExists);
		
	}
	
//	@Test
//	public void testGet() {
//
//		ApprovalsDAO dao = new ApprovalsDAO();
//		List<Approval> testList = new ArrayList<Approval>();
//		try {
//			testList = dao.get(commonList.get(0).getAlternativeId());
//
//		} catch (Exception e) {
//			Assert.fail("Invalid:" + e.getMessage());
//		}
//		Assert.assertEquals(testList.isEmpty(), false);
//		Assert.assertNotEquals(commonList, testList);
//		
//	}

}
