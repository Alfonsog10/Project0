package Application;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

class AccountEmp
{
		  String Username, Password;
		 
		  static ArrayList<AccountEmp> list = new ArrayList<AccountEmp>();
		 
		  AccountEmp(){}
		 
		  AccountEmp(String username, String password)
		 	{
			 	Username = username;
			 	Password = password;
	        }
		 
		  void insert(String username, String password) // input user name and password
		    { 
		    	Username = username;
			 	Password = password;
		    }
		 
		 public  void display_details()
		    {
		        System.out.println("Username :" +Username);
		        System.out.println("Password : "+Password + "\n\n");
		    }
		 
		 public  void updateList(ArrayList<AccountEmp> list, AccountEmp employee) //adding users to list
		 	{
				list.add(employee);		
			}
		 
		 void fetchAccounts() 
		 {	
			 
			 try 
			 {
				 Class.forName("org.postgresql.Driver");
		         EmployTable.c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5434/postgres",
		            "postgres", "1Alfonsog@");
		         EmployTable.c.setAutoCommit(false);
		         System.out.println("Opened database successfully");
		         
		         EmployTable.stmt = EmployTable.c.createStatement();
				 ResultSet rs = EmployTable.stmt.executeQuery( "SELECT * FROM EMPLOYEE;" );
				 while(rs.next()) 
				 {
					 String username = rs.getString("username");
					 String password = rs.getString("password");
					 
					 AccountEmp employee = new AccountEmp(username, password);

					 updateList(list, employee);

				 }
			 } 
			 
			 	catch (Exception e) 
		       	{
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.exit(0);
			    }
		 }
		 
}




public class Employee 
{
		public void employeeMenu(AccountEmp employees) //method invoked in the Start class to execute the menu
		{
	            AccountEmp employee = new AccountEmp();

	            
	            int userChoice;
				boolean quit = false; 


				Scanner in = new Scanner(System.in);   
				
				do {
				
					boolean access = false;
				
				do {
				
					System.out.println("Enter Username : ");
					String username = in.next();
					System.out.println("Enter Password : ");
					String password = in.next();

	        	  	//checking array list
					int i = 0;
	        	  	for(AccountEmp user: employees.list) 
	        	  	{
	        	  		if (username.equals(user.Username) && password.equals(user.Password)) 
	        	  		{
	        	  			access = true;
	        	  			employee = user;
	        	  			break;
	        	  		} 
	        	  		
	        	  		else if(i+1 == employees.list.size()) 
	        	  		{
	        	  			if (username.equals(user.Username)) 
	    				    {
	    				        System.out.println("Invalid Password!");
	    				    } 
	    				    
	    				    else if (password.equals(user.Password)) 
	    				    {
	    				        System.out.println("Invalid Username!");
	    				    } 
	    				    
	    				    else 
	    				    {
	    				        System.out.println("Invalid Username & Password!");
	    				    }
	        	  		}
	        	  		
	        	  		i++;
	        	  	} 
	        	  	

				} 
				
					while(!access);
        	  		if (access)
        	  		{
			        
        	  		System.out.println("Access Granted! Welcome! \n");
			        
			        do {
		                  
		                  System.out.println("1) Access One Account:  ");
		                  System.out.println("2) Access All Accounts:  ");
		                  System.out.println("0) Quit");
				    	
		                  userChoice = in.nextInt();
		                  
		                  switch (userChoice) 
		                  {

		                  	case 1: 
		                  		boolean found = false;
		                  		do {
		                  			int i = 0;		
		                  			
			                  		System.out.print("Enter customer account number: ");
			                  		int accNum = in.nextInt();
			                  		for(Account customer: Account.list){
			                  			if(customer.Account_num == accNum) 
			                  			{
			                  				customer.display_details();
			                  				System.out.println("Access Granted!");
			                  				found = true;
			                  				break;
			                  			} 
			                  			
			                  			else if(i+1 == Account.list.size()) 
			                  			{
			                  				System.out.println("Invalid Account Number!");
			                	  		}
			                  			i++;
			                  		}
		                  		   } while(!found);
		                  		break;
		                  		
		                  		
		                  	case 2: 
		                  		for(Account element: Account.list) //checking if element existis in list
		                  		 {		                	  		
		                  			element.display_details();
		                         }
		                  		
		                  		break;
	                	  		                	  	
		                  	case 0:
	      	  			
		                  		quit = true;
		                  		break;
	    
		                  	default:
	          
		                  		System.out.println("Please Try Again.");
		                  		break;
		                  }
	                	  	
	            		} while (!quit); 
				    		System.out.println("Thank you. Please come again!");
				    		System.out.println("\n");
			    
				    }
			    
				}
	        
			while(!quit);
			in.close();

		}
	            
} 	  

