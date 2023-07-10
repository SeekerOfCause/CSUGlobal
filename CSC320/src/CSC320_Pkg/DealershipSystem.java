package CSC320_Pkg;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DealershipSystem {

	// Set static array list to contain cars
	static ArrayList<AutoInventory> inventory = new ArrayList<AutoInventory>();
	static Scanner scnr = new Scanner(System.in);
	static boolean exit = false;

	public static void main(String[] args) {
		
		System.out.println("Welcome to the dealer inventory system.");

		while (!exit) {
			System.out.println("Add, Update, Remove, Inventory, Print or quit?");
			if (scnr.hasNext()) {
				String userChoice = scnr.next();
				scnr.nextLine(); // consume newline character in the buffer
				if (userChoice.toLowerCase().compareTo("add") == 0) {
					addNewVehicle();

				} else if (userChoice.toLowerCase().compareTo("inventory") == 0) {
					printInventory();
				} else if (userChoice.toLowerCase().compareTo("quit") == 0) {
					System.out.println("Goodbye");
					exit = true;
				} else if (userChoice.toLowerCase().compareTo("update") == 0) {
					updateVehicle();
				} 
				else if (userChoice.toLowerCase().compareTo("print") == 0) {
					printInventoryToFile();
				}
				
				else if (userChoice.toLowerCase().compareTo("remove") == 0) {
					removeVehicle();
				}
				
				else {
					System.out.println("Invalid Input");
				}
			}
		}

	}

	public static void addNewVehicle() {
		String make;
		String model;
		String year;
		String color;
		String cond;
		String vin;
		double mileage;
		double msrp;
		double dealerPur;
		double price;

		System.out.println("Please enter vehicle VIN, Make, Model, and Year:");

		String[] userInput = new String[4];
		int i = 0;

		String input = scnr.nextLine();

		while (i < 4 || i < input.split(" ").length) {

			try {
				userInput[i] = input.split(" ")[i];
				i++;
			} catch (Exception e) {
				System.out.println("Invalid input");
				return;

			}
		}

		make = userInput[1];
		model = userInput[2];
		year = userInput[3];
		vin = userInput[0];

		System.out.println("Please enter vehicle color:");
		color = scnr.nextLine();

		System.out.println("Please enter vehicle condition:");
		cond = scnr.nextLine();

		System.out.println("Please enter vehicle mileage:");
		mileage = scnr.nextDouble();

		System.out.println("Please enter vehicle MSRP:");
		msrp = scnr.nextDouble();

		System.out.println("Please enter vehicle dealer purchase price:");
		dealerPur = scnr.nextDouble();

		System.out.println("Please enter vehicle price:");
		price = scnr.nextDouble();

		AutoInventory car = new AutoInventory(make, model, year, color, cond, vin, mileage, msrp, dealerPur, price);
		inventory.add(car);

		System.out.println("Vehicle added:");
		System.out.printf(
				"Make: %s, Model: %s, Year: %s, Color: %s, Condition: %s, VIN: %s, Mileage: %.2f, MSRP: %.2f, Dealer Purchase Price: %.2f, Price: %.2f\n",
				car.getMake(), car.getModel(), car.getYear(), car.getColor(), car.getCond(), car.getVin(),
				car.getMileage(), car.getMsrp(), car.getDealerPur(), car.getPrice());

	}
	
	public static void removeVehicle() {
		
		System.out.println("Enter the VIN of the vehicle you want to update: ");
		String vin = scnr.nextLine();
		AutoInventory car = null;
		for (AutoInventory a : inventory) {
			if (a.getVin().equals(vin)) {
				inventory.indexOf(a);
				inventory.remove(a);
				break;
			}
		}
		if (car == null) {
			System.out.println("Vehicle not found");
			return;
		}
		
		
		
	}

	public static void updateVehicle() {
		System.out.println("Enter the VIN of the vehicle you want to update: ");
		String vin = scnr.nextLine();
		AutoInventory car = null;
		for (AutoInventory a : inventory) {
			if (a.getVin().equals(vin)) {
				car = a;
				break;
			}
		}
		if (car == null) {
			System.out.println("Vehicle not found");
			return;
		}
		System.out.println("Enter new Vin (leave blank to keep current value):");
		String newVin = scnr.nextLine();
		if (!newVin.isEmpty()) {
			car.setVin(newVin);
		}
		System.out.println("Enter new make (leave blank to keep current value):");
		String make = scnr.nextLine();
		if (!make.isEmpty()) {
			car.setMake(make);
		}
		System.out.println("Enter new model (leave blank to keep current value):");
		String model = scnr.nextLine();
		if (!model.isEmpty()) {
			car.setModel(model);
		}
		System.out.println("Enter new year (leave blank to keep current value):");
		String year = scnr.nextLine();
		if (!year.isEmpty()) {
			car.setYear(year);
		}
		System.out.println("Enter new color (leave blank to keep current value):");
		String color = scnr.nextLine();
		if (!color.isEmpty()) {
			car.setColor(color);
		}
		System.out.println("Enter new condition (leave blank to keep current value):");
		String cond = scnr.nextLine();
		if (!cond.isEmpty()) {
			car.setCond(cond);
		}
		System.out.println("Enter new mileage (leave blank to keep current value):");
		String mileageStr = scnr.nextLine();
		if (!mileageStr.isEmpty()) {
			try {
				double mileage = Double.parseDouble(mileageStr);
				car.setMileage(mileage);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input for mileage");
			}
		}
		System.out.println("Enter new MSRP (leave blank to keep current value):");
		String msrpStr = scnr.nextLine();
		if (!msrpStr.isEmpty()) {
			try {
				double msrp = Double.parseDouble(msrpStr);
				car.setMsrp(msrp);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input for MSRP");
			}
		}
		System.out.println("Enter new dealer purchase price (leave blank to keep current value):");
		String dealerPurStr = scnr.nextLine();
		if (!dealerPurStr.isEmpty()) {
			try {
				double dealerPur = Double.parseDouble(dealerPurStr);
				car.setDealerPur(dealerPur);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input for dealer purchase price");
			}
		}
		System.out.println("Enter new price (leave blank to keep current value):");
		String priceStr = scnr.nextLine();
		if (!priceStr.isEmpty()) {
			try {
				double price = Double.parseDouble(priceStr);
				car.setPrice(price);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input for price");
			}
		}
		System.out.println("Vehicle updated:");

		for (AutoInventory a : inventory) {
			if (a.getVin().equals(newVin)) {
				car = a;
				break;
			}
		}

		System.out.printf(
				"Make: %s, Model: %s, Year: %s, Color: %s, Condition: %s, VIN: %s, Mileage: %.2f, MSRP: %.2f, Dealer Purchase Price: %.2f, Price: %.2f\n",
				car.getMake(), car.getModel(), car.getYear(), car.getColor(), car.getCond(), car.getVin(),
				car.getMileage(), car.getMsrp(), car.getDealerPur(), car.getPrice());

		printFilePrompt(car);
	}

	public static void printInventory() {
		System.out.println("Current inventory:\n");
		System.out.printf("%-10s %-10s %-10s %-17s %-10s %-10s %-10s%n", "Make", "Model", "Year", "VIN", "Condition",
				"Mileage", "Price");
		System.out.println("------------------------------------------------------------------");
		for (AutoInventory car : inventory) {
			System.out.printf("%-10s %-10s %-10s %-17s %-10s %-10.1f $%-10.2f%n", car.getMake(), car.getModel(),
					car.getYear(), car.getVin(), car.getCond(), car.getMileage(), car.getPrice());
		}
	}
	
	public static void printFilePrompt(AutoInventory car) {
		System.out.println("Do you want to print the information to a file? (y/n)");
		String printToFile = scnr.nextLine();
		if (printToFile.equalsIgnoreCase("y")) {
		    System.out.println("Do you want to print just the added car or the whole inventory? (a/i)");
		    String printOption = scnr.nextLine();
		    if (printOption.equalsIgnoreCase("a")) {
		        printToFile(car);
		    } else if (printOption.equalsIgnoreCase("i")) {
		        printInventoryToFile();
		    }
		}
	}
	
	public static void printToFile(AutoInventory car) {
	    try {
	        FileWriter writer = new FileWriter("car.txt");
	        writer.write(car.toString());
	        writer.close();
	        System.out.println("Car information has been printed to file.");
	    } catch (IOException e) {
	        System.out.println("An error occurred while printing the car information to file.");
	        e.printStackTrace();
	    }
	}

	// Method to print inventory to file
	public static void printInventoryToFile() {
	    try {
	        FileWriter writer = new FileWriter("inventory.txt");
	        for (AutoInventory car : inventory) {
	            writer.write(car.toString());
	        }
	        writer.close();
	        System.out.println("Inventory has been printed to file.");
	    } catch (IOException e) {
	        System.out.println("An error occurred while printing the inventory to file.");
	        e.printStackTrace();
	    }
	}

}