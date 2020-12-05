package com.amazonaws.lambda.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.amazonaws.lambda.demo.model.Alternative;
import com.amazonaws.lambda.demo.model.Approval;
import com.amazonaws.lambda.demo.model.Disapproval;
import com.amazonaws.lambda.demo.model.Opinion;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class AlternativesDAO implements DataAccessAsymmetric<Alternative>{

	private Connection connection;
	private static final String tableName = "Alternatives";
	private final LambdaLogger logger;
	
    public AlternativesDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		connection = DatabaseUtil.connect();
    	} catch (Exception e) {
    		connection = null;
    	}
    }    
	
	@Override
	public List<Alternative> get(String uniqueId) throws Exception {
		//logger.log("AlternativesDAO::get() -- Begin");
    	List<Alternative> list;  	
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + tableName + ".choiceID = ?");           
            ps.setString(1,  uniqueId);
            ResultSet resultSet = ps.executeQuery();
            list = generate(resultSet);
            resultSet.close();
            ps.close();
            //logger.log("AlternativesDAO::get() -- End");
            return list;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in AlternativesDAO::get(): " + e.getMessage());
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
			throw new Exception("Exception in AlternativesDAO::delete(): " + e.getMessage());
		}
		return rowsAffected;
	}

	@Override
	public boolean insert(Alternative t) throws Exception {
		//logger.log("AlternativesDAO::insert -- Begin");
				try {
					boolean alreadyExists = false;
					//perhaps make this a function?
					List<Alternative> list = get(t.getChoiceID());
					ListIterator<Alternative> iterator = list.listIterator();
					while (iterator.hasNext()) { 
						Alternative apv = iterator.next();
						if(apv.getAlternativeID() == t.getAlternativeID()) {
							alreadyExists = true;
							break;
						}
					} 	
					if (!alreadyExists) {
						PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName + " (alternativeID, choiceID, description, isChosen) values(?, ?, ?, ?);");
							ps.setString(1, t.getAlternativeID());
							ps.setString(2, t.getChoiceID());
							ps.setString(3, t.getName());
							ps.setBoolean(4, false); // 1 indicates Alternative
							ps.execute();
					}
					//logger.log("AlternativesDAO::insert() -- End");
					return alreadyExists;
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Exception in AlternativesDAO::insert(): " + e.getMessage());
				}
	}

	@Override
	public List<Alternative> generate(ResultSet resultSet) throws Exception {//logger.log("AlternativesDAO::generate() -- Begin");
        
    	ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
        try {
			while(resultSet.next()) {
				final String choiceId = resultSet.getString("choiceID");
				final String description = resultSet.getString("description");
				final String alternativeId = resultSet.getString("alternativeID");
				final boolean isChosen = resultSet.getBoolean("isChosen");
				
				ApprovalsDAO dao = new ApprovalsDAO(logger);
				List<Approval> approvals = new ArrayList<Approval>(dao.get(alternativeId));
				DisapprovalsDAO dao2 = new DisapprovalsDAO(logger);
				List<Disapproval> disapprovals = new ArrayList<Disapproval>(dao2.get(alternativeId));

					alternatives.add(new Alternative(description, choiceId, alternativeId, approvals, disapprovals, isChosen));
				
				
			}
		} catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Exception in AlternativesDAO::generate(): " + e.getMessage());
        }
        //logger.log("AlternativesDAO::generate() -- End");
        return alternatives;
	}

}
