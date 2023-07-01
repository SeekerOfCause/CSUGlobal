package CriticalThinking.Module6;

public class TestIfSorted {

	 @SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> String testIfSorted(Object[] sortedArray) {
	    	boolean ordered = true;
	    	for (int i = 1; i < sortedArray.length; i++) {
	    		if (((Comparable<Comparable<?>>) sortedArray[i]).compareTo((Comparable<?>) sortedArray[i - 1]) < 0) {
	                ordered = false;
	            }
	    	}
	    	
	    	if (ordered) {
	    		return "The array is sorted in ascending order";
	    	}
	    	else {
	    		return "The array is not sorted";
	    	}
	    }

	
}
