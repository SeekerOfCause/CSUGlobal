package CriticalThinking.Module2;

public class BagDemo {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();

        // Testing the ShoppingCart class
        shoppingCart.testAdd();
        shoppingCart.displayBag();
        System.out.println("\n" + shoppingCart.isFullDisplay());
        shoppingCart.testAddTwo();
        shoppingCart.displayBag();
        System.out.println("\n" + shoppingCart.isFullDisplay());
    }
}
