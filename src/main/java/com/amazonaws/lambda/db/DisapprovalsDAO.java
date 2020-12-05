package com.amazonaws.lambda.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import com.amazonaws.lambda.demo.model.Disapproval;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
//import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class DisapprovalsDAO implements DataAccessAsymmetric<Disapproval>{

	
	private Connection connection;
	private static final String tableName = "Approvals";
	private final LambdaLogger logger;
	
    public DisapprovalsDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		connection = DatabaseUtil.connect();
    	} catch (Exception e) {
    		connection = null;
    	}
    }    

	@Override
	public List<Disapproval> get(String uniqueId) throws Exception { //uniqueID = alternative ID
		logger.log("DisapprovalsDAO::get() -- Begin");
//		System.out.println("DisapprovalsDAO::get() -- Begin");
    	List<Disapproval> list;  	
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Users.userID, Users.name, Approvals.status, Approvals.timestamp, Alternatives.alternativeID, Alternatives.choiceID FROM Users JOIN Alternatives ON Users.choiceID = Alternatives.choiceID JOIN Approvals ON Approvals.alternativeID = Alternatives.alternativeID AND Approvals.userID = Users.userID JOIN (SELECT Approvals.userID, MAX(Approvals.timestamp) AS timestamp FROM Approvals WHERE Approvals.alternativeID = ? GROUP BY Approvals.userID) Maxtimes ON Users.userID = Maxtimes.userID AND Approvals.timestamp = Maxtimes.timestamp WHERE Approvals.alternativeID = ?");           
            ps.setString(1,  uniqueId);
            ps.setString(2,  uniqueId);
            ResultSet resultSet = ps.executeQuery();
            list = generate(resultSet);
            resultSet.close();
            ps.close();
//            System.out.println("DisapprovalsDAO::get() -- End");
           logger.log("DisapprovalsDAO::get() -- End");
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in DisapprovalsDAO::get(): " + e.getMessage());
        }
	}

	@Override
	public int delete(String uniqueId) throws Exception {
		logger.log("DisapprovalsDAO::delete() -- Begin");
		int rowsAffected = 0;
		
		try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM " + tableName + " WHERE alternativeID = ?;");
            ps.setString(1, uniqueId);
            rowsAffected = ps.executeUpdate();
            ps.close();       
            logger.log("DisapprovalsDAO::delete() -- End");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in DisapprovalsDAO::delete(): " + e.getMessage());
		}
		return rowsAffected;
	}

	@Override
	public boolean insert(Disapproval t) throws Exception {
		logger.log("DisapprovalsDAO::insert -- Begin");
//		System.out.println("DisapprovalsDAO::insert -- Begin");
		try {
			boolean alreadyExists = !get(t.getAlternativeId()).isEmpty();
//			System.out.println("DisapprovalsDAO::insert -- alreadyExists = " + alreadyExists);
//			if (!alreadyExists) {
				
				PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (alternativeID, userID, timestamp, status) values(?, ?, ?, ?);");
					ps.setString(1, t.getAlternativeId());
					ps.setString(2, t.getUserId());
					ps.setTimestamp(3, t.getTimestamp());
					ps.setInt(4, -1); // 1 indicates disapproval
					ps.execute();
//			}
			logger.log("DisapprovalsDAO::insert() -- End");
//			System.out.println("DisapprovalsDAO::insert() -- End");
			return alreadyExists;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in DisapprovalsDAO::insert(): " + e.getMessage());
		}
	}

	@Override
	public List<Disapproval> generate(ResultSet resultSet) throws Exception {
		logger.log("DisapprovalsDAO::generate() -- Begin");
        
    	ArrayList<Disapproval> approvals = new ArrayList<Disapproval>();
        try {
			while(resultSet.next()) {
				final String userId = resultSet.getString("userID");
				final String userName = resultSet.getString("name");
				final String alternativeId = resultSet.getString("alternativeID");
				final Timestamp timestamp = resultSet.getTimestamp("timestamp");
				final int approveStatus = resultSet.getInt("status");
				final String choiceID = resultSet.getString("choiceID");
				
				if (approveStatus == -1) {
					approvals.add(new Disapproval(alternativeId, userId, timestamp, userName, choiceID));
				}
				
			}
		} catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in DisapprovalsDAO::generate(): " + e.getMessage());
        }
        logger.log("DisapprovalsDAO::generate() -- End");
        return approvals;
	}

}
