package criticalThinking;

public class DiscTest {

	public static void main(String[] args) {

		class Item {
			private String name;
			private String description;
			private double price;
			private int quantity;

			public Item(String name, String description, double price, int quantity) {
				this.name = name;
				this.description = description;
				this.price = price;
				this.quantity = quantity;
			}
			// gives item properties

		}

		Item[] inventory = new Item[5]; // creates an array of items with length 5

		inventory[0] = new Item("Phone", "A new phone", 899.50, 4);
		// stores a phone in the inventory array at inventory[0]

		inventory[1] = new Item("Laptop", "A fancy laptop", 1399.99, 2);
		// stores a fancy laptop in inventory array at inventory[1]

		System.out.println("Item 1 is a " + inventory[0].name + " that costs $" + inventory[0].price + ".");
	}
}
