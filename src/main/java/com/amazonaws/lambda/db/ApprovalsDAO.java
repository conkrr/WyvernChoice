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

public class ApprovalsDAO implements DataAccess<List<Approval>>{

	
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
	public List<Approval> get(String uniqueId) throws Exception {
		logger.log("ApprovalsDAO::get() -- Begin");
    	List<Approval> list;  	
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Users.userID, Users.name, Approvals.status FROM Users JOIN Alternatives ON Users.choiceID = Alternatives.choiceID JOIN Approvals ON Approvals.alternativeID = Alternatives.alternativeID AND Approvals.userID = Users.userID JOIN (SELECT Approvals.userID, MAX(Approvals.timestamp) AS timestamp FROM Approvals WHERE Approvals.alternativeID = ? GROUP BY Approvals.userID) Maxtimes ON Users.userID = Maxtimes.userID AND Approvals.timestamp = Maxtimes.timestamp WHERE Approvals.alternativeID = ?");           
            ps.setString(1,  uniqueId);
            ps.setString(2,  uniqueId);
            ResultSet resultSet = ps.executeQuery();
            list = generate(resultSet);
            resultSet.close();
            ps.close();
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
            logger.log("ApprovalsDAO::delete() -- End");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in ApprovalsDAO::delete(): " + e.getMessage());
		}
		return rowsAffected;
	}

	@Override
	public boolean insert(List<Approval> t) throws Exception {
		logger.log("ApprovalsDAO::insert -- Begin");
		try {
			boolean alreadyExists = get(t.get(0).getAlternativeId()) != null;
			if (!alreadyExists) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (alternativeID, userID, timestamp, status) values(?, ?, ?, ?);");
				ListIterator<Approval> iterator = t.listIterator();
				while (iterator.hasNext()) { 
					Approval apv = iterator.next();
					ps.setString(1, apv.getAlternativeId());
					ps.setString(2, apv.getUserId());
					ps.setTimestamp(3, apv.getTimestamp());
					ps.setInt(4, 1); // 1 indicates approval
					ps.execute();
				} 
			}
			logger.log("ApprovalsDAO::insert() -- End");
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
				int approveStatus = resultSet.getInt("status");
				
				if (approveStatus == 1) {
					approvals.add(new Approval(alternativeId, userId, timestamp, userName));
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
