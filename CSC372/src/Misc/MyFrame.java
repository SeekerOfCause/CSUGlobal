package Misc;
import javax.swing.*;

public class MyFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3409058492048852628L;

	public MyFrame() {
        // Set the frame title
        setTitle("My Frame");

        // Set the frame size
        setSize(400, 300);

        // Set the frame to appear in the center of the screen
        setLocationRelativeTo(null);

        // Set the frame to exit the application when closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold components
        JPanel panel = new JPanel();

        // Add a label to the panel
        JLabel label = new JLabel("Hello, World!");
        panel.add(label);

        // Add the panel to the frame
        add(panel);
    }

    public static void main(String[] args) {
        // Create a new instance of the frame
        MyFrame frame = new MyFrame();

        // Set the frame to be visible
        frame.setVisible(true);
    }
}

