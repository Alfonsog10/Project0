package Application;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;


class Account
{
	 String Name,Account_type;
	 int Account_num;
	 double Account_Balance;
	 
	 static ArrayList<Account> list = new ArrayList<Account>(); //creating list object of Arraylist Account type
	 
	 Account(){} //constructor
	 
	 Account(String name,int acc_num,double Balance,String acc_type) 
	 	{
            Name = name;
            Account_num = acc_num;
            Account_Balance = Balance;
            Account_type = acc_type;
        }
	 
	 Account(String name,int acc_num,String acc_type)
	 	{
         Name = name;
         Account_num = acc_num;
         Account_Balance = 0;
         Account_type = acc_type;
	 	}
	 
	  void fetchAccounts() //this method gets the CUSTOMER table from SQL and saves it in an array list
	  {	
		 
		 try 
		 {
			 Class.forName("org.postgresql.Driver");
	         CustTable.c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5434/postgres",
	            "postgres", "1Alfonsog@");
	         CustTable.c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         CustTable.stmt = CustTable.c.createStatement();
			 ResultSet rs = CustTable.stmt.executeQuery( "SELECT * FROM CUSTOMER;" );
			 while(rs.next()) 
			 {
				 String user_name = rs.getString("name");
				 String type = rs.getString("accounttype");
				 int accNum = rs.getInt("ACCOUNTNUM");
				 double balance = rs.getDouble("balance");
				 
				Account customer = new Account(user_name, accNum, balance, type);
//				insert(user_name, accNum, balance, type);
//				display_details();
//				 customer.display_details();
				 updateList(list, customer);
//				 for(Account e: list) {
//					 e.display_details();
//					 
//				 }
//				 System.out.print("*************\n");
			 }
		 }	
		 
		 	catch (Exception e) 
	     	{
		        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		        System.exit(0);
	     	}
	 }

	  void insert(String name,int acc_num,String acc_type) // input user name, account number and type 
	    { 
	        Name = name;
	        Account_type = acc_type; //user specifies what type of account is being generated
	        Account_num = acc_num; // generate random number 
	        Account_Balance = 0; //initiate account balance to 0 
	    }
	 
	  void insert(String name,int acc_num,double balance,String acc_type) //polyformism 
	    { 
	        Name = name;
	        Account_type = acc_type; //user specifies what type of account is being generated
	        Account_num = acc_num; // generate random number 
	        Account_Balance = balance; //initiate account balance to 0 
	    }
	 
	  void deposite(int acc_num, double money) 
	    {                 
	        Account_Balance = money;    
	    }

	   void withdraw(double withdraw)
	    {
	        Account_Balance = Account_Balance-withdraw;
	    }
	 
	 public  void display_details() //method invoked to display the account information
	    {
	        System.out.println("Depositor Name :" +Name);
	        System.out.println("Account Number : "+Account_num);
	        System.out.println("Account Balance : "+Account_Balance);
	        System.out.println("Account Type : "+Account_type + "\n\n");
	    }
	 
	 public  void updateList(ArrayList<Account> list, Account user) //adding users to list
	 	{
			list.add(user);		
		}
	 
	 public  void printList()
	 {
		 for(Account e: list) 
		 {
			 e.display_details();
		 }
	 }

}
	

public class Customer 
{
	
