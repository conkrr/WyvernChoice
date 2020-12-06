package com.amazonaws.lambda.db;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Approval;
import com.amazonaws.lambda.demo.model.Choice;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class ChoicesDAO implements DataAccess<Choice> {
java.sql.Connection connection;
	
	final String tableName = "Choices";
	
	final LambdaLogger logger;
	
    public ChoicesDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		connection = DatabaseUtil.connect();
    	} catch (Exception e) {
    		connection = null;
    	}
    }

    public List<Choice> getAll() throws Exception{
    	List<Choice> choices = new ArrayList<Choice>();
    	try {
            
            PreparedStatement psChoices = connection.prepareStatement("SELECT * FROM " + tableName + ";");
            ResultSet resultSetChoices = psChoices.executeQuery();
            //while (resultSetChoices.next()) {
                choices = generateList(resultSetChoices);
            resultSetChoices.close();
            psChoices.close();
           
            
            logger.log("getAllChoice end");
            

        } catch (Exception e) {
        	
        	e.printStackTrace();
        	
            throw new Exception("Failed in getting all choice: " + e.getMessage());
        }
    	return choices;
    }
    
	@Override
	public Choice get(String uniqueId) throws Exception {
		logger.log("getChoice start");
      try {
      	
      	
      	
          Choice choice = null;
          PreparedStatement psChoices = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?;");
          psChoices.setString(1,  uniqueId);
          ResultSet resultSetChoices = psChoices.executeQuery();
          while (resultSetChoices.next()) {
          choice = generate(resultSetChoices);
          }
          resultSetChoices.close();
          psChoices.close();
         
          
          logger.log("getChoice end");
          return choice;

      } catch (Exception e) {
      	
      	e.printStackTrace();
      	
          throw new Exception("Failed in getting choice: " + e.getMessage());
      }
	}

	@Override
	public int delete(String uniqueId) throws Exception {
		int rowsAffected = 0;
		
		try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM " + tableName + " WHERE choiceID = ?;");
            ps.setString(1, uniqueId);
            rowsAffected = ps.executeUpdate();
            ps.close();       
            //logger.log("AlternativesDAO::delete() -- End");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in ChoicesDAO::delete(): " + e.getMessage());
		}
		return rowsAffected;
	}

	@Override
	public boolean insert(Choice t) throws Exception {
		//logger.log("AlternativesDAO::insert -- Begin");
		try {
			boolean alreadyExists = false;
			Choice c = get(t.id);
			alreadyExists = (c != null);
			 	
			if (!alreadyExists) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (id,description,creationTime,creatingUserID,completionTime,maxParticipants,currentParticipants) VALUES (?,?,?,?,?,?,?);");
					ps.setString(1, t.id);
					ps.setString(2, t.description);
					ps.setTimestamp(3, t.creationDate);
					ps.setString(4, t.creatingUserId);
					ps.setNull(5, Types.TIMESTAMP);
					ps.setInt(6, t.maxParticipants);
					ps.setInt(7, t.currentParticipants);
					ps.execute();
					
					AlternativesDAO dao = new AlternativesDAO(logger);
					for(Alternative a : t.alternatives){
						dao.insert(a);
					}
					
			}
			//logger.log("AlternativesDAO::insert() -- End");
			return alreadyExists;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in ChociesDAO::insert(): " + e.getMessage());
		}
	}

	@Override
	public Choice generate(ResultSet resultSet) throws Exception {
		logger.log("AlternativesDAO::generate() -- Begin");
    	Choice c = null;
        try {
			//while(resultSet.next()) {
				logger.log("AlternativesDAO::generate() -- extraction from resultSet");
				logger.log(resultSet.toString());
				final String choiceId = resultSet.getString("id");
				final String userId = resultSet.getString("creatingUserID");
				final String description = resultSet.getString("description");
				final int maxParticipants = resultSet.getInt("maxParticipants");
				final int currentParticipants = resultSet.getInt("currentParticipants");
				//final boolean isChosen = resultSet.getBoolean("isChosen");
				final Timestamp creationTime = resultSet.getTimestamp("creationTime");
				
				AlternativesDAO dao = new AlternativesDAO(logger);
				List<Alternative> alternatives = dao.get(choiceId);
				c = new Choice(choiceId, description, creationTime, userId, false, alternatives,
						maxParticipants, currentParticipants);
				
			//}

		} catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in AlternativesDAO::generate(): " + e.getMessage());
        }
        logger.log("AlternativesDAO::generate() -- End");
        return c;
	}
    
	
	public List<Choice> generateList(ResultSet resultSet) throws Exception {
		logger.log("AlternativesDAO::generate() -- Begin");
    	List<Choice> list = new ArrayList<Choice>();
        try {
        	while(resultSet.next()) {
				logger.log("AlternativesDAO::generate() -- extraction from resultSet");
				logger.log(resultSet.toString());
				final String choiceId = resultSet.getString("id");
				final String userId = resultSet.getString("creatingUserID");
				final String description = resultSet.getString("description");
				final int maxParticipants = resultSet.getInt("maxParticipants");
				final int currentParticipants = resultSet.getInt("currentParticipants");
				//final boolean isChosen = resultSet.getBoolean("isChosen");
				final Timestamp creationTime = resultSet.getTimestamp("creationTime");
				
				AlternativesDAO dao = new AlternativesDAO(logger);
				List<Alternative> alternatives = dao.get(choiceId);
				Choice c = new Choice(choiceId, description, creationTime, userId, false, alternatives,
						maxParticipants, currentParticipants);
				list.add(c);
			}

		} catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in AlternativesDAO::generate(): " + e.getMessage());
        }
        logger.log("AlternativesDAO::generate() -- End");
        return list;
	}
	
