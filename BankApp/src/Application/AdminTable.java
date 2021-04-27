package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AdminTable 
{

	static Connection c = null;
    static Statement stmt = null;
    
	public static void main( String args[] ) 
	{
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5434/postgres",
	            "postgres", "1Alfonsog@");
	         System.out.println("Opened database successfully");

	        /* stmt = c.createStatement();
	         String sql = "CREATE TABLE ADMIN " +
	            "(ID INT PRIMARY KEY     NOT NULL," +
	            " USERNAME       TEXT    NOT NULL, " +
	            " NAME    	 	 TEXT    NOT NULL, " +
	            " LASTNAME       TEXT    NOT NULL)"; */
	         
	         stmt = c.createStatement();
	         String sql = "INSERT INTO ADMIN(ID,USERNAME,NAME,LASTNAME) "
	            + "VALUES (0001, 'BObrian', 'Bob', 'Obrian');";
	         stmt.executeUpdate(sql);
	
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");

	}
}
