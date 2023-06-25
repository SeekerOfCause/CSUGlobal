package CriticalThinking.Module6;

import java.util.ArrayList;

public class TestIfSorted {

	 public static <T extends Comparable<T>> String testIfSorted(Object[] sortedArray) {
	    	boolean ordered = true;
	    	for (int i = 1; i < sortedArray.length; i += 2) {
	    		if (((Comparable) sortedArray[i]).compareTo((Comparable) sortedArray[i - 1]) < 0) {
	                ordered = false;
	            }
	    	}
	    	
	    	if (ordered) {
	    		return "The array is sorted";
	    	}
	    	else {
	    		return "The array is not sorted";
	    	}
	    }

	
}