//    public Choice getChoice(String id) throws Exception {
//    	logger.log("getChoice start");
//        try {
//        	
//        	
//        	
//            Choice choice = null;
//            PreparedStatement psChoices = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?;");
//            PreparedStatement psAlternatives = connection.prepareStatement("SELECT * FROM " + subEntityTableName + " WHERE choiceID=?;");
//            psChoices.setString(1,  id);
//            psAlternatives.setString(1,  id);
//            ResultSet resultSetChoices = psChoices.executeQuery();
//            ResultSet resultSetAlternatives = psAlternatives.executeQuery();
//            while (resultSetChoices.next()) {
//                choice = generateChoice(resultSetChoices, resultSetAlternatives);
//            }
//            resultSetChoices.close();
//            psChoices.close();
//            resultSetAlternatives.close();
//            psAlternatives.close();
//            
//            
//            logger.log("getChoice end");
//            return choice;
//
//        } catch (Exception e) {
//        	
//        	e.printStackTrace();
//        	
//            throw new Exception("Failed in getting choice: " + e.getMessage());
//        }
//    }
//    
//    public List<Choice> getAllChoices() throws Exception {
//    	logger.log("getAllChoices start");
//        try {
//        	
//        	List<Choice> choices = null;
//        	
//            Choice choice = null;
//            PreparedStatement psChoices = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?;");
//            PreparedStatement psAlternatives = connection.prepareStatement("SELECT * FROM " + subEntityTableName + " WHERE choiceID=?;");
//            psChoices.setString(1,  id);
//            psAlternatives.setString(1,  id);
//            ResultSet resultSetChoices = psChoices.executeQuery();
//            ResultSet resultSetAlternatives = psAlternatives.executeQuery();
//            while (resultSetChoices.next()) {
//                choice = generateChoice(resultSetChoices, resultSetAlternatives);
//            }
//            resultSetChoices.close();
//            psChoices.close();
//            resultSetAlternatives.close();
//            psAlternatives.close();
//            
//            
//            logger.log("getChoice end");
//            return choice;
//
//        } catch (Exception e) {
//        	
//        	e.printStackTrace();
//        	
//            throw new Exception("Failed in getting choice: " + e.getMessage());
//        }
//    }
//    
////    public boolean updateConstant(Constant constant) throws Exception {
////        try {
////        	String query = "UPDATE " + tableName + " SET value=? WHERE name=?;";
////        	PreparedStatement ps = connection.prepareStatement(query);
////            ps.setDouble(1, constant.value);
////            ps.setString(2, constant.name);
////            int numAffected = ps.executeUpdate();
////            ps.close();
////            
////            return (numAffected == 1);
////        } catch (Exception e) {
////            throw new Exception("Failed to update report: " + e.getMessage());
////        }
////    }
//    
//    public boolean deleteChoice(String choiceID) throws Exception {
//        try {
//            PreparedStatement psChoice = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?;");
//            PreparedStatement psAlternative = connection.prepareStatement("DELETE FROM " + subEntityTableName + " WHERE choiceId = ?;");
//            psChoice.setString(1, choiceID);
//            psAlternative.setString(1,  choiceID);
//            psAlternative.executeUpdate();
//            int numAffected = psChoice.executeUpdate();
//            psChoice.close();
//            
//            return (numAffected > 0);
//
//        } catch (Exception e) {
//            throw new Exception("Failed to delete choice: " + e.getMessage());
//        }
//    }
//
//
//    public boolean addChoice(Choice choice) throws Exception {
//    	logger.log("addChoice start");
//        try {
////            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
////            ps.setString(1, choice.id);
////            ResultSet resultSet = ps.executeQuery();
////            
////            // already present?
////            while (resultSet.next()) {
////                Choice c = generateChoice(resultSet);
////                resultSet.close();
////                return false;
////            }
//        	boolean exists = getChoice(choice.id) != null;
//
//        	if (!exists) {
//        		
//        		logger.log("in add choice");
//        		PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (id, description,creationTime,creatingUserID,completionTime,maxParticipants,currentParticipants) values(?, ?, ?, ?, ?, ?, ?);");
//                ps.setString(1,  choice.id.toString());
//                ps.setString(2,  choice.description);
//                ps.setTimestamp(3, choice.creationDate);
//                ps.setString(4, choice.creatingUserId);
//                ps.setNull(5, java.sql.Types.TIMESTAMP);
//                ps.setInt(6, choice.maxParticipants);
//                ps.setInt(7, choice.currentParticipants);
//                ps.execute();
//                
//                for(int i = 0; i < choice.alternatives.size(); i++) {
//                	ps = connection.prepareStatement("INSERT INTO " + subEntityTableName + " (alternativeID,choiceID,description,isChosen) values(?, ?, ?, ?);");
//                    ps.setString(2,  choice.id.toString());
//                    ps.setString(1,  choice.alternatives.get(i).alternativeID);
//                    ps.setString(3, choice.alternatives.get(i).name);
//                    ps.setBoolean(4, false);
//                    ps.execute();
//                }
//                
//        	}
//        	
//        	logger.log("addChoice end");
//            return exists;
//
//        } catch (Exception e) {
//        	logger.log(e.toString());
//            throw new Exception("Failed to insert choice: " + e.getMessage());
//        }
//    }
//
////    public List<Constant> getAllConstants() throws Exception {
////        
////        List<Constant> allConstants = new ArrayList<>();
////        try {
////            Statement statement = connection.createStatement();
////            String query = "SELECT * FROM " + tableName + ";";
////            ResultSet resultSet = statement.executeQuery(query);
////
////            while (resultSet.next()) {
////                Constant c = generateConstant(resultSet);
////                allConstants.add(c);
////            }
////            resultSet.close();
////            statement.close();
////            return allConstants;
////
////        } catch (Exception e) {
////            throw new Exception("Failed in getting constants: " + e.getMessage());
////        }
////    }
//    
//    private Choice generateChoice(ResultSet resultSetChoice, ResultSet resultSetAlternatives) throws Exception {
//    	logger.log("generateChoice start");
//    	String id  = resultSetChoice.getString("id");
//        String description = resultSetChoice.getString("description");
//        Timestamp creationTime = resultSetChoice.getTimestamp("creationTime");
//        String userId  = resultSetChoice.getString("creatingUserId");
//        //Timestamp completionTime = resultSetChoice.getTimestamp("completionTime");
//        int maxParticipants = resultSetChoice.getInt("maxParticipants");
//        int currentParticipants = resultSetChoice.getInt("currentParticipants");
//        		
//        List<Alternative> alternatives = new ArrayList<Alternative>();
//        while(resultSetAlternatives.next()) {
//        	String aId = resultSetAlternatives.getString("alternativeID");
//        	String aDesc = resultSetAlternatives.getString("description");
//        	boolean aIsChosen = resultSetAlternatives.getBoolean("isChosen");
//        	
//        	alternatives.add(new Alternative(aDesc, aId, approvals, aIsChosen));
//        }
//        logger.log("generateChoice end");
//        return new Choice (id, description, creationTime, userId, false, alternatives, maxParticipants, currentParticipants);
//    }
}
