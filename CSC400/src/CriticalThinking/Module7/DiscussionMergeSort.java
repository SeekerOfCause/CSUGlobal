package CriticalThinking.Module7;

import java.util.Arrays;
import java.util.Comparator;

public class DiscussionMergeSort {

    public static void main(String[] args) {
        Person[] people = {
            new Person("Alice", 85),
            new Person("Bob", 73),
            new Person("Charlie", 94),
            new Person("David", 83),
            new Person("Eve", 79),
        };

        Comparator<Person> gradeComparator = Comparator.comparingInt(Person::getGrade);

        Person[] sortedPeople = mergeSort(people, gradeComparator);

        // Print the sorted array of people
        for (Person person : sortedPeople) {
            System.out.println(person);
        }
    }

    public static Person[] mergeSort(Person[] array, Comparator<Person> comparator) {
        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;

        Person[] left = mergeSort(Arrays.copyOfRange(array, 0, mid), comparator);
        Person[] right = mergeSort(Arrays.copyOfRange(array, mid, array.length), comparator);

        return merge(left, right, comparator);
    }

    private static Person[] merge(Person[] left, Person[] right, Comparator<Person> comparator) {
        Person[] sortedArray = new Person[left.length + right.length];
        int leftIdx = 0;
        int rightIdx = 0;
        int listIdx = 0;

        while (leftIdx < left.length && rightIdx < right.length) {
            if (comparator.compare(left[leftIdx], right[rightIdx]) <= 0) {
                sortedArray[listIdx] = left[leftIdx];
                leftIdx++;
            } else {
                sortedArray[listIdx] = right[rightIdx];
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

    static class Person {
        private String name;
        private int grade;

        public Person(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public int getGrade() {
            return grade;
        }

        @Override
        public String toString() {
            return name + " - Grade: " + grade;
        }
    }
}
