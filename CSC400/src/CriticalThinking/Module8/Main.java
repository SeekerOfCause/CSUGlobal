package CriticalThinking.Module8;

import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Display display = new Display(100);

		int choice;
		do {

			// Print out main menu to user
			System.out.println("\nMenu:");
			System.out.println("1. Add an order");
			System.out.println("2. Remove an order");
			System.out.println("3. Display order list");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				scanner.nextLine(); // Consume the newline character
				System.out.print("Enter customer last name: ");
				String lastName = scanner.nextLine();
				System.out.print("Enter total cost: ");
				double totalCost = scanner.nextDouble();

				Order order = new Order(lastName, display.getSize() + 1, totalCost);
				display.addOrder(order);
				System.out.println("Order added successfully!");
				break;
			case 2:
				System.out.print("Enter the order number to be removed: ");
				int index = scanner.nextInt();
				display.removeOrder(index);
				break;
			case 3:
				display.displayOrders();
				break;
			case 4:
				System.out.println("Exiting...");
				scanner.close();
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		} while (choice != 4);
	}
}