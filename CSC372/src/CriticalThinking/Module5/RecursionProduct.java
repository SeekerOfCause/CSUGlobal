package CriticalThinking.Module5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecursionProduct extends Application {
	
	//initiate private variables
	private TextField[] numberFields;
	private Label resultLabel;
	private Label directions;


	public void start(Stage primaryStage) {
		
		//set title of window
		primaryStage.setTitle("Product Recursion Calculator");

		//create new grid pane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		//create new label for user directions
		directions = new Label("Enter your numbers:");

		//use loop to create input fields for all 5 numbers
		numberFields = new TextField[5];
		for (int i = 0; i < 5; i++) {
			Label numberLabel = new Label("Number " + (i + 1) + ":");
			TextField numberField = new TextField();
			numberFields[i] = numberField;

			gridPane.addRow(i, numberLabel, numberField);
		}

		//create new button, set action to call calculateProduct()
		Button calculateButton = new Button("Submit");
		calculateButton.setOnAction(e -> calculateProduct());

		//create new label to show direction by default and display product when submitted
		resultLabel = new Label("Press submit when ready");
		gridPane.add(calculateButton, 0, 5, 2, 1);
		gridPane.add(resultLabel, 0, 6, 2, 1);
		
		//create new VBox, set inserts and position, then add directions and grid pane
		VBox vBox = new VBox(10);
		vBox.setPadding(new Insets(20));
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(directions, gridPane);
		
		//set new scene, call and show on primaryStage
		Scene scene = new Scene(vBox, 300, 350);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void calculateProduct() {

		//bool to see if all inputs are valid
		boolean goodInput = true;

		//new number array
		int[] numbers = new int[5];

		//for loop to validate input fields and display error message if invalid
		for (int i = 0; i < 5; i++) {
			if (numberFields[i].getText().equals("") || !numberFields[i].getText().matches("[0-9]")) {
				resultLabel.setText("Please enter a number in each section.");
				goodInput = false;
			}
		}
			//if goodInput is true, execute for loop to set inputs to an array, then pass to multiply()
			if (goodInput) {
				for (int j = 0; j < 5; j++) {
				String input = numberFields[j].getText();
				numbers[j] = Integer.parseInt(input);
				}
				int product = multiply(numbers, 0);
				resultLabel.setText("Product of the numbers: " + product);
			}

			
		
	}

	private int multiply(int[] numbers, int index) {
		//kill statement to end recursion if index has reached the end of the array and return answer
		if (index == numbers.length - 1) {
			return numbers[index];
		} else {
			//call recursively to multiply all numbers
			return numbers[index] * multiply(numbers, index + 1);
		}
	}
}
