package CriticalThinking.Module2;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class CheckingAccount extends BankAccount {

	// initialize variable for interest rate
	double interestRate;
	Double preProcessBal;
	double withdrawReq;

	// constructor for CheckingAccount object
	public CheckingAccount(String firstName, String lastName, int accountID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.interestRate = 1.75;
		this.accountID = accountID;
		this.accountType = "Checking";
		this.balance = 0;
	}

	// Override withdrawal method to allow overdraft with fees

	@Override
	public void withdrawal(Scanner scnr) {
		System.out.println("How much would you like to withdraw:");
		double withdraw = scnr.nextDouble();
		if ((this.balance - withdraw) > 0) {
			this.balance -= withdraw;
			System.out.printf("\nWithdrawal of $%.2f succeessful\n", withdraw);

			this.accountSummary();
		} else {
			preProcessBal = Double.valueOf(balance);
			withdrawReq = Double.valueOf(withdraw);
			this.balance -= withdraw;
			System.out.printf("\nWithdrawal of $%.2f succeessful\n", withdraw);

			// call processWithdrawal() funciton to process overdraft fees
			processWithdrawal();
		}
	}

	// Adds $30 fee to withdrawal if balance is taken below zero
	private void processWithdrawal() {
		balance -= 30.0;
		System.out.println("Balance too low, a $30 fee has been assessed on the account balance.");
		System.out.printf("Current balance: $%.2f\n\n", balance);
		// feeLogUpdate();
	}

	@Override
	public void accountSummary() {
		super.accountSummary();
		System.out.printf("\nInterest Rate: %.2f\n\n", interestRate);

	}
}