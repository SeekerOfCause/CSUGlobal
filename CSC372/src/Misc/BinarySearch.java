package Misc;

public class BinarySearch {
	public static void main(String[] args) {
		int[] array = { 2, 5, 8, 12, 16, 23, 38, 56, 72, 91 };
		int target = 23;

		int result = binarySearch(array, target);

		if (result == -1) {
			System.out.println("Element not found in the array.");
		} else {
			System.out.println("Element found at index " + result);
		}
	}

	public static int binarySearch(int[] array, int target) {
		int low = 0;
		int high = array.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1;
	}
}
