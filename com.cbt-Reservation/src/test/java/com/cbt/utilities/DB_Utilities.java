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

	public static void main(String[] args) {
		boolean b = establishConnection();
		System.out.println(b);
	}
	
	
	public static boolean establishConnection() {
		String url = ConfigReader.getProperties("connectionString");
		String user = ConfigReader.getProperties("DB_username");
		String password = ConfigReader.getProperties("DB_password");
		
		try {
			myConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(myConn!= null)return true ; 
		return false;
	}
	
	
	public static List<Map<String, Object>> getQueryResultMap(String query) {
		executeQuery(query);
		List<Map<String, Object>> queryResult = new ArrayList<>();
		ResultSetMetaData rsmd;

		try {
			rsmd = resultSet.getMetaData();
			while (resultSet.next()) {
				Map<String, Object> resultMap = new HashMap<>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					resultMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
				}
				queryResult.add(resultMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryResult;
	}
	
	public static List<String> getColumnNames(String query) {
		executeQuery(query);
		List<String> columns = new ArrayList<>();
		ResultSetMetaData rsmd;

		try {
			rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
				columns.add(rsmd.getColumnName(i));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return columns;

	}
	
	private static void executeQuery(String query) {
		try {
			statement = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void destroy() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
