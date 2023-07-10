package CriticalThinking.Module2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MenuFrame extends JFrame implements ActionListener {

	// initiate components
	JFrame menu;
	GridBagConstraints layoutConst;
	JTextArea display;
	JButton b1, b2, b3, b4;
	JLabel menuTitle;

	// default constructor
	public void menuFrame() {

		int hourlyWage;             
	      JFrame topFrame = null;                // Application window
	      JLabel wageLabel = null;               // Label for hourly salary
	      JLabel salLabel = null;                // Label for yearly salary
	      JTextField salField = null;            // Displays hourly salary 
	      JTextField wageField = null;           // Displays yearly salary
	      GridBagConstraints layoutConst = null; // GUI component layout

	      hourlyWage = 20;

	      // Set hourly and yearly salary
	      wageLabel = new JLabel("Hourly wage:");
	      salLabel = new JLabel("Yearly salary:");

	      wageField = new JTextField(15);
	      wageField.setEditable(false);
	      wageField.setText(Integer.toString(hourlyWage));

	      salField = new JTextField(15);
	      salField.setEditable(false);
	      salField.setText(Integer.toString((hourlyWage * 40 * 50)));

	      // Create frame and add components using GridBagLayout
	      topFrame = new JFrame("Salary");
	      
	      // Use a GridBagLayout
	      topFrame.setLayout(new GridBagLayout());

	      // Create GridBagConstraints
	      layoutConst = new GridBagConstraints();
	      
	      // Specify component's grid location
	      layoutConst.gridx = 0;
	      layoutConst.gridy = 0;
	      
	      // 10 pixels of padding around component
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      
	      // Add component using the specified constraints
	      topFrame.add(wageLabel, layoutConst);

	      layoutConst = new GridBagConstraints();
	      layoutConst.gridx = 1;
	      layoutConst.gridy = 0;
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      topFrame.add(wageField, layoutConst);

	      layoutConst = new GridBagConstraints();
	      layoutConst.gridx = 0;
	      layoutConst.gridy = 1;
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      topFrame.add(salLabel, layoutConst);

	      layoutConst = new GridBagConstraints();
	      layoutConst.gridx = 1;
	      layoutConst.gridy = 1;
	      layoutConst.insets = new Insets(10, 10, 10, 10);
	      topFrame.add(salField, layoutConst);

	      // Terminate program when window closes
	      topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      // Resize window to fit components
	      topFrame.pack();
	      
	      // Display window
	      topFrame.setVisible(true);
	   
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
