package CriticalThinking.Module2;

public class MyType {
	String name;
	String description;
	double price;
	long barcode;

	
//	public MyType() {
//		name = "";
//		description = "";
//		price = 0.0;
//		barcode = 0000000000000;
//	}
//	
	public MyType(String name, String description, double price, long barcode) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.barcode = barcode;
	}
	
	@Override
    public String toString() {
        return "Name: " + name +
                "\nDescription: " + description +
                "\nPrice: " + price +
                "\nBarcode: " + barcode;
    }
}
