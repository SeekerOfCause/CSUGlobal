package CriticalThinking.Module3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;

public class Main extends Application {

	// Create required variables
	int greenInt = 0;
	VBox vbox = new VBox();
	TextArea text = new TextArea();
	String textStr = new String();
	String dir = System.getProperty("user.dir");
	String strDate;

	@Override
	public void start(Stage stage) {

		// Create new menu
		Menu menu = new Menu("Best Menu Ever!");

		// Create menu bar
		MenuBar menuBar = new MenuBar();

		// Create menu items
		MenuItem getDate = new MenuItem("Get Date");
		MenuItem logDate = new MenuItem("Log Date");
		MenuItem backgroundChange = new MenuItem("Change Background");
		MenuItem exit = new MenuItem("Exit");

		// Add menu items to menu
		menu.getItems().addAll(getDate, logDate, backgroundChange, exit);

		// add menu to menu bar
		menuBar.getMenus().add(menu);

		Alert conf = new Alert(AlertType.NONE);

		// creates an instance of Random() for a random number
		Random randNum = new Random();

		// Event handler for getDate to call date/time and display in text area
		EventHandler<ActionEvent> getDateEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent b) {
				Date date = Calendar.getInstance().getTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy,  hh:mm:ss");
				strDate = dateFormat.format(date);
				text.setText("Current Date and Time:\n\n" + strDate);
			}
		};

		// Set event to getDate
		getDate.setOnAction(getDateEvent);

		// Event handler for logDate to log date/time
		EventHandler<ActionEvent> confLogDateEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent c) {

				try {

					// Check if text is empty
					if (text.getText().isEmpty()) {
						text.setText("");
					}
					// Clear text area if previous message is displayed
					else if (text.getText().equals("Date and Time logged.")) {
						text.setText("");
					} else {
						try {
							conf.setAlertType(AlertType.CONFIRMATION);
							conf.setTitle("Log Date?");
							conf.setHeaderText("CONFIRM: Log this date?");
							conf.setContentText(text.getText());
							Optional<ButtonType> res = conf.showAndWait();

							if (!res.isPresent()) {

							} else if (res.get() == ButtonType.OK) {
								// Create log.txt file if not created already
								File logTxt = new File(dir + "\\log.txt");
								logTxt.createNewFile();

								// use FileWiter to append data to file
								FileWriter fr = new FileWriter(logTxt, true);
								fr.write(text.getText() + "\n\n ------------------\n\n");
								fr.close();
								text.setText("Date and Time logged.");
							} else if (res.get() == ButtonType.CANCEL) {
								text.setText("");
								conf.close();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							text.setText("Unable to write to file");
						}
					}
					// display error if unable to write file
				} catch (Exception e) {
					text.setText("Couldn't create file");
				}

			}

		};

		// Set event to logDate
		logDate.setOnAction(confLogDateEvent);

		// Add event handler for backgroundChange to change background color of VBox
		// using setBackground()
		EventHandler<ActionEvent> backgroundChangeEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent d) {

				// Get random int from 1-250 to use as green value in Color.rgb
				greenInt = randNum.nextInt(250);
				// Set VBox background to new background with a new background fill of color
				// equal to int value greenInt
				vbox.setBackground(new Background(new BackgroundFill(Color.rgb(0, greenInt, 0), null, null)));
			}
		};

		// Event to exit program when called
		EventHandler<ActionEvent> exitEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent d) {
				System.exit(0);
			}
		};

		exit.setOnAction(exitEvent);

		// Set event to backgroundChange
		backgroundChange.setOnAction(backgroundChangeEvent);

		// Set text area as a non-editable field with set size
		text.setEditable(false);
		text.setMaxHeight(70);
		text.setPrefWidth(150);

		// Create HBox for TextArea
		HBox hboxTop = new HBox();
		hboxTop.setPadding(new Insets(40));
		hboxTop.getChildren().add(text);
		hboxTop.setAlignment(Pos.CENTER);
		hboxTop.setPrefHeight(200);
		hboxTop.setPrefWidth(100);

		// Create a VBox to hold the hboxTop
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setSpacing(10);
		vbox.getChildren().addAll(menuBar, hboxTop);

		// Create a Scene with VBox at set width and height
		Scene scene = new Scene(vbox, 450, 250);

		// Set title for stage and show stage
		stage.setTitle("Whatever this is...");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