	public void customerMenu(Account customers) //method invoked in Start class to execute the menu 
	{
		Account customer = new Account();

		
		String user_name = null, Acc_type;
        Acc_type = null;
        double balance = 0;
        int Acc_number = 0, pos = 0;
        double withdraw = 0;

        int randomNumber = 0; //generate random number as account number
        randomNumber = (int)((Math.random() * 9000)+1000); 


            Scanner in = new Scanner(System.in); //reference variable to invoke a new scanner class
            Scanner strng = new Scanner(System.in);
            
            int userChoice;
            boolean quit = false;
            
            do
            {
                  System.out.println("1. Create Account");
                  System.out.println("2. Enter Account");
                  System.out.println("0. to quit: \n");
                  System.out.print("Enter Your Choice : ");
                  
                  userChoice = in.nextInt();
                  
                  switch (userChoice) 
                  {
                 
                  case 1: //will be used to create the account, update array list, & update data table
                	  
                        System.out.print("Enter your Name : ");
                        user_name = strng.nextLine(); 
                       
                        System.out.print("Enter Account Type : ");
                        Acc_type = in.next();
                        
                        customer.insert(user_name, randomNumber, Acc_type); // creating user parameters that will be added to list
                        
                        System.out.println("\n\tYour Account Details\n\tDont Forget Your Account Number\n");
                        System.out.println("***********************sd***");   
                        customers.updateList(customers.list, customer); //updating array list with new customer
                        CustTable.newCustomer(randomNumber, user_name, Acc_type, balance); //updating table with new customer
                        customer.display_details();

                        break;
                  
                  case 2:  //Returning customers can enter their information in order to access their accounts
                	  	do 
                	  	{
	                	  	System.out.print("Enter Your Account Number : ");
	                	  	Acc_number=in.nextInt();
	                	  	boolean isNumber = false;
	                	  	
	                	  	 for(Account element: customers.list) 
	                	  	 {
	                	  		if (Acc_number == element.Account_num) //checking to see of the account number is in the array list
	                	  		{
	                	  			isNumber = true;
	                	  			customer = element;
	                	  			break;
	                	  		}
	                	  		pos++; // this is to know which element we are on and update list
	                         }

	                	  	if (isNumber)
	                	  	{
	                	  		do 
	                	  		{
	                	  		
		                	  		System.out.println("1. Deposit money");
		                            System.out.println("2. Withdraw money");
		                            System.out.println("3. Check balance");
		                            System.out.println("4. Display Account Details");
		                            System.out.println("0. to quit: \n");
		                            System.out.print("Enter Your Choice : ");
		                            
		                            userChoice = in.nextInt();
		                            
		                            switch (userChoice) 
		                            {
		                            
			                            case 1: // deposit
			                    	  	
			                    	  	
			                            	System.out.print("Enter Amount Of Money : ");
			                            	balance = in.nextInt();
			                            	customer.Account_Balance += balance;
			                            	customers.list.get(pos).Account_Balance = customer.Account_Balance; //updating the balance in the array list
			                            	CustTable.updateBalance(Acc_number, balance); //updating the balance in the table
			                            	System.out.println("\t Successfully Deposited.");
			                            	
			                            	break;
			
			                	  		case 2: // withdraw money                      
			                	  	
			                	  			                        
			                	  				if(customer.Account_Balance==0)
			                	  				{
			                	  					System.out.print("There Is No Money.");
			                	  				}
			                	  			
				                	  			else
				                	  			{
					                            	 System.out.print("Enter Amout Of Money : ");   
					                            	 withdraw = in.nextInt();  
					                	  			
					                            
					                	  			if(withdraw > customer.Account_Balance)
					                	  			{
					                	  				System.out.print("Enter Valid Amout of Money : ");
					                	  				withdraw = in.nextInt();
					                	  			}
					                             
					                	  			else 
					                             	{
					                            	 	customer.withdraw(withdraw);
					                            	 	CustTable.updateBalance(Acc_number, customer.Account_Balance);
					                            	 	customers.list.get(pos).Account_Balance = customer.Account_Balance;
					                            	 	System.out.println("Your Current Balance is : "+customer.Account_Balance); 
					                            	 	
					                             	}
				                	  			}
			                          
			                             
			                	  				break;
			                	  		
			                	  		case 3: // check balance 
			
			                      	 			System.out.println("Your Current Balance is : "+customer.Account_Balance);
			                             
			                      	 			break;
			                	  		case 4: // display all info 
			
			                	  			                             
			                	  				customer.display_details();  
			                	  				break;
			                             
			                	  		case 0:
			                	  			
				                	  			quit = true;
				                	  			break;
			                  
			                	  		default:
			                        
				                	  			System.out.println("Please Try Again.");
				                	  			break;
		                            	} 
	
	                	  		} 
	                	  		
	                	  		  while(!quit);	
	                	  	} 
	                	  	
	                	  		  else 
	                	  		  {
	                	  			  System.out.println("Wrong Number... Try Again!");
	                	  		  }
                	  	}
                	  	
                	  			  while(!(Acc_number == customer.Account_num)); //if false, return again to ask for user input
                	  			  break;
                	  	
                  case 0:
      	  			
                  quit = true;
      	  		  break;
    
                  default:
          
      	  			System.out.println("Please Try Again.");
      	  			break;
      	  			
                  }
                	  	
            	} 
            	
            	  while (!quit);
            
      	  		  System.out.println("Thank you. Please come again!");
                  
                  System.out.println("\n");
                  in.close();
                  strng.close();
            }


            
} //  end main function 
	
	
		

