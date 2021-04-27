package com.application.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtility 
{

	public static Connection getConnection() throws SQLException {
		/*
		 * The getConnection method of the DriverManager
		 * uses the following syntax:
		 * getConnection(db_url, username, password);
		 * 
		 * url syntax: "jdbc:postgresql://[host_location]:[port]/[db_name]"
		 */
		String url = "jdbc:postgresql://localhost:5434/postgres";
		String user = "postgres";
		String pass = "1Alfonsog@";
		return DriverManager.getConnection(url, user, pass);
	}
	
	// This main method is used to test our connection!
	public static void main(String[] args) {
		// try with resources allows you to pass a resource argument to a try statement
		// in this case the
		try (Connection conn = ConnectionUtility.getConnection()) {
			System.out.println("The Connection was successful!"); // if this works, our connection was estbalished
		} catch(SQLException e) {
			System.out.println("An Error Occured");
			e.printStackTrace();
		}
	}
	
	/* static method used to get employee dao impl objects instead of 
	 * creating them directly...We can also add logic to determine which
	 * DAO impl we want here in projects with multiple database language
	 * connections
	 */
	
	//public static EmployeeDAO getEmployeeDAO() {
		//return new EmployeeDAOImpl_postgre();
	//} 
	
	//public static LoginDAO getLoginDAO() {
		//return new LoginDAOImpl();
	//}
}

