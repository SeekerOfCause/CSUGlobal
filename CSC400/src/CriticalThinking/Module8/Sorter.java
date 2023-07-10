package CriticalThinking.Module8;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorter {
	
	public void quickSort(ArrayList<Order> array, int low, int high, Comparator<Order> comparator) {
		if (low < high) {
			int pivotIndex = partition(array, low, high, comparator); // Get the pivot index
			quickSort(array, low, pivotIndex - 1, comparator); // Recursively sort the lower partition
			quickSort(array, pivotIndex + 1, high, comparator); // Recursively sort the upper partition
		}
	}

	public int partition(ArrayList<Order> array, int low, int high, Comparator<Order> comparator) {
		Order pivot = array.get(high); // Select the pivot element
		int i = low - 1; // Index of the smaller element

		for (int j = low; j < high; j++) {
			if (comparator.compare(array.get(j), pivot) > 0) {
				i++;
				swap(array, i, j); // Swap elements if the current element is greater than the pivot
			}
		}

		swap(array, i + 1, high); // Swap the pivot element to its correct position
		return i + 1; // Return the updated pivot index
	}

	public void swap(ArrayList<Order> array, int i, int j) {
		Order temp = array.get(i); // Temporary variable to hold the element at index i
		array.set(i, array.get(j)); // Replace element at index i with element at index j
		array.set(j, temp); // Replace element at index j with the temporary variable
	}

}
