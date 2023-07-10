package CriticalThinking.Module8;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileWriter;

public class StudentData extends Application {
	
	//Create LinkedList to hold students info
	private List<Student> studentList = new LinkedList<>();

	//Create text input fields and labels for name, address, and gpa
	private Label nameLabel = new Label("Name:");
	private Label addressLabel = new Label("Address:");
	private Label gpaLabel = new Label("GPA:");
	private TextField nameTextField;
	private TextField addressTextField;
	private TextField gpaTextField;

	//Create text area and scroll pane to display student info
	private TextArea studentDisplayTextArea;
	private ScrollPane studentDisplayScrollPane;

	//Create text label to display to user
	private Label addAnotherStudentLabel = new Label("Add another student");

	//Create buttons for submit, yes, and no
	private Button submitButton = new Button();
	private Button yesButton = new Button("Yes");
	private Button noButton = new Button("No");

	//Create grid box and VBox to display in scene
	private VBox studentsInfoVBox;
	private GridPane gridPane = createGridPane();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		//Set title of application window
		primaryStage.setTitle("Student Data Entry");

		
		//Create all labels and fields for application in gridPane
		createLabelsAndFields(gridPane);
		createSubmitButton(gridPane);

		//Call new VBox and set spacing and insets
		studentsInfoVBox = new VBox();
		studentsInfoVBox.setSpacing(10);
		studentsInfoVBox.setPadding(new Insets(10));

		//Call new text area and scroll pane, set text area within scroll pane
		studentDisplayTextArea = new TextArea();
		studentDisplayTextArea.setEditable(false);
		studentDisplayScrollPane = new ScrollPane(studentDisplayTextArea);

		//Set max height and width of scroll pane to max height and width of text area within
		studentDisplayScrollPane.setVmax(studentDisplayTextArea.getMaxHeight());
		studentDisplayScrollPane.setHmax(studentDisplayTextArea.getMaxWidth());

		//Call new VBox, add grid pane and scroll pane
		VBox mainVBox = new VBox(10);
		mainVBox.setAlignment(Pos.CENTER);
		mainVBox.getChildren().addAll(gridPane, studentDisplayScrollPane);

