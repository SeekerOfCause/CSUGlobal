package CriticalThinking.Module8;

class Order {
	private String lastName;
	private int orderNumber;
	private double totalCost;

	public Order(String lastName, int orderNumber, double totalCost) {
		this.lastName = lastName;
		this.orderNumber = orderNumber;
		this.totalCost = totalCost;
	}

	public String getLastName() {
		return lastName;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public double getTotalCost() {
		return totalCost;
	}
}
