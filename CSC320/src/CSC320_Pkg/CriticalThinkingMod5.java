package CSC320_Pkg;

import java.util.Scanner;

public class CriticalThinkingMod5 {

	public static void main(String[] args) {

		/*
		 * Import Scanner
		 * 
		 * Create Variables 
		 *  
		 * Prompt user for day of week
		 * 
		 * Get index of day in Days array
		 * 
		 * Return temp at day index
		 * 
		 * Print day and temp to user
		 */

		// Setup Variables
		String[] days = new String[] { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
		double[] week_temp = new double[] { 87.2, 74.5, 81.4, 76.5, 86.5, 83.9, 79.2 };
		int k = 0;
		int i = 0;
		boolean input = false;
		String userDay;
		double weeklyAvg;
		double total = 0;

		// Create scanner object
		Scanner scnr = new Scanner(System.in);

		// Ask for user input
		System.out.println("Enter a day of the week");
		userDay = scnr.next();
		scnr.close();

		// For loop to iterate over array
		for (i = 0; i <= days.length - 1; i++) {
			total += week_temp[i];
			// Check if user input matches a day, set input to true if match is found, set
			// day to index of match
			if (userDay.toLowerCase().compareTo(days[i].toLowerCase()) == 0) {

				input = true;
				k = i;
			}
		}

		weeklyAvg = 610;
		weeklyAvg = total / 7;

		//Check if user entered 'week'
		if (userDay.compareToIgnoreCase("week") == 0) {
			//Print weeks temps and week average
			System.out.printf("Sun: %.2f, Mon: %.2f, Tues: %.2f, Wed: %.2f, Thurs: %.2f, Fri: %.2f, Sat: %.2f\n",
					week_temp[0], week_temp[1], week_temp[2], week_temp[3], week_temp[4], week_temp[5], week_temp[6]);
			System.out.printf("Weekly average: %.2f", weeklyAvg);
		} 
		// Check if user input is valid with 'input' bool
		else if (input) {
			// Print out day and temp, print Invalid Input if no match from user entry
			System.out.printf("The temperature on %s was %.2f\n", days[k], week_temp[k]);
		} else {
			//Print invalid input error
			System.out.print("Invalid Input");
		}

	}

}
