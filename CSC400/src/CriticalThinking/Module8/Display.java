package CriticalThinking.Module8;

import java.util.ArrayList;

class Display {
	private ArrayList<Order> orderByLastName; // ArrayList to store orders sorted by last name
	private ArrayList<Order> orderByOrderNumber; // ArrayList to store orders sorted by order number
	private Sorter sorter = new Sorter();
	private int size; // number of orders stored

	public Display(int capacity) {
		orderByLastName = new ArrayList<Order>(); // Initialize the ArrayList for sorting by last name
		orderByOrderNumber = new ArrayList<Order>(); // Initialize the ArrayList for sorting by order number
		size = 0; // Initialize the size to 0
	}

	public void addOrder(Order order) {
		orderByLastName.add(order); // Add the order to the ArrayList sorted by last name
		orderByOrderNumber.add(order); // Add the order to the ArrayList sorted by order number
		size++; // Increment the size
		sorter.quickSort(orderByLastName, 0, size - 1, new OrderByLastNameComparator()); // Sort the ArrayList by last name
		sorter.quickSort(orderByOrderNumber, 0, size - 1, new OrderByOrderNumberComparator()); // Sort the ArrayList by order
																						// number
	}

	public void removeOrder(int index) {
		if (index < 0 || index >= size) {
			System.out.println("Invalid index!"); // Print error message if the index is invalid
			return;
		}

		orderByOrderNumber.remove(index); // Remove the order from the ArrayList sorted by order number
		orderByLastName = new ArrayList<>(orderByOrderNumber); // Update the ArrayList sorted by last name
		size--; // Decrement the size
		sorter.quickSort(orderByLastName, 0, size - 1, new OrderByLastNameComparator()); // Sort the ArrayList by last name
	}

	// Display orders to users
	public void displayOrders() {
		System.out.println("Orders sorted by last name:");
		for (int i = 0; i < size; i++) { // Prints orders in orderByLastName
			Order order = orderByLastName.get(i);
			System.out.println("Last Name: " + order.getLastName() + ", Order Number: " + order.getOrderNumber()
					+ ", Total Cost: " + order.getTotalCost());
		}

		System.out.println("\nOrders sorted by order number:");
		for (int i = 0; i < size; i++) { // Prints orders in ordersByOrderNumber
			Order order = orderByOrderNumber.get(i);
			System.out.println("Last Name: " + order.getLastName() + ", Order Number: " + order.getOrderNumber()
					+ ", Total Cost: " + order.getTotalCost());
		}
	}



	public int getSize() {
		return size; // Return the size of the display
	}
}