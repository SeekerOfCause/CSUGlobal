package CriticalThinking.Module7;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {

    public static <T> T[] mergeSort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;

        T[] left = mergeSort(Arrays.copyOfRange(array, 0, mid), comparator);
        T[] right = mergeSort(Arrays.copyOfRange(array, mid, array.length), comparator);

        return merge(left, right, comparator);
    }

    private static <T> T[] merge(T[] left, T[] right, Comparator<T> comparator) {
        T[] sortedArray = (T[]) new Object[left.length + right.length];
        int leftIdx = 0;
        int rightIdx = 0;
        int listIdx = 0;

        while (leftIdx < left.length && rightIdx < right.length) {
            T leftElement = left[leftIdx];
            T rightElement = right[rightIdx];

            if (comparator.compare(leftElement, rightElement) <= 0) {
                sortedArray[listIdx] = leftElement;
                leftIdx++;
            } else {
                sortedArray[listIdx] = rightElement;
                rightIdx++;
            }
            listIdx++;
        }

        while (leftIdx < left.length) {
            sortedArray[listIdx] = left[leftIdx];
            leftIdx++;
            listIdx++;
        }

        while (rightIdx < right.length) {
            sortedArray[listIdx] = right[rightIdx];
            rightIdx++;
            listIdx++;
        }

        return sortedArray;
    }
}

