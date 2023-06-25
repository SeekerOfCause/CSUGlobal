package CriticalThinking.Module1;

import java.util.ArrayList;

public class OnlineCart {
	//ArrayList to hold our OnlineDeliveryBag shopping cart objects
	private static ArrayList<OnlineDeliveryBag> cartArray;
	
	public static void main(String[] args) {
		//Create new cart ArrayList
		cartArray = new ArrayList<OnlineDeliveryBag>();
		
		//Create new OnlineDeliveryBag shopping cart for this customer
		//Set customer's number to cartArray's size
        OnlineDeliveryBag customersBag = new OnlineDeliveryBag(cartArray.size());
        
        //Create new item and place in customers cart
        Item item = new Item("Can of Soup", 4.0);
        customersBag.addItem(item);
        
        //Retrieve all items in customer's cart and return in an array
        ArrayList<Item> items = customersBag.getItems();
        
        //Print out a list of items in the cart
	        for (int i = 0; i < items.size(); i++) {
	        	System.out.println(items.get(i).toString());
	        }
	        
	    //Set size to and print the size of this customers cart
        int size = customersBag.size();
        System.out.println(size);
        
        //Check if customers shopping cart is empty, print result
        boolean isEmpty = customersBag.isEmpty();
        System.out.println(isEmpty);  // Output: false
        
        //Remove item from customers shopping cart, print result
        boolean removed = customersBag.removeItem(item);
        System.out.println(removed);  // Output: true
	}

}
