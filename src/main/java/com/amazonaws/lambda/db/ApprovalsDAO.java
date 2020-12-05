package com.amazonaws.lambda.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import com.amazonaws.lambda.demo.model.Approval;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
//import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class ApprovalsDAO implements DataAccessAsymmetric<Approval>{

	
	private Connection connection;
	private static final String tableName = "Approvals";
	private final LambdaLogger logger;
	
    public ApprovalsDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		connection = DatabaseUtil.connect();
    	} catch (Exception e) {
    		connection = null;
    	}
    }    

	@Override
	public List<Approval> get(String uniqueId) throws Exception { //uniqueID = alternative ID
		logger.log("ApprovalsDAO::get() -- Begin");
//		System.out.println("ApprovalsDAO::get() -- Begin");
    	List<Approval> list;  	
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Users.userID, Users.name, Approvals.status, Approvals.timestamp, Alternatives.alternativeID, Alternatives.choiceID FROM Users JOIN Alternatives ON Users.choiceID = Alternatives.choiceID JOIN Approvals ON Approvals.alternativeID = Alternatives.alternativeID AND Approvals.userID = Users.userID JOIN (SELECT Approvals.userID, MAX(Approvals.timestamp) AS timestamp FROM Approvals WHERE Approvals.alternativeID = ? GROUP BY Approvals.userID) Maxtimes ON Users.userID = Maxtimes.userID AND Approvals.timestamp = Maxtimes.timestamp WHERE Approvals.alternativeID = ?");           
            ps.setString(1,  uniqueId);
            ps.setString(2,  uniqueId);
            ResultSet resultSet = ps.executeQuery();
            list = generate(resultSet);
            resultSet.close();
            ps.close();
//            System.out.println("ApprovalsDAO::get() -- End");
            logger.log("ApprovalsDAO::get() -- End");
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in ApprovalsDAO::get(): " + e.getMessage());
        }
	}

	@Override
	public int delete(String uniqueId) throws Exception {
		logger.log("ApprovalsDAO::delete() -- Begin");
		int rowsAffected = 0;
		
		try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM " + tableName + " WHERE alternativeID = ?;");
            ps.setString(1, uniqueId);
            rowsAffected = ps.executeUpdate();
            ps.close();       
            //logger.log("ApprovalsDAO::delete() -- End");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in ApprovalsDAO::delete(): " + e.getMessage());
		}
		return rowsAffected;
	}

	@Override
	public boolean insert(Approval t) throws Exception {
		logger.log("ApprovalsDAO::insert -- Begin");
//		System.out.println("ApprovalsDAO::insert -- Begin");
		try {
			boolean alreadyExists = !get(t.getAlternativeId()).isEmpty();
//			System.out.println("ApprovalsDAO::insert -- alreadyExists = " + alreadyExists);
			if (!alreadyExists) {
				
				PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (alternativeID, userID, timestamp, status) values(?, ?, ?, ?);");
					ps.setString(1, t.getAlternativeId());
					ps.setString(2, t.getUserId());
					ps.setTimestamp(3, t.getTimestamp());
					ps.setInt(4, 1); // 1 indicates approval
					ps.execute();
			}
			logger.log("ApprovalsDAO::insert() -- End");
//			System.out.println("ApprovalsDAO::insert() -- End");
			return alreadyExists;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in ApprovalsDAO::insert(): " + e.getMessage());
		}
	}

	@Override
	public List<Approval> generate(ResultSet resultSet) throws Exception {
		logger.log("ApprovalsDAO::generate() -- Begin");
        
    	ArrayList<Approval> approvals = new ArrayList<Approval>();
        try {
			while(resultSet.next()) {
				final String userId = resultSet.getString("userID");
				final String userName = resultSet.getString("name");
				final String alternativeId = resultSet.getString("alternativeID");
				final Timestamp timestamp = resultSet.getTimestamp("timestamp");
				final int approveStatus = resultSet.getInt("status");
				final String choiceID = resultSet.getString("choiceID");
				
				if (approveStatus == 1) {
					
					approvals.add(new Approval(alternativeId, userId, timestamp, userName, choiceID));
					logger.log("ApprovalsDAO::generate() -- Added approval to list");
				}
				
			}
		} catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in ApprovalsDAO::generate(): " + e.getMessage());
        }
        logger.log("ApprovalsDAO::generate() -- End");
        return approvals;
	}

}
