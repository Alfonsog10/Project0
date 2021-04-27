package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility 
{

		
		public static Connection getConnection() throws SQLException {
		
			String url = "jdbc:postgresql://localhost:5434/postgres";
			String user = "postgres";
			String pass = "1Alfonsog@";

			return DriverManager.getConnection(url, user, pass);
		}
		

		public static void main(String[] args) {
			try (Connection conn = ConnectionUtility.getConnection()) {
				System.out.println("The Connection was successful!"); 
			} catch(SQLException e) {
				System.out.println("An Error Occured");
				e.printStackTrace();
			}
		}
}
