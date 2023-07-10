package Misc;

public class QuickSort {
	public static void main(String[] args) {
		
		int[] array = { 9, 4, 7, 2, 1, 5, 8, 3, 6 };

		System.out.println("Before sorting: ");
		printArray(array);

		quickSort(array, 0, array.length - 1);

		System.out.println("After sorting: ");
		printArray(array);
	}

	public static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(array, low, high);

			quickSort(array, low, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, high);
		}
	}

	public static int partition(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (array[j] < pivot) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, i + 1, high);

		return i + 1;
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	

	public static void printArray(int[] array) {
		for (int num : array) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	
}