		//Set new scene, add VBox with elements, call new scene in primaryStage
		Scene scene = new Scene(mainVBox, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private GridPane createGridPane() {
		//Create a new gridPane, set alignment and gaps
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		return gridPane;
	}

	private void createLabelsAndFields(GridPane gridPane) {

		//Create new labels and text input areas for user input
		nameLabel.setFont(Font.font(14));
		gridPane.add(nameLabel, 0, 0);

		nameTextField = new TextField();
		gridPane.add(nameTextField, 1, 0);

		addressLabel.setFont(Font.font(14));
		gridPane.add(addressLabel, 0, 1);

		addressTextField = new TextField();
		gridPane.add(addressTextField, 1, 1);

		gpaLabel.setFont(Font.font(14));
		gridPane.add(gpaLabel, 0, 2);

		gpaTextField = new TextField();
		gridPane.add(gpaTextField, 1, 2);
	}

	private void createSubmitButton(GridPane gridPane) {
		
		//Create new submit button, set text to submit and visibility to true in case of secondary or later call to reset submit button.
		submitButton.setText("Submit");
		submitButton.setVisible(true);
		
		//Set on action for submit button to take in input text for name/address and verify double input for GPA.
		submitButton.setOnAction(event -> {
			String name = nameTextField.getText();
			String address = addressTextField.getText();
			double GPA = 0.0;
			try {
				GPA = Double.parseDouble(gpaTextField.getText());
				
				//Reject GPA and advise user to enter correct GPA if outside of normal GPA range.
				if (GPA > 5 || GPA < 0) {
					GPA = 0.0;
					Double.parseDouble(addressTextField.getText());
				}
			} catch (NumberFormatException e) {
				gpaTextField.clear();
				gpaTextField.setPromptText("Invalid GPA");
				return;
			}

			//Create new student object with name, address, and GPA then add student to linked list
			Student student = new Student(name, address, GPA);
			studentList.add(student);

			//Add students info to list displayed to user
			displayStudentInfo(student);

			//Call addAnotherStudent to ask user if they want to enter another students info
			addAnotherStudent(submitButton, gridPane);

		});

		//Call new HBox, set alignment, add submit button, then add button box to gridPane
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().add(submitButton);
		gridPane.add(buttonBox, 1, 3);
	}

	private void displayStudentInfo(Student student) {
		//Create new label with students information, set Font for info, then append to text area to display to user
		Label studentLabel = new Label("\nName: " + student.getName() + "\n" + "Address: " + student.getAddress() + "\n"
				+ "GPA: " + student.getGPA() + "\n");
		studentLabel.setFont(Font.font(14));
		studentDisplayTextArea.appendText(studentLabel.getText());
	}

	private void clearFields() {
		
		//Clear all input fields
		nameTextField.clear();
		addressTextField.clear();
		gpaTextField.clear();
		gpaTextField.setPromptText("");
	}

	

	private void addAnotherStudent(Button submitButton, GridPane gridPane) {
		
		//Set submit button visibility to false to hide while displaying yes and no button
		submitButton.setVisible(false);

		//Set font for label asking if user wants to add more students
		addAnotherStudentLabel.setFont(new Font("Arial", 16));

		//Set label, yes button, and no button to visible
		addAnotherStudentLabel.setVisible(true);
		yesButton.setVisible(true);
		noButton.setVisible(true);

		//Add action to yes button clearing input fields, resetting submit button, and removing yes/no buttons and "add another student" text
		yesButton.setOnAction(e -> {
			clearFields();
			submitButton.setText("Submit");
			createSubmitButton(gridPane);
			yesButton.setVisible(false);
			noButton.setVisible(false);
			addAnotherStudentLabel.setVisible(false);
		});

		//Set no button action to save student data to file
		noButton.setOnAction(e -> {
			saveStudentDataToFile();

		});

		//Call new HBox, align, and add yes/no buttons
		HBox buttonHBox = new HBox(10);
		buttonHBox.setAlignment(Pos.CENTER);
		buttonHBox.getChildren().addAll(yesButton, noButton);

		//Add Hbox to new VBox with "add another student" text
		VBox addMoreStudentsVBox = new VBox(10);
		addMoreStudentsVBox.setAlignment(Pos.CENTER);
		addMoreStudentsVBox.getChildren().addAll(addAnotherStudentLabel, buttonHBox);

		//Add VBox to girdPane
		gridPane.add(addMoreStudentsVBox, 1, 3);
	}

	private void saveStudentDataToFile() {
		
		//Sort entries in linked list
		Collections.sort(studentList, Comparator.comparing(Student::getName));

		//Try/Catch to write entries in linked list to new file
		try (FileWriter writer = new FileWriter("student_data.txt")) {
			for (Student student : studentList) {
				writer.write("Name: " + student.getName() + "\n");
				writer.write("Address: " + student.getAddress() + "\n");
				writer.write("GPA: " + student.getGPA() + "\n\n");
			}
			System.out.println("Student data has been written to student_data.txt.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		//Remove data entry elements, display success of failure message to user, setup exit button to close program
		nameLabel.setText("Student data has been written\nto student_data.txt.");
		addressLabel.setVisible(false);
		gpaLabel.setVisible(false);
		nameTextField.setVisible(false);
		addressTextField.setVisible(false);
		gpaTextField.setVisible(false);
		yesButton.setVisible(false);
		noButton.setVisible(false);
		addAnotherStudentLabel.setVisible(false);
		submitButton = new Button("EXIT");
		submitButton.setVisible(true);
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().add(submitButton);
		gridPane.add(buttonBox, 1, 3);
		submitButton.setOnAction(exitEvent -> {
			System.exit(0);
		});
	}
}
