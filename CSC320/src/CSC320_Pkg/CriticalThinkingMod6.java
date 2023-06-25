package CSC320_Pkg;

import java.util.*;

public class CriticalThinkingMod6 {

/*  Create new scan object
 *  
 *  Call method to get input and print to screen
 *  Print "Success" or "Failure" returned from method
 *  
 *  
 *  
 *  Create method to get user input:
 *  
 *  public static String method (scanner object)
 *  
 *  Create array to store user inputs as strings
 *  Create integer i to track while loop iteration
 *  
 *  Create string to hold user input line
 *  
 *  While loop to iterate over the length of input line split with split(" ")
 *  
 *  Try catch to enter split user data in array at array[i]
 *  
 *     Try stores user input split into array and increments i
 *     
 *     Catch advises user to only enter three inputs and returns "Failure" to calling method
 *  
 *  Print statement to print user inputs
 *  return "Success" to calling method
 *  
 */

	public static void main(String[] args) {

		//Create new scanner object
		Scanner scnr = new Scanner(System.in);

		//call method and print what it returns
		System.out.println(getInput(scnr));

	}

	public static String getInput(Scanner scanObj) {

		//create new string array to hold user input and integer for while loop
		String[] userInput = new String[3];
		int i = 0;

		//request input from user
		System.out.println("Please enter three separate inputs. i.e. 'word' '1234' '#@#$%'");
		String input = scanObj.nextLine();
		
		//while loop to iterate over line entered by user, place at least 3 (at most the amount entered by user) into array
		while (i < 3 || i < input.split(" ").length) {

			//try catch to enter user input strings into array. will catch if user entered less than or  more than 3 input strings
			try {
				userInput[i] = input.split(" ")[i];
				i++;
			}
			catch (Exception e) {
				System.out.println("Invalid, please enter three valid inputs");
				
				//returns "Failure" to calling method
				return "Failure";
			}
		}
		
		//Prints user input strings and returns "Success" to calling method for printing to console
		System.out.printf("Your input was %s, %s, and %s.\n", userInput[0], userInput[1], userInput[2]);
		return "Success";
	}
}

