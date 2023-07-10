package CriticalThinking.Module2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BankAppFrame extends JFrame implements ActionListener{

		JFrame topFrame = null; // Application window
		GridBagConstraints layoutConst = null; // GUI component layout
		JTextArea display = null; // GUI text display area
		JButton b1;
		JButton b2;
		JButton b3;
		JButton b4;
		

		BankAppFrame() {
		b1 = new JButton("Create an Account");
		b1.setSize(10, 5);
		b1.addActionListener(this);
		
		b2 = new JButton("Deposit Funds");
		b2.setSize(15, 5);
		b3 = new JButton("Withdraw Funds");
		b3.setSize(15, 5);
		b4 = new JButton("Exit");
		b4.setSize(5, 3);

		display = new JTextArea();
		display.setText("Welcome to the banking menu\n\nWhere would you like to start?\n\n\n\n\n\n\n");

		// Create frame and add components using GridBagLayout
		topFrame = new JFrame("Salary");

		topFrame.setSize(500, 500);

		// Use a GridBagLayout
		topFrame.setLayout(new GridBagLayout());

		

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.weightx = 0.0;
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.anchor = GridBagConstraints.WEST;
		layoutConst.insets = new Insets(10, 10, 10, 10);
		topFrame.add(b1, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 2;
		layoutConst.weightx = 0.0;
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.anchor = GridBagConstraints.WEST;
		layoutConst.insets = new Insets(10, 10, 10, 10);
		topFrame.add(b2, layoutConst);
		
		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 3;
		layoutConst.weightx = 0.0;
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.anchor = GridBagConstraints.WEST;
		layoutConst.insets = new Insets(10, 10, 10, 10);
		topFrame.add(b3, layoutConst);
		
		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 0;
		layoutConst.gridy = 4;
		layoutConst.weightx = 0.0;
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.anchor = GridBagConstraints.WEST;
		layoutConst.insets = new Insets(10, 10, 10, 10);
		topFrame.add(b4, layoutConst);
		
		layoutConst = new GridBagConstraints();
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		layoutConst.gridheight = GridBagConstraints.REMAINDER;
		layoutConst.fill = GridBagConstraints.VERTICAL;
		layoutConst.insets = new Insets(10, 10, 10, 10);
		topFrame.add(display, layoutConst);

		// Terminate program when window closes
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display window
		
		}
		
		public void run() {
			this.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
