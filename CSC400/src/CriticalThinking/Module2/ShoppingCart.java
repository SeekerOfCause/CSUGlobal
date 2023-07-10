package CriticalThinking.Module2;

import java.util.Arrays;

public class ShoppingCart implements BagInterface<MyType> {
    private MyType[] myBag;
    private int numberOfProducts;
    private static final int DEFAULT_CAPACITY = 10;

    //Creates shopping cart object
    public ShoppingCart() {
        myBag = new MyType[DEFAULT_CAPACITY];
        numberOfProducts = 0;
    }


    //Adds MyType objects to shopping cart to the end of MyType array
    public void add(MyType newEntry) {
        if (!isFull()) {
            myBag[numberOfProducts] = newEntry;
            numberOfProducts++;
        }
    }


    //Returns a copy of the MyType array
    public MyType[] toArray() {
        return Arrays.copyOf(myBag, numberOfProducts);
    }

    //Returns true if shopping cart is full
    public boolean isFull() {
        return numberOfProducts == DEFAULT_CAPACITY;
    }
    
    public String isFullDisplay() {
    	String fullOrNah;
    	if (numberOfProducts == DEFAULT_CAPACITY) {
    		fullOrNah = "The shopping cart is full\n";
    	}
    	else {
    		fullOrNah = "The shopping cart is not full\n";
    	}
    	return fullOrNah;
    }

    // Additional methods specific to the ShoppingCart class
    public void testAdd() {
        add(new MyType("Apple Juice", "A delicious fruit", 2.95, 1234567890123L));
        add(new MyType("Trix", "A cereal, also delicious", 5.99, 9876543210417L));
        add(new MyType("Milk, Whole, 1 gal", "Cow juice, for the cereal of course", 3.84, 9789465436987L));
        add(new MyType("Paper Towel, 12 rolls", "Absorbent paper to soak up spilled milk", 12.99, 79846508489704L));
        
    }
    
    public void testAddTwo() {
    	add(new MyType("Water, Spring, 1 gal", "Liquid required by the body", 2.95, 363789783793L));
        add(new MyType("Eggs, 1 dozen", "Chickens, but not quite", 5.99, 96789378937893L));
        add(new MyType("Cheese, Cheddar, Shredded, 16 oz", "Plenty of cheese in here...", 3.84, 7896789789367L));
        add(new MyType("Bread, Wheat", "For PB&J's only... that means you Steve...", 12.99, 65416817867564L));
        add(new MyType("Soup, Tomato", "For those who like that kinda thing", 3.84, 6541681768716L));
        add(new MyType("Cookies, Chocolate Chip", "A", 12.99, 541867186784543L));
    }

    //Displays content of shopping cart
    public void displayBag() {
    	System.out.println("Contents of the shopping cart:\n");
        MyType[] contents = toArray();
        for (MyType item : contents) {
            System.out.println(item.toString() + "\n");
        }
    }
}
