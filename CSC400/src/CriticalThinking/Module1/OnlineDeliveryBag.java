package CriticalThinking.Module1;

import java.util.ArrayList;

public class OnlineDeliveryBag {
	//ArrayList to hold items in this bag
    private ArrayList<Item> items;
    
    //Private int to hold customer number designation of shopping cart Bag object
    private int customerNumber;

    //Constructor to create new ArrayList that will hold our items
    public OnlineDeliveryBag(int num) {
        items = new ArrayList<>();
        setCustomerNum(num);
    }
    
    //Set customer number for this shopping cart Bag
    public void setCustomerNum(int num) {
    	this.customerNumber = num;
    }
    
    //Get customer number for this shopping cart Bag
    public int getCustomerNum() {
    	return this.customerNumber;
    }

    //Add an item to our Bag's ArrayList
    public void addItem(Item item) {
        items.add(item);
    }
    
    //Remove an item from our Bag's ArrayList
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    //Get all items in our Bag's list
    public ArrayList<Item> getItems() {
        return items;
    }

    //Check to see if our Bag's ArrayList is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    //Get the size of our Bag's ArrayList
    public int size() {
        return items.size();
    }
}
