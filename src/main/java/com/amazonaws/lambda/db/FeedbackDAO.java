package com.amazonaws.lambda.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Feedback;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class FeedbackDAO implements DataAccessAsymmetric<Feedback> {
	
	private Connection connection;
	private static final String tableName = "Feedback";
	private final LambdaLogger logger;
	
    public FeedbackDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		connection = DatabaseUtil.connect();
    	} catch (Exception e) {
    		connection = null;
    	}
    }    
	@Override
	public List<Feedback> get(String uniqueId) throws Exception {
		if(logger != null )logger.log("FeedbackDAO::get() -- Begin");
//		System.out.println("FeedbackDAO::get() -- Begin");
    	List<Feedback> list;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE alternativeID = ?;");           
            ps.setString(1,  uniqueId);
            ResultSet resultSet = ps.executeQuery();
            list = generate(resultSet);
            resultSet.close();
            ps.close();
//            System.out.println("FeedbackDAO::get() -- End");
            if(logger != null )logger.log("FeedbackDAO::get() -- End");
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in FeedbackDAO::get(): " + e.getMessage());
        }
	}

	@Override
	public int delete(String uniqueId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean insert(Feedback t) throws Exception {

		if(logger != null )logger.log("FeedbackDAO::insert -- Begin");
//		System.out.println("FeedbackDAO::insert -- Begin");
		try {
			boolean alreadyExists = get(t.alternativeID).contains(t);
//			System.out.println("FeedbackDAO::insert -- alreadyExists = " + alreadyExists);
//			if (!alreadyExists) {
				
				PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " Feedback (alternativeID, content, creationTime, creatingUser ) VALUES (?,?,?,?);");
					ps.setString(1, t.alternativeID);
					ps.setString(2, t.text);
					ps.setTimestamp(3, t.timestamp);
					ps.setString(4, t.userID);
					ps.execute();
//			}
			if(logger != null )logger.log("FeedbackDAO::insert() -- End");
//			System.out.println("FeedbackDAO::insert() -- End");
			return alreadyExists;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in FeedbackDAO::insert(): " + e.getMessage());
		}
	}

	@Override
	public List<Feedback> generate(ResultSet resultSet) throws Exception{
		if(logger != null )logger.log("FeedbackDAO::generate() -- Begin");
        
    	ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
        try {
			while(resultSet.next()) {
				final String userName = resultSet.getString("creatingUser");
				final String content = resultSet.getString("content");
				final Timestamp timestamp = resultSet.getTimestamp("creationTime");
				final int approveStatus = resultSet.getInt("status");
				final String altID = resultSet.getString("alternativeID");
				
				if (approveStatus == 1) {
					
					feedbacks.add(new Feedback(userName, content, timestamp, altID));
					if(logger != null )logger.log("FeedbackDAO::generate() -- Added approval to list");
				}
				
			}
		} catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in FeedbackDAO::generate(): " + e.getMessage());
        }
        if(logger != null )logger.log("FeedbackDAO::generate() -- End");
        return feedbacks;
	}

}
