package CriticalThinking.Module1;

import java.util.Scanner;

public class BankAccount {
	
	
	//initialize required variables
	String firstName;
	String lastName;
	static int accountID;
	double balance;
	String accountType;

	//default constructor for BankAccount object
	public BankAccount() {
		this.balance = 0.0;
	}
	
	public BankAccount(String firstName, String lastName, int accountID) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountID = accountID;
		this.accountType = "Regular";
		this.balance = 0.0;
		
		
	}

	public void deposit(Scanner scnr) {
		System.out.println("How much would you like to deposit:");
		double deposit = scnr.nextDouble();
		this.balance += deposit;
		System.out.printf("\nDeposit of $%.2f succeessful\n", deposit);
		System.out.println("");
		this.accountSummary();
	}

	public void withdrawal(Scanner scnr) {
		System.out.println("How much would you like to withdraw:");
		double withdraw = scnr.nextDouble();
		if ((this.balance - withdraw) > 0) {
		this.balance -= withdraw;
		System.out.printf("\nWithdrawal of $%.2f succeessful\n", withdraw);
		
		this.accountSummary();
		}
		else {
			System.out.println("Unable to withdraw, insufficient funds");
			System.out.println("");
			this.accountSummary();
		}
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public void setAccountID(int acctId) {
		this.accountID = acctId;
	}

	public double getBalance() {
		return balance;
	}

	public void accountSummary() {
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.printf("Account ID: %09d\n", accountID);
		System.out.printf("Account type: %s", accountType);
		System.out.printf("Balance: $%.2f\n", balance);
		System.out.println("");
	}

}
