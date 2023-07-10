package criticalThinking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CriticalThinking_Module_Four {

	public static void main(String[] args) {
		
		/*
		 * Write a program that utilizes a while-loop to read a set of five floating-point values from user input. Include 		 		 * code to prevent an endless loop. Ask the user to enter the values, then print the following data:
		 * 
		 * Create variables for:
		 * 	List userInput
		 * 	Int variable
		 * 	Double total
		 * 	Double average
		 * 	Double maximum
		 * 	Double minimum
		 * 	Double interestTot
		 * 
		 * While loop that asks user for input to add floating-point values to list:
		 * 
		 * 	while user input is not 'done':
		 * 		add user input to userInput
		 * 		ask user for another input
		 * 		add 1 to variable
		 * 	
		 * 
		 * While loop that takes all user input and calculates required outputs:
		 * 
		 * 	set variable to zero
		 * 
		 * 	while variable is less than userInput length:
		 * 		add float at userInput[variable] to total
		 * 		if float at userInput[variable] is larger than maximum:
		 * 			maximum equals userInput[variable]
		 * 		if float at userInput[variable is less than minimum:
		 * 			minimum equals userInput[variable]
		 * 
		 * Calculate average and interest total at 20%:
		 * 
		 * 	average equals total divided by length of user input
		 * 
		 * 	interestTot equals total plus total times .20
		 * 
		 * Print required output
		 */

		// create scanner and list
		Scanner scnr = new Scanner(System.in);
		List<Double> userInputList = new ArrayList<Double>();

		// Create variables
		int i;
		Double input;
		String check = null;
		Double total = 0.0;
		Double average = 0.0;
		Double maximum = 0.0;
		Double minimum;
		Double interestTot = 0.0;

		// Initial request for input
		System.out.println("Enter the ammounts:");

		// Check if input is of Double type
		scnr.hasNextDouble();

		// While loop, stops if 'check' variable is equal to "done"
		while (check != "done") {

			// check if user has entered input (used to manage if statement during while
			// loop)
			if (scnr.hasNext()) {

				// if user entered Double then add input to end of user input list and ask for
				// next input
				if (scnr.hasNextDouble()) {

					input = scnr.nextDouble();
					userInputList.add(input);

					System.out.println("Enter next ammount, or 'done' if no more entries.");
					scnr.nextLine();
				}

				// if user entered "done" set check to "done" and add another line
				else if (scnr.hasNext("done")) {
					check = "done";
					System.out.print("");
				}

				// if is not a double or "done", print invalid statement and move to next input
				// line
				else if (scnr.hasNext()) {
					System.out.println("Invalid input, please try again");
					scnr.nextLine();

					continue;
				}
			}

		}

		// set variable for while loop
		i = 0;

		// set initial minimum value to handle while loop minimum calculation
		minimum = userInputList.get(0);

		// while loop stops if i is greater than or equal to user input list length
		while (i < userInputList.size()) {
			total += userInputList.get(i);

			// if user input at 'i' is greater than current maximum, replace
			if (userInputList.get(i) > maximum) {
				maximum = userInputList.get(i);
			}

			// if user input at 'i' is less than current minimum, replace
			if (userInputList.get(i) < minimum) {
				minimum = userInputList.get(i);
			}

			// increment i
			i++;
		}

		// calculate total interest at %20
		interestTot = total + (total * 0.20);

		// calculate average by dividing total by user input list length
		average = total / userInputList.size();

		// print required outputs
		System.out.printf("Your total is %.2f\n", total);
		System.out.printf("The average is %.2f\n", average);
		System.out.printf("The minimum is %.2f\n", minimum);
		System.out.printf("The maximum is %.2f\n", maximum);
		System.out.printf("The Total with 20 percent interest is %.2f\n", interestTot);

	}

}
