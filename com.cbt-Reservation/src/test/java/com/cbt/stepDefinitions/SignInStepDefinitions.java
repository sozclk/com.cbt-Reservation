package com.cbt.stepDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cbt.utilities.DB_Utilities;

public class SignInStepDefinitions {
	
	private static Connection myConn;
	private static Statement statement;
	private static ResultSet resultSet;

	public static void main(String[] args) throws SQLException {
		List<String> columns = new ArrayList<>();
		List<Map<String, Object>> mymap = new ArrayList<>();
		DB_Utilities.establishConnection();
		mymap = DB_Utilities.getQueryResultMap("select firstname, id from users\n" + 
				"where id between 30 and 40;");
		
		
//		ResultSet rs ;
//		mymap =  DB_Utilities.getQueryResultMapList("select id from users\n" + 
//				"where id between 30 and 40");
		
		System.out.println(mymap);
	}
}
