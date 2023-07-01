package CriticalThinking.Module7;

import java.util.Comparator;

public class StudentComparator {
    public static final Comparator<Student> NAME = Comparator.comparing(Student::getName);
    public static final Comparator<Student> AGE = Comparator.comparingInt(Student::getAge);
    public static final Comparator<Student> GRADE = Comparator.comparing(Student::getGrade);
}
