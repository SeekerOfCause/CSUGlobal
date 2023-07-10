package CriticalThinking.Module6;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class SortingSystem extends Application {

    private ListView<Student> studentListView;
    private ObservableList<Student> studentList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student List GUI");

        // Initialize list of students
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", 20, "123 Main St"));
        students.add(new Student(2, "Alice", 22, "456 Elm St"));
        students.add(new Student(3, "David", 19, "654 Cedar St"));
        students.add(new Student(4, "Sarah", 18, "789 Oak St"));
        students.add(new Student(5, "Michael", 21, "234 Maple St"));
        students.add(new Student(6, "Emily", 19, "321 Pine St"));
        students.add(new Student(7, "Jacob", 22, "890 Spruce St"));
        students.add(new Student(8, "Olivia", 20, "987 Walnut St"));
        students.add(new Student(9, "Daniel", 23, "567 Birch St"));
        students.add(new Student(10, "Sophia", 21, "432 Fir St"));
        students.add(new Student(11, "Matthew", 19, "345 Cedar St"));
        students.add(new Student(12, "Emma", 18, "678 Oak St"));
        students.add(new Student(13, "Andrew", 22, "901 Maple St"));
        students.add(new Student(14, "Grace", 20, "543 Pine St"));
        students.add(new Student(15, "William", 21, "876 Spruce St"));
        students.add(new Student(16, "Ava", 19, "210 Walnut St"));
        students.add(new Student(17, "James", 20, "543 Birch St"));
        students.add(new Student(18, "Mia", 23, "321 Fir St"));
        students.add(new Student(19, "Alexander", 18, "987 Cedar St"));
        students.add(new Student(20, "Sofia", 21, "654 Oak St"));

        // Create title row for our observable array
        Student studentTitle =  new StudentTitle("No.", "Name", "Age", "Address");
        
        

        // Add array of students and title row to studentList
        studentList = FXCollections.observableArrayList(students);
        studentList.add(0, studentTitle);

        // Create main display area
        studentListView = new ListView<>();
        studentListView.setItems(studentList);

        // Create buttons for sorting and set actions with sorting functions
        Button sortNameButton = new Button("Sort by Name");
        sortNameButton.setOnAction(e -> sortListByName());

        Button sortRollNoButton = new Button("Sort by Roll No");
        sortRollNoButton.setOnAction(e -> sortListByRollNo());

        Button sortAgeButton = new Button("Sort by Age");
        sortAgeButton.setOnAction(e -> sortListByAge());

        // Create new layout and add dislpay and buttons
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(studentListView, sortNameButton, sortRollNoButton, sortAgeButton);

        // Create new scene
        Scene scene = new Scene(layout, 450, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sortListByName() {
        studentList.sort(new NameComparator());
    }

    private void sortListByRollNo() {
        studentList.sort(new RollNoComparator());
    }
    
    private void sortListByAge() {
    	studentList.sort(new AgeComparator());
    }
}
