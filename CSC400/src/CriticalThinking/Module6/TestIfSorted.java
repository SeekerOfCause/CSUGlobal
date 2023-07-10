package CriticalThinking.Module6;

<<<<<<< HEAD
public class TestIfSorted {

	 @SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> String testIfSorted(Object[] sortedArray) {
	    	boolean ordered = true;
	    	for (int i = 1; i < sortedArray.length; i++) {
	    		if (((Comparable<Comparable<?>>) sortedArray[i]).compareTo((Comparable<?>) sortedArray[i - 1]) < 0) {
=======
import java.util.ArrayList;

public class TestIfSorted {

	 public static <T extends Comparable<T>> String testIfSorted(Object[] sortedArray) {
	    	boolean ordered = true;
	    	for (int i = 1; i < sortedArray.length; i += 2) {
	    		if (((Comparable) sortedArray[i]).compareTo((Comparable) sortedArray[i - 1]) < 0) {
>>>>>>> cbee1a7 (Reformatting directory)
	                ordered = false;
	            }
	    	}
	    	
	    	if (ordered) {
<<<<<<< HEAD
	    		return "The array is sorted in ascending order";
=======
	    		return "The array is sorted";
>>>>>>> cbee1a7 (Reformatting directory)
	    	}
	    	else {
	    		return "The array is not sorted";
	    	}
	    }

	
}
