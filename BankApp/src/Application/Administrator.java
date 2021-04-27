package Application;

import java.util.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

class AccountAdm
{
	 String Username, ID;
	 
	 static ArrayList<AccountAdm> list = new ArrayList<AccountAdm>();
	 
	 AccountAdm(){}
	 
	  AccountAdm(String username, String id)
	 	{
		 	Username = username;
		 	ID = id;
        }
	 
	 void insert(String username, String id) // input user name, account number and type 
	    { 
	    	Username = username;
		 	ID = id;
	    }
	 
	 public  void display_details()
	    {
	        System.out.println("Username :" +Username);
	        System.out.println("ID : "+ID+ "\n\n");
	    }
	 
	 public  void updateList(ArrayList<AccountAdm> list, AccountAdm Admin) //adding users to list
	 	{
			list.add(Admin);		
		}
	 
	 void fetchAccounts() 
	 {	
		 
		 try 
		 {
			 Class.forName("org.postgresql.Driver");
			 AdminTable.c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5434/postgres",
	            "postgres", "1Alfonsog@");
			 AdminTable.c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         AdminTable.stmt = AdminTable.c.createStatement();
			 ResultSet rs = AdminTable.stmt.executeQuery( "SELECT * FROM ADMIN;" );
			 while(rs.next()) 
			 {
				 String username = rs.getString("username");
				 String id = rs.getString("id");
				 
				 AccountAdm  Administrator = new AccountAdm(username, id);

				 updateList(list, Administrator);

			 }
		 } 
		 
		 	catch (Exception e) 
	       	{
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		    }
	 }

	 public  void printList()
	 {
		 for(AccountAdm e: list) 
		 {
			 e.display_details();
		 }
	 }
}






public class Administrator
{
	public void adminMenu(AccountAdm admins)
	{
		
            
		AccountAdm Administrator = new AccountAdm();

        
        int userChoice;
		boolean quit = false; 


		Scanner input = new Scanner(System.in);   
		
		do {
		
			boolean access = false;
		
		do {
		
			System.out.println("Enter Username : ");
			String username = input.next();
			System.out.println("Enter ID : ");
			String id = input.next();
	  	
    	  	//checking array list
			int i = 0;
    	  	for(AccountAdm admin: admins.list) 
    	  	{
    	  		if (username.equals(admin.Username) && id.equals(admin.ID)) 
    	  		{
    	  			access = true;
    	  			Administrator = admin;
    	  			break;
    	  		} 
    	  		
    	  		else if(i+1 == admins.list.size()) 
    	  		{
    	  			if (username.equals(admin.Username)) 
				    {
				        System.out.println("Invalid Username!");
				    } 
				    
				    else if (id.equals(admin.ID)) 
				    {
				        System.out.println("Invalid ID!");
				    } 
				    
				    else 
				    {
				        System.out.println("Invalid Username & ID!");
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
                  System.out.println("3) Access Employees Accounts:  ");
                  System.out.println("0) Quit");
		    	
                  userChoice = input.nextInt();
                  
                  switch (userChoice) 
                  {

                  	case 1: 
                  		boolean found = false;
                  		do {
                  			int i = 0;		
                  			
	                  		System.out.print("Enter customer account number: ");
	                  		int accNum = input.nextInt();
	                  		for(Account customer: Account.list)
	                  		{
	                  			if(customer.Account_num == accNum) 
	                  			{
	                  				customer.display_details();
	                  				found = true;
	                  				break;
	                  			} 
	                  			
	                  			else if(i+1 == Account.list.size()) 
	                  			{
	                  				System.out.println("Invalid Account Number!");
	                	  		}
	                  			i++;
	                  		}
                  		   } 
                  		
                  		while(!found);
                  		
                  	    break;
                  		
                  		
                  	case 2: 
                  		
                  		for(Account element: Account.list) //checking if element existis in list
                  		 {		                	  		
                  			element.display_details();
                         }
                  		
                  		break;
            	  		                	  	
                  	case 3:
                  		
					for(AccountEmp user: AccountEmp.list) //checking if element existis in list
                 		 {		                	  		
                 			user.display_details();
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
				input.close();

		}
	
}


