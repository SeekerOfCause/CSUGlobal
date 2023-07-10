package CriticalThinking.Module6;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArrayEntry extends Application {
	private  ArrayList<Integer> testArray;
	
    private TextField inputField;
    private Button processButton;
    private TextArea sortedResult;
    private TextArea isSortedResult;
    private Button testSortedButton;
    private Button testUnsortedButton;

    @Override
    public void start(Stage primaryStage) {
<<<<<<< HEAD

        inputField = new TextField();
        inputField.setPromptText("Enter an array of element separated by a ','");
        
=======
        // Create UI components
        inputField = new TextField();
>>>>>>> cbee1a7 (Reformatting directory)
        processButton = new Button("Process");
        testSortedButton = new Button("Test Sorted");
        testUnsortedButton = new Button("Test Unsorted");
        
        
        sortedResult = new TextArea();
        sortedResult.setEditable(false);
        
        isSortedResult = new TextArea();
        isSortedResult.setEditable(false);

<<<<<<< HEAD
=======
        // Process button event handler
>>>>>>> cbee1a7 (Reformatting directory)
        processButton.setOnAction(event -> processInput());
        testSortedButton.setOnAction(event -> testSorted());
        testUnsortedButton.setOnAction(event -> testUnsorted());
        
        HBox tests = new HBox(5);
        tests.getChildren().addAll(testSortedButton, testUnsortedButton);

<<<<<<< HEAD
=======
        // Layout setup
>>>>>>> cbee1a7 (Reformatting directory)
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(inputField, processButton, sortedResult, isSortedResult, tests);

<<<<<<< HEAD
        Scene scene = new Scene(root, 400, 300);
        
        
        primaryStage.setTitle("Array Input GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        inputField.setVisible(false);
        processButton.requestFocus();
        inputField.setVisible(true);
        
=======
        // Scene setup
        Scene scene = new Scene(root, 400, 300);

        // Stage setup
        primaryStage.setTitle("Array Input GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
>>>>>>> cbee1a7 (Reformatting directory)
    }
    
    private void processInput() {
		String input = inputField.getText();
		ArrayList<Object> sortedInput = parseArray(input);
		Object[] sortedArray = ShellSort.shellSort(sortedInput).toArray();
		sortedResult.setText(arrayToString(sortedArray));
		
	}

	private void testSorted() {
		getArrays(1);
		inputField.setText(testArray.toString());
		Object[] sortedArray = ShellSort.shellSort(testArray).toArray();
		sortedResult.setText(arrayToString(sortedArray));
		isSortedResult.setText(TestIfSorted.testIfSorted(sortedArray));
	}
    
    private void testUnsorted() {
		getArrays(0);
		inputField.setText(testArray.toString());
		Object[] sortedArray = ShellSort.shellSort(testArray).toArray();
		sortedResult.setText(arrayToString(sortedArray));
		isSortedResult.setText(TestIfSorted.testIfSorted(sortedArray));
	}

	private void getArrays(int i) {
    	
    	testArray = new RandomArray(i).getArray();
    	
    }

    

    private ArrayList<Object> parseArray(String input) {
        input = input.trim();
        if (input.startsWith("{") && input.endsWith("}")) {
            input = input.substring(1, input.length() - 1);
        } else if (input.startsWith("[") && input.endsWith("]")) {
            input = input.substring(1, input.length() - 1);
        }
        String[] elements = input.split(",");
        ArrayList<Object> array = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
        	array.add(elements[i]);
        }
        return array;
    }

    private String arrayToString(Object[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i].toString());
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void displayGUI() {
        launch();
    }
}
