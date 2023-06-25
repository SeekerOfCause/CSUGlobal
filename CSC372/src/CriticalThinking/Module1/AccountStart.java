package CriticalThinking.Module1;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountStart {

	
	//Initialize variables for program
	static Scanner scnr = new Scanner(System.in);

	static ArrayList<BankAccount> accountLog = new ArrayList<BankAccount>();
	String[] choices = { "deposit", "withdraw", "create account", "create checking account", "check balance", "exit" };

	static String firstName = null;
	static String lastName = null;
	static int accountID;
	static double deposit = 0.0;
	static double withdrawal = 0.0;
	static BankAccount account = null;
	static boolean userExit = false;
	static boolean goodInput = false;

	public static void main(String[] args) {

		//welcome user to program
		System.out.println("Welcome to [Banking System]\n");

		//while loop to continue returning to main menu until user wants to exit
		while (!userExit) {

			//give options to user
			System.out.println(
					"-------------------\nWould you like to:\n\n1: Create account\n2: Deposit funds\n3: Withdraw funds\n4: exit\n\nPlease enter a number 1-4.");
			//check to make sure user has entered a number
			if (scnr.hasNextInt()) {

				//get user number input
				int userChoice = scnr.nextInt();

				//ensures user input is between 1 and 4
				if (userChoice > 0 && userChoice < 6) {

					//initiates create account function
					if (userChoice == 1) {
						createAccount();
					}
					//initiates deposit function
					if (userChoice == 2) {
						
						try {
							BankAccount account = findAccount();
							account.deposit(scnr);
						} catch (Exception e) {
							System.out.println("Unknown error, please try again");
						}
						
					}

					//initiates withdraw function
					if (userChoice == 3) {
						
						try {
							BankAccount account = findAccount();
							account.withdrawal(scnr);
						} catch (Exception e) {
							System.out.println("Unknown error, please try again");
						}
						
					}
					
					//exits program
					if (userChoice == 4) {
						System.out.println("Thank you for banking with us, have a nice day :)");
						userExit = true;
					}
					//if (userChoice == 5) {
					//	CheckingAccount.printFeeLog();
					//}
				}
			} 
			//advises user they have entered an invalid input, redirects them to main menu to try again.
			else {
				System.out.println("Invalid, please enter a number 1-4");
				scnr.nextLine();
			}
		}
		
		//close scanner to avoid leaks
		scnr.close();
	}
	
	//create account function
	public static void createAccount() {
		
		//clears scanner
		scnr.nextLine();
		
		//initialize required variables
		boolean acctCreated = false;
		boolean isChecking = false;

		//asks user if they want to make their account a checking account
		System.out.println("Will this be a checking account? (y/n)");
		
		//sets isChecking to true if user wants a checking account
		if (scnr.next().toLowerCase().equals("y")) {
			isChecking = true;
			//clears scanner
			scnr.nextLine();
		} else {
			//leaves isChecking false, clears scanner
			scnr.nextLine();
		}

		//loop to ask user for required info until all info is received and account is created
		do {
			
			System.out.println("Enter your first and last name:");
			//creates array from user input
			String[] name = scnr.nextLine().split(" ");

			try {

				//try catch to verify there are two entries in name to check if user entered both first and last name
				firstName = name[0].toString();
				lastName = name[1].toString();

				//verifying with user if these inputs are correct
				System.out.printf("First Name: %s\nLast Name: %s\nIs this correct? (y/n)\n", firstName,
						lastName);

				String choice = scnr.next();

				//if user inputs that the names are correct they continue, if not they repeat name entry by looping again
				if (choice.equals("y")) {
					
					//clear scanner
					scnr.nextLine();
					
					//set account ID to current amount of accounts in log plus one
					accountID = accountLog.size() + 1;

					//if isChecking, then create a checking account object, if not create bank account object
					if (isChecking) {
						CheckingAccount account = new CheckingAccount(firstName, lastName, accountID);
						accountLog.add(account);
						System.out.print("Account created successfully\nSee summary below:\n\n");
						account.accountSummary();
						acctCreated = true;
						break;
					} else {
						BankAccount account = new BankAccount(firstName, lastName, accountID);
						accountLog.add(account);
						System.out.print("Account created successfully\nSee summary below:\n\n");
						account.accountSummary();
						acctCreated = true;
						break;
					}

				} 
				//loop back through if user indicated names are incorrect
				else if (choice.equals("n")) {
					System.out.println("Please try again");
					scnr.nextLine();
				} 
				//catches unknown inputs from user and loops back through to name entry
				else {
					System.out.println("Invalid Input");
					scnr.nextLine();
				}

			} catch (Exception e) {
				System.out.println("Invalid Input");
			}
		} 
		//Will loop through until account is properly created
		while (!acctCreated);
	}

	//Function to find account in account log
	public static BankAccount findAccount() {

		//boolean to check if account was properly found
		boolean found = false;
		
		//loops until account is found
		while (!found) {

			//Asks user for account id or to enter "Name" to use thier first and last name.
			System.out.println("Please enter your accountID, or enter 'Name' to use your first and last name:");

			if (scnr.next().toLowerCase().equals("name")) {

				//clear scanner
				scnr.nextLine();
				System.out.println("Enter your first and last name:");
				
				//splits name into array to check that both first and last are entered
				String[] name = scnr.nextLine().split(" ");

				try {
					String first = name[0];
					String last = name[1];
					for (int i = 0; i < accountLog.size(); i++) {
						//if matching account is found, create 'account' object set to account and print summary
						if (accountLog.get(i).firstName.equals(first) && accountLog.get(i).lastName.equals(last)) {
							account = accountLog.get(i);
							System.out.println("Account located:");
							account.accountSummary();
						}
					}
					
					
					System.out.println("");
					System.out.println("_______________");
					System.out.println("");
					found = true;
					return account;

				} catch (Exception e) {
					System.out.println("Please verify the spelling of the First and Last name and try again.");
				}
			}

			else {
				try {
					
					//use account id to find account
					int acctID = scnr.nextInt();
					for (int i = 0; i < accountLog.size(); i++) {
						int acctCheckID = accountLog.get(i).accountID;
						//if matching account is found set 'account' object to the account and print summary
						if (acctID == acctCheckID) {
							account = accountLog.get(i);
							System.out.println("Account located:");
							account.accountSummary();
						}
					}

					System.out.println("");
					System.out.println("_______________");
					System.out.println("");
					found = true;
					return account;
				} catch (Exception e) {
					System.out.println("Please enter a valid account number");
					scnr.next();
				}
			}
		}
		//return null if unable to locate properly
		return null;
	}
}
