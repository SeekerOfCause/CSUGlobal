package CSC320_Pkg;

import java.util.Scanner;

public class CriticalThinkingMod3 {

	public static void main(String[] args) {

		/*
		 * Declare variable for weekly income, weekly tax withholding and tax rate:
		 * 	Double weekIncome; 
		 * 	Double taxRate; 
		 * 	Double weekTaxWithhold;
		 * 
		 * If else statement to set the tax level: 
		 * 	if weekIncome is less than $500: taxRate equals 0.10
		 * 	else if weekIncome is less than $1500: taxRate equals 0.15 
		 * 	else if weekIncome is less than $2500: taxRate equals 0.20 
		 * 	else if weekIncome is greater than or equal to $2500: taxRate equals 0.30
		 * 
		 * Calculate weekly tax withholding:
		 * 	weekTaxWithhold equals weekIncome * taxRate
		 * 
		 * Display weekTaxWithold: 
		 * 	Print weekTaxWithold
		 */

		//***Added comments to clarify code***
		
		
		//Initialized required variables
		double weekIncome;
		double taxRate = 0;
		double weekTaxWithhold;
		boolean input = false;
		
		//Initialized scanner as scnr
		Scanner scnr = new Scanner(System.in);

		//Print out request to user for weekly income
		System.out.println("Enter weekly income");
		
		
		//***Added do/while loop to catch invalid input from user***
		do {
		if (scnr.hasNextDouble()) {
		
		input = true;
		}
		else {
			System.out.println("Invalid Input");
			scnr.next();
		}
		}while (!input);
		
		//Set weekIncome to input from user
		weekIncome = scnr.nextDouble();
		
		//If statement to use user input to set proper tax rate
		if (weekIncome < 500.00) {
			taxRate = 0.10;
		} else if (weekIncome < 1500.00) {
			taxRate = 0.15;
		} else if (weekIncome < 2500.00) {
			taxRate = 0.20;
		} else if (weekIncome >= 2500.00) {
			taxRate = 0.30;
		}
		
		// ***Closed scnr to avoid memory leaks***
		scnr.close();
		
		
		//Set weekTaxWithold to weekly income times tax rate
		weekTaxWithhold = weekIncome * taxRate;

		
		//Print out weekly tax rate to user
		System.out.printf("Your weekly tax withholding is %.2f\n", weekTaxWithhold);

	}

}
