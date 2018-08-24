package com.cbt.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DB_Utilities {
	
private static Connection myConn;
private static Statement statement;
private static ResultSet resultSet;

	
	public static boolean establishConnection() {
		String url = ConfigReader.getProperties("connectionString");
		String user = ConfigReader.getProperties("DB_username");
		String password = ConfigReader.getProperties("DB_password");
		
		try {
			myConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(myConn!= null)			return true ; 
		return false;
	}
	
	public List<Map<String, Object>> getQueryResult(String query) throws SQLException{
		
		List<Map<String, Object>> queryResult = new ArrayList<>();
		
		statement = myConn.createStatement();
		resultSet = statement.executeQuery(query);
		
		ResultSetMetaData rsmd = resultSet.getMetaData();
		
		while(resultSet.next()) {
			
			Map<String, Object> resultMap = new HashMap<>();
			
			for(int i = 1; i < rsmd.getColumnCount(); i++) {
				resultMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
			}
			queryResult.add(resultMap);
		}
		
		return queryResult;
	}
}
