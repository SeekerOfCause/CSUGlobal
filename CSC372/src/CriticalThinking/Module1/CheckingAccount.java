package CriticalThinking.Module1;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class CheckingAccount extends BankAccount {

	// initialize variable for interest rate
	double interestRate;
	Double preProcessBal;
	double withdrawReq;
	// String feeEntry;

	// initialize log for overdraft fees using time stamps

	// private static Map<String, String> feeLog = new HashMap<String, String>();

	// character stream for fee log
	// StringWriter feeCharStream = new StringWriter();
	// Augments char stream with print()
	// PrintWriter feeString = new PrintWriter(feeCharStream);

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

//		if (feeLog.size() >= 1) {
//			System.out.printf("Number of fees assessed: %d\n", feeLog.size());
//			System.out.println("Fee history: \n");
//
//			int tracker = 0;
//			Object[] keys = feeLog.keySet().toArray();
//			for (int i = 0; i < keys.length; i++) {
//				if (feeLog.get(keys[i]).equals(Integer.toString(accountID))) {
//					tracker++;
//					
//					;
//					System.out.println(keys[i]);
//
//				}
//				System.out.println("------------------");
//			}
//			System.out.printf("\nTotal fees: $%d\n Total number of overdrafts: %d\n", tracker * 30, tracker);
//		}

//	}

//	private void feeLogUpdate() {
//		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		String timeStamp = dateForm.format(now);
//		feeString.print("Date: " + timeStamp + "\nRequested Ammount: $" + withdrawReq + "\nBalance at time of request: $"
//				+ preProcessBal + "\nFinal balance: " + balance);
//		feeEntry = feeCharStream.toString();
//		feeLog.put(feeEntry, Integer.toString(accountID));
//	}

//	public static void printFeeLog() {
//		Object[] keys = feeLog.keySet().toArray();
//		for (int i = 0; i < keys.length; i++) {
//			if (feeLog.get(keys[i]).equals(Integer.toString(accountID))) {
//				System.out.println("");
//				System.out.println(keys[i]);
//				System.out.println("------------------");
//			}
//		}
	}
}
