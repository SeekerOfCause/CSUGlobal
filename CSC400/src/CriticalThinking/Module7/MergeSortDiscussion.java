package CriticalThinking.Module7;

import java.util.Comparator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MergeSortDiscussion extends Application {

    private TableView<Student> table;
    private ObservableList<Student> students;

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Merge Sort GUI");

        // Initialize the table and student data
        table = new TableView<>();
        students = FXCollections.observableArrayList(
                new Student("Alice", 21, "A"),
                new Student("Bob", 19, "B"),
                new Student("Charlie", 20, "C"),
                new Student("David", 18, "B"),
                new Student("Eve", 22, "A")
        );

        // Create table columns
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Student, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        // Add columns to the table
        table.getColumns().addAll(nameColumn, ageColumn, gradeColumn);
        table.setItems(students);

        // Create sorting buttons
        Button sortByNameBtn = new Button("Sort by Name");
        sortByNameBtn.setOnAction(e -> sortBy("name"));

        Button sortByAgeBtn = new Button("Sort by Age");
        sortByAgeBtn.setOnAction(e -> sortBy("age"));

        Button sortByGradeBtn = new Button("Sort by Grade");
        sortByGradeBtn.setOnAction(e -> sortBy("grade"));

        // Arrange buttons horizontally
        HBox buttonBox = new HBox(10, sortByNameBtn, sortByAgeBtn, sortByGradeBtn);
        buttonBox.setPadding(new Insets(10));

        // Arrange table and buttons vertically
        VBox vbox = new VBox(10, table, buttonBox);

        // Set up the scene
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private <T> void sortBy(String attribute) {
    	
    	Comparator<T> name = (Comparator<T>) StudentComparator.NAME;
    	Comparator<T> age = (Comparator<T>) StudentComparator.AGE;
    	Comparator<T> grade = (Comparator<T>) StudentComparator.GRADE;
    	T[] studentList = (T[]) students.toArray();
        switch (attribute) {
            case "name":
                //students = MergeSort.mergeSort(studentList, name);
                break;
            case "age":
                MergeSort.mergeSort(studentList, age);
                break;
            case "grade":
                MergeSort.mergeSort(studentList, grade);
                break;
        }
    }
}

    
    
