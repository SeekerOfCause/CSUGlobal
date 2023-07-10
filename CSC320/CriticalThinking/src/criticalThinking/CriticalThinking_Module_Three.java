package criticalThinking;

import java.util.Scanner;

public class CriticalThinking_Module_Three {

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

		double weekIncome;
		double taxRate = 0;
		double weekTaxWithhold;
		Scanner scnr = new Scanner(System.in);

		System.out.println("Enter weekly income");
		weekIncome = scnr.nextDouble();

		if (weekIncome < 500.00) {
			taxRate = 0.10;
		} else if (weekIncome < 1500.00) {
			taxRate = 0.15;
		} else if (weekIncome < 2500.00) {
			taxRate = 0.20;
		} else if (weekIncome >= 2500.00) {
			taxRate = 0.30;
		}

		weekTaxWithhold = weekIncome * taxRate;

		System.out.printf("Your weekly tax withholding is %.2f", weekTaxWithhold);

	}

}
