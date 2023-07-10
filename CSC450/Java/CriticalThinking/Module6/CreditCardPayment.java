package CriticalThinking.Module6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreditCardPayment {
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);

		System.out.print("Enter your credit card number: ");
		String creditCardNumber = scnr.nextLine();

		// Saving credit card number to a text file
		saveToFile(creditCardNumber);

		System.out.println("Payment successful!");

		scnr.close();
	}

	public static void saveToFile(String data) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("credit_card_numbers.txt"));
			writer.write(data);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
