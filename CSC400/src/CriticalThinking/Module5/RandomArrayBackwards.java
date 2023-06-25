package CriticalThinking.Module5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class RandomArrayBackwards extends Application {
	private String[] arr;
	private Random random = new Random();
	private TextArea inputArea;
	private TextArea resultArea;
	private Button displayButton;
	private String result;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Array Portion Reverse");

		// Create JavaFX components
		displayButton = new Button("Display Random Portion");
		displayButton.setOnAction(e -> displayRandomPortion());

		inputArea = new TextArea();
		inputArea.setPromptText("Enter a string");

		resultArea = new TextArea();
		resultArea.setPromptText("A portion will be displayed backwards");
		resultArea.setEditable(false);

		inputArea.setDisable(true);

		// Create a VBox and add components
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(20));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(inputArea, displayButton, resultArea);

		Scene scene = new Scene(vbox, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();

		displayButton.requestFocus();
		inputArea.setDisable(false);

	}

	private void displayRandomPortion() {
		//Initialize 'result' as an empty string
		result = "";
		
		//Split input text into array and set to arr 
		arr = inputArea.getText().split("");

		//If arr has values, set start and end integers then check if start is less than end
		if (arr.length > 0) {
			int start = random.nextInt(arr.length);
			int end = random.nextInt(arr.length);

			// Ensure start index is smaller than end index
			if (start > end) {
				int temp = start;
				start = end;
				end = temp;
			}

			//Call recursive function to crate backwards portion
			displayArrayPortionBackwards(start, end);

			//Set result of recursion to resultArea text
			resultArea.setText(result);
		} else {
			//If no input, change button to display error message
			displayButton.setText("Please enter a string, then click the button");
		}
	}

	private void displayArrayPortionBackwards(int start, int end) {
		//Base case. if start is greater than end, end function.
		if (start > end) {
			return;
		}

		//Append value of arr[end]
		result += arr[end];
		
		//Recursively call with end - 1 as end value.
		displayArrayPortionBackwards(start, end - 1);
	}

	public static void main(String[] args) {
		launch(args);

	}
}
