package CriticalThinking.Module6;

import java.util.ArrayList;

public class ShellSort {
	
	//Initialize int arrays with random arrays

	@SuppressWarnings("unchecked")
	public static ArrayList<Object> shellSort(ArrayList<?> array) {
	    int n = array.size();
	    ArrayList<Object> sortedArray = new ArrayList<>(array); // Create a new ArrayList with the same elements

	    for (int gap = n / 2; gap > 0; gap /= 2) {
	        for (int i = gap; i < n; i++) {
	            Object temp = sortedArray.get(i);
	            int j;
	            for (j = i; j >= gap && ((Comparable<Object>) sortedArray.get(j - gap)).compareTo(temp) > 0; j -= gap) {
	                
	                sortedArray.set(j, sortedArray.get(j - gap)); // Update the sorted array
	            }
	            
	            sortedArray.set(j, temp); // Update the sorted array
	        }
	    }
	    
	    return sortedArray;
	}

    
    

	
}
