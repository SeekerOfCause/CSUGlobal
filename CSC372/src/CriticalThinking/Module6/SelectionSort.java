package CriticalThinking.Module6;

import java.util.ArrayList;
import java.util.Comparator;

public class SelectionSort {
    public static void selectionSort(ArrayList<Student> students, Comparator<Student> comparator) {
        int n = students.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(students.get(j), students.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            Student temp = students.get(minIndex);
            students.set(minIndex, students.get(i));
            students.set(i, temp);
        }
    }
}
