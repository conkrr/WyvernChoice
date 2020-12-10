package com.amazonaws.lambda.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.lambda.demo.model.User;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class UsersDAO {
java.sql.Connection connection;
	
	final String tableName = "Users";
	//final String subEntityTableName = "Alternatives";
	
	final LambdaLogger logger;
	
    public UsersDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		connection = DatabaseUtil.connect();
    	} catch (Exception e) {
    		connection = null;
    	}
    }

    public User getUser(String id) throws Exception {
        
        try {
            User user = null;
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE userID=?;");
           
            ps.setString(1,  id);
           
            
            
            ResultSet resultSet = ps.executeQuery();
         
            
            
            
            while (resultSet.next()) {
                user = generateUser(resultSet);
            }
            
            
            
            
            resultSet.close();
            ps.close();
            
            return user;

        } catch (Exception e) {
        	
        	e.printStackTrace();
        	
            throw new Exception("Failed in getting user: " + e.getMessage());
        }
    }
    
    public boolean isChoiceOpen(String choiceId) throws Exception {
    	 try {
             int currentUsers = 0;
             int maxUsers = 0;
             
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM Choices WHERE id=?;");
            
             ps.setString(1,  choiceId);
            
             ResultSet resultSet = ps.executeQuery();
             
             while (resultSet.next()) {
            	 maxUsers = resultSet.getInt("maxParticipants");
            	 currentUsers = resultSet.getInt("currentParticipants");
             }
          
             logger.log("UsersDAO::isChoiceOpen -- Max: " + maxUsers + " Current: " + currentUsers);

             resultSet.close();
             ps.close();
             
             return currentUsers < maxUsers;

         } catch (Exception e) {
         	
         	e.printStackTrace();
         	
             throw new Exception("Failed in getting choice users: " + e.getMessage());
         }
    }
    



    public boolean addUser(User user) throws Exception {
        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
//            ps.setString(1, choice.id);
//            ResultSet resultSet = ps.executeQuery();
//            
//            // already present?
//            while (resultSet.next()) {
//                Choice c = generateChoice(resultSet);
//                resultSet.close();
//                return false;
//            }
        	boolean exists = getUser(user.userId) != null;
        	if (!exists) {
        		
        		logger.log("in add user");
        		PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (choiceID, name, password, userID) values(?, ?, ?, ?);");
        		             
        		
        		ps.setString(1, user.choiceId);
                ps.setString(2, user.username);
                ps.setString(3, user.password);
                ps.setString(4, user.userId.toString());
                ps.execute();
                ps = connection.prepareStatement("UPDATE Choices SET currentParticipants = currentParticipants + 1 WHERE id=?;");
                ps.setString(1, user.choiceId);
                ps.execute();     
        	}
        	
        	
            return exists;

        } catch (Exception e) {
        	logger.log(e.toString());
            throw new Exception("Failed to insert User: " + e.getMessage());
        }
    }

    private User generateUser(ResultSet resultSet) throws Exception {
        String id  = resultSet.getString("userID");
        String username = resultSet.getString("name");
        String password = resultSet.getString("password");
        String choiceID = resultSet.getString("choiceID");
        
        
        return new User(choiceID, username, password, id);
    }
}
