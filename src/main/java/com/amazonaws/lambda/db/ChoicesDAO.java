package com.amazonaws.lambda.db;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Choice;

public class ChoicesDAO {
java.sql.Connection connection;
	
	final String tableName = "ChoiceFake";

    public ChoicesDAO() {
    	try  {
    		connection = DatabaseUtil.connect();
    	} catch (Exception e) {
    		connection = null;
    	}
    }

    public Choice getChoice(String id) throws Exception {
        
        try {
            Choice choice = null;
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?;");
            ps.setString(1,  id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                choice = generateChoice(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return choice;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting choice: " + e.getMessage());
        }
    }
    
//    public boolean updateConstant(Constant constant) throws Exception {
//        try {
//        	String query = "UPDATE " + tableName + " SET value=? WHERE name=?;";
//        	PreparedStatement ps = connection.prepareStatement(query);
//            ps.setDouble(1, constant.value);
//            ps.setString(2, constant.name);
//            int numAffected = ps.executeUpdate();
//            ps.close();
//            
//            return (numAffected == 1);
//        } catch (Exception e) {
//            throw new Exception("Failed to update report: " + e.getMessage());
//        }
//    }
    
    public boolean deleteChoice(Choice choice) throws Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?;");
            ps.setString(1, choice.id);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete choice: " + e.getMessage());
        }
    }


    public boolean addChoice(Choice choice) throws Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
            ps.setString(1, choice.id);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Choice c = generateChoice(resultSet);
                resultSet.close();
                return false;
            }

            ps = connection.prepareStatement("INSERT INTO " + tableName + " (id,description) values(?,?);");
            ps.setString(1,  choice.id);
            ps.setString(2,  choice.description);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
    }

//    public List<Constant> getAllConstants() throws Exception {
//        
//        List<Constant> allConstants = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM " + tableName + ";";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                Constant c = generateConstant(resultSet);
//                allConstants.add(c);
//            }
//            resultSet.close();
//            statement.close();
//            return allConstants;
//
//        } catch (Exception e) {
//            throw new Exception("Failed in getting constants: " + e.getMessage());
//        }
//    }
    
    private Choice generateChoice(ResultSet resultSet) throws Exception {
        String id  = resultSet.getString("id");
        String description = resultSet.getString("description");
        return new Choice (id, description);
    }
}
