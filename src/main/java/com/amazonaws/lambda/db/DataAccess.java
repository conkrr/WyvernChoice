package com.amazonaws.lambda.db;

import java.sql.ResultSet;

public interface DataAccess<T> {
	
	//queries database and constructs wrapper object 
	T get(String uniqueId) throws Exception;
	
	//deletes rows from database, returns number of rows affected
	int delete(String uniqueId) throws Exception;
	
	// inserts row into database, returns whether or not row already exists
	boolean insert(T t) throws Exception;
	
	//uses result of query to generate object
	T generate(ResultSet resultSet) throws Exception;
	
	
}
