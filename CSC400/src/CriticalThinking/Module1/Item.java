package CriticalThinking.Module1;

public class Item {
    private String name;
    private double price;

    //Constructor for an Item
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters

    //Get the name of this item
    public String getName() {
        return name;
    }

    //Set the name of this item
    public void setName(String name) {
        this.name = name;
    }

    //Get the price of this item
    public double getPrice() {
        return price;
    }

    //Set the price of this item
    public void setPrice(double price) {
        this.price = price;
    }
    
    //Get a description of item in form "[name] ($[price])"
    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}
