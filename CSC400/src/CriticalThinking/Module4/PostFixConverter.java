package CriticalThinking.Module4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class PostFixConverter extends Application {

	StringBuilder postfixString = new StringBuilder();
	Deque<Character> operatorStack = new ArrayDeque<>();
	String postfix = "";
	
	Label inputLabel = new Label("Enter Infix Expression:");
	TextField userInput = new TextField();
	TextArea postfixDisplay = new TextArea();
	Button convertButton = new Button("Convert");
	Button testButton = new Button("Test");
	Button resetButton = new Button("Reset");

	String[] infixExpressions = { "2 + 3 * 4", "(5 + 6) * 7", "8 - (9 + 10)", "11 * (12 - 13) / 14",
			"(15 + 16) / (17 - 18)", "19 * 20 + 21 - 22", "23 * (24 + 25) / 26", "27 + (28 - 29) * 30",
			"31 / (32 + 33) - 34", "35 + 36 * (37 - 38)", "(39 - 40) * 41 / 42", "43 / (44 + 45) - 46",
			"47 + 48 * (49 - 50)", "(51 - 52) * 53 / 54", "55 / (56 + 57) - 58", "59 + 60 * (61 - 62)",
			"(63 - 64) * 65 / 66", "67 / (68 + 69) - 70", "71 + 72 * (73 - 74)", "(75 - 76) * 77 / 78" };

	static int precedence(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;

		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}

	@Override
	public void start(Stage primaryStage) {

		postfixDisplay.setEditable(false);
		postfixDisplay.setDisable(true);
		postfixDisplay.setPromptText("Enter an Infix to see Postfix Conversion");

		convertButton.setVisible(true);
		testButton.setVisible(true);
		resetButton.setVisible(false);
		convertButton.setOnAction(event -> processInfixExpression(userInput.getText()));
		testButton.setOnAction(testEvent -> testInfixConversion());
		
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		vbox.getChildren().addAll(inputLabel, userInput, postfixDisplay, convertButton, testButton, resetButton);

		
		Scene scene = new Scene(vbox, 400, 300);
		primaryStage.setTitle("Infix Expression GUI");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void testInfixConversion() {
		Random random = new Random();
		int randomNum = random.nextInt(20);
		System.out.println(infixExpressions[randomNum]);
		processInfixExpression(infixExpressions[randomNum].toString());
	}

	private void processInfixExpression(String infixExpression) {
		postfix = "";
		
		userInput.setText(infixExpression);
		
		postfixDisplay.setDisable(false);
		postfixDisplay.clear();
		
		convertButton.setVisible(false);
		testButton.setVisible(false);
		resetButton.setVisible(true);
		resetButton.setOnAction(resetEvent -> {
			resetScreen();
		});

		for (int i = 0; i < infixExpression.length(); ++i) {
			char c = (char) infixExpression.charAt(i);

			
			if (Character.isLetterOrDigit(c) || Character.isWhitespace(c) || Character.compare(c, ".".toCharArray()[0]) == 0)
				postfix += c;

		
			else if (c == '(')
				operatorStack.push(c);

		
			else if (c == ')') {
				while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
					postfix += operatorStack.peek();
					operatorStack.pop();
				}

				operatorStack.pop();
			}

		
			else {
				while (!operatorStack.isEmpty() && precedence(c) <= precedence(operatorStack.peek())) {

					postfix += operatorStack.peek();
					operatorStack.pop();
				}
				operatorStack.push(c);
			}
		}

		
		while (!operatorStack.isEmpty()) {
			if (operatorStack.peek() == '(')
				postfix = "Invalid Expression";
			postfix += operatorStack.peek();
			operatorStack.pop();
		}
		
		postfixDisplay.setText(postfix);
	}
	
	public void resetScreen() {
		postfixDisplay.setText("");
		userInput.setText("");
		resetButton.setVisible(false);
		convertButton.setVisible(true);
		testButton.setVisible(true);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
