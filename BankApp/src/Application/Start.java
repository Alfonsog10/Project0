/* **********************************************
 *												*
 * 												*
 * Developer: Alfonso Guaderrama   				*
 * Version 1.0					   				*
 * Revature 					   				*
 * 								   				*
 *                                				*
 * **********************************************/


package Application;

import java.util.Scanner;

public class Start 
{
	 
	 boolean running = true;
	 int selecOption;

	public  void menu() 
	{
		
		System.out.println("Selection: \n 1) ADMIN \n 2) EMPLOYEE \n 3) CUSTOMER\n 4) QUIT");
		System.out.print("Choose option: ");
	}



	public static void main(String[] args) 
	{
		Start bank = new Start();
		
		Account customers =  new Account(); 
		customers.fetchAccounts();
		
		AccountEmp employees = new AccountEmp();
        employees.fetchAccounts();
        
        AccountAdm admin = new AccountAdm();
        admin.fetchAccounts();
        //admin.printList();
		
		Scanner input = new Scanner (System.in); 
		int sel;
		
		Customer custom = new Customer ();
		Administrator administ = new Administrator ();
		Employee employ = new Employee ();
		

			//do {
				bank.menu();						 		//display menu
				sel = input.nextInt();
				
				switch(sel)
				{
					case 1: //ADMIN
						sel = 1;
						System.out.println("You have selected the Administrator Menu \n");
						administ.adminMenu(admin);	//launches everything inside the Administrator Menu class
						break;
						
					case 2: //EMPLOYEE
						sel = 2;
						System.out.println("You have selected the Employee Menu \n");
						employ.employeeMenu(employees);  //launches everything inside the Employee Menu class
						break;
						
					case 3:	//CUSTOMER
						sel = 3;
						System.out.println("You have selected the Customer Menu \n");
						custom.customerMenu(customers);	 //launches everything inside the Customer Menu class
						
						break;
						
					case 4:	//TERMINATE PROGRAM
						sel = 4;
						System.out.println("System terminated \n");
						
						break;
					
					default: //INVALID INPUT
						System.out.println("Invalid Input");    
				}
				input.close();
			//} while(sel != 4);
			
		}


}
