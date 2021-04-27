package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CustTable 
{
	
	static Connection c = null;
    static Statement stmt = null;
	
	public static void main(String args[]) {
//		updateCustomerTable(1234, "alf", "checkings", 0.0);
	}

	public static void newCustomer(int accNum, String name, String accType, double balance) 
	{
	      
	     /* try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5434/postgres",
	            "postgres", "1Alfonsog@");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE CUSTOMER " +
	            "(ACCOUNTNUM INT PRIMARY KEY     NOT NULL," +
	            " NAME           TEXT    NOT NULL, " +
	            " ACCOUNTTYPE    TEXT     NOT NULL, " +
	            " BALANCE        REAL)";
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully"); */

	      try 
	      {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5434/postgres",
		            "postgres", "1Alfonsog@");
		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         String sql = "INSERT INTO CUSTOMER (ACCOUNTNUM,NAME,ACCOUNTTYPE,BALANCE) "
		            + "VALUES ('"+accNum+"', '"+name+"', '"+accType+"', '"+balance+"');";
		         stmt.executeUpdate(sql);

		        /* sql = "INSERT INTO CUSTOMER (ID,NAME,AGE,ADDRESS,SALARY) "
		            + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
		         stmt.executeUpdate(sql);

		         sql = "INSERT INTO CUSTOMER (ID,NAME,AGE,ADDRESS,SALARY) "
		            + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
		         stmt.executeUpdate(sql);

		         sql = "INSERT INTO CUSTOMER (ID,NAME,AGE,ADDRESS,SALARY) "
		            + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
		         stmt.executeUpdate(sql); */ 

		         stmt.close();
		         c.commit();
		         c.close();
		   } 
	      
	       catch (Exception e) 
	       {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		   }
		      	 System.out.println("Records created successfully");
	
	}
	
	public static void updateBalance(int accNum, double balance) {
		try 
	      {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5434/postgres",
		            "postgres", "1Alfonsog@");
		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         String sql = "UPDATE CUSTOMER set BALANCE = '"+balance+"' where ACCOUNTNUM = '"+accNum+"';";
		            
		         stmt.executeUpdate(sql);

		         stmt.close();
		         c.commit();
		         c.close();
		   } 
	      
	       catch (Exception e) 
	       {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		   }
		      	 System.out.println("Balance updated successfully");
	}
}
