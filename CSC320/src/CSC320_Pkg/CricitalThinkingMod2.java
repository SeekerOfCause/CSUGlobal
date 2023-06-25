package CSC320_Pkg;

import java.util.Scanner;

public class CricitalThinkingMod2 {
	
	/*
	 * Import java scanner and java math

Set variables:
	String for Car Manufacturer
	String for Car Model
	String for Car year
	Long for Odometer Start
	Long for Odometer End
	Double for Gallons Used
	Double for Miles PerGallon

Print statement for input and setting in put:

	“Enter car manufacturer:”
	Set input to string for Car Manufacturer

	“Enter Car Model:”
	Set input to string for Car Model

	“Enter Car Year:”
	Set input to string for Car Model

	“Enter starting odometer reading:”
	Set input to long for Odometer Start

	“Enter ending odometer reading:”
	Set input to long for Odometer End

	“Enter gallons of gas used:”
	Set input to double for Gallons Used

Calculate Miles per gallon:

	Calculate miles driven – 
	        Miles Driven = (Odometer End) – (Odometer Start)

	Calculate miles per gallon –
	        Miles per Gallon = (Miles Driven) / (Gallons Used)





Print info to user:

	Print Car Information-
	        Print new line “Car: (Car Year) (Car Manufacturer) (Car Model)”	

	Print MPG Information – 
	        Print new line “Miles driven: (Miles Driven)”
	        Print new line “Gallons of gas used: (Gallons Used)”
	        Print new line “Miles per Gallon: (Miles per Gallon)”

	 * 
	 * 
	 */

	
	
	public static void main(String[] args) {
		
		//Initialize scanner and variables
		Scanner scan = new Scanner(System.in);
		String carMan;
		String carModel;
		String carYear;
		double odStart;
		double odEnd;
		double milesDriven;
		double galUsed;
		double mpg;
		
		//Request car info from user
		System.out.println("Enter your car's year, manufacturer, and model:\nExample: 2015 Chevrolet Volt");
		carYear = scan.next();
		carMan = scan.next();
		carModel = scan.next();
		
		//Request odometer readings from user
		System.out.println("Enter the starting and ending odometer readings:\nExample: 1000.0 1500.0");
		odStart = scan.nextDouble();
		odEnd = scan.nextDouble();
		
		//Request gallons of gas used from user
		System.out.println("Enter the gallons used during drive:\nExample: 25.2");
		galUsed = scan.nextDouble();
		scan.close();
		
		//Calculate miles driven from odometer readings
		milesDriven = odEnd - odStart;
		
		//Calculate miles driven from miles driven and gallons of gas used
		mpg = milesDriven / galUsed;
		
		//Print car information
		System.out.printf("Car: %s %s %s\n", carYear, carMan, carModel);
		
		//Print miles driven, gallons used, and mpg
		System.out.printf("Miles Driven: %.2f\nGallons Used: %.2f\nMPG: %.2f", milesDriven, galUsed, mpg);
	}

}

