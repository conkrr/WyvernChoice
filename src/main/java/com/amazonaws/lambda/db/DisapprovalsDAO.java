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

public class DisapprovalsDAO implements DataAccess<List<Disapproval>>{

	
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
	public List<Disapproval> get(String uniqueId) throws Exception {
		logger.log("DisapprovalsDAO::get() -- Begin");
    	List<Disapproval> list;  	
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Users.userID, Users.name, Disapprovals.status FROM Users JOIN Alternatives ON Users.choiceID = Alternatives.choiceID JOIN Disapprovals ON Disapprovals.alternativeID = Alternatives.alternativeID AND Disapprovals.userID = Users.userID JOIN (SELECT Disapprovals.userID, MAX(Disapprovals.timestamp) AS timestamp FROM Disapprovals WHERE Disapprovals.alternativeID = ? GROUP BY Disapprovals.userID) Maxtimes ON Users.userID = Maxtimes.userID AND Disapprovals.timestamp = Maxtimes.timestamp WHERE Disapprovals.alternativeID = ?");           
            ps.setString(1,  uniqueId);
            ps.setString(2,  uniqueId);
            ResultSet resultSet = ps.executeQuery();
            list = generate(resultSet);
            resultSet.close();
            ps.close();
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
	public boolean insert(List<Disapproval> t) throws Exception {
		logger.log("DisapprovalsDAO::insert -- Begin");
		try {
			boolean alreadyExists = get(t.get(0).getAlternativeId()) != null;
			if (!alreadyExists) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (alternativeID, userID, timestamp, status) values(?, ?, ?, ?);");
				ListIterator<Disapproval> iterator = t.listIterator();
				while (iterator.hasNext()) { 
					Disapproval apv = iterator.next();
					ps.setString(1, apv.getAlternativeId());
					ps.setString(2, apv.getUserId());
					ps.setTimestamp(3, apv.getTimestamp());
					ps.setInt(4, -1); // -1 indicates Disapproval
					ps.execute();
				} 
			}
			logger.log("DisapprovalsDAO::insert() -- End");
			return alreadyExists;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in DisapprovalsDAO::insert(): " + e.getMessage());
		}
	}

	@Override
	public List<Disapproval> generate(ResultSet resultSet) throws Exception {
		logger.log("DisapprovalsDAO::generate() -- Begin");
        
    	ArrayList<Disapproval> Disapprovals = new ArrayList<Disapproval>();
        try {
			while(resultSet.next()) {
				final String userId = resultSet.getString("userID");
				final String userName = resultSet.getString("name");
				final String alternativeId = resultSet.getString("alternativeID");
				final Timestamp timestamp = resultSet.getTimestamp("timestamp");
				int approveStatus = resultSet.getInt("status");
				
				if (approveStatus == -1) {
					Disapprovals.add(new Disapproval(alternativeId, userId, timestamp, userName));
				}
				
			}
		} catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in DisapprovalsDAO::generate(): " + e.getMessage());
        }
        logger.log("DisapprovalsDAO::generate() -- End");
        return Disapprovals;
	}

}
