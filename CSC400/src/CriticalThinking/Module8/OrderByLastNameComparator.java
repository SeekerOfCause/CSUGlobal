package CriticalThinking.Module8;

import java.util.Comparator;

public class OrderByLastNameComparator implements Comparator<Order> {
	/**
	 * Compares two Order objects based on the customers last names.
	 *
	 * @param o1 the first Order object to compare
	 * @param o2 the second Order object to compare
	 * @return a negative integer if o1's last name is less than o2's last name,
	 *         zero if they have the same last name, a positive integer if o1's last
	 *         name is greater than o2's last name
	 */
	@Override
	public int compare(Order o1, Order o2) {
		return o1.getLastName().compareTo(o2.getLastName());
	}
}
