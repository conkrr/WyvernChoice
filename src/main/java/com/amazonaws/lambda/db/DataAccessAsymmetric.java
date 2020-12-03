package com.amazonaws.lambda.db;

import java.sql.ResultSet;
import java.util.List;

public interface DataAccessAsymmetric<T> {

	
	//queries database and constructs list of wrapper objects 
	List<T> get(String uniqueId) throws Exception;
	
	//deletes rows from database, returns number of rows affected
	int delete(String uniqueId) throws Exception;
	
	// inserts single row into database, returns whether or not row already exists
	boolean insert(T t) throws Exception;
	
	//uses result of query to generate object
	List<T> generate(ResultSet resultSet) throws Exception;
	
	
}
