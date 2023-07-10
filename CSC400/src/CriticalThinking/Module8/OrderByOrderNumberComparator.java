package CriticalThinking.Module8;

import java.util.Comparator;

public class OrderByOrderNumberComparator implements Comparator<Order> {
	/**
	 * Compares two Order objects based on their order numbers.
	 *
	 * @param o1 the first Order object to compare
	 * @param o2 the second Order object to compare
	 * @return a negative integer if o1's order number is less than o2's order
	 *         number, zero if they have the same order number, a positive integer
	 *         if o1's order number is greater than o2's order number
	 */
    @Override
    public int compare(Order o1, Order o2) {
        return Integer.compare(o1.getOrderNumber(), o2.getOrderNumber());
    }
}