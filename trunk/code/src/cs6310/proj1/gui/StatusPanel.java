package cs6310.proj1.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;



public class StatusPanel extends JPanel {
	JTextArea messageArea;
	JSeparator separator;
	
	public StatusPanel() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new BorderLayout(5, 5));

		separator = new JSeparator();
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		messageArea.setBackground(getBackground());
		
		add(separator, BorderLayout.NORTH);
		add(messageArea, BorderLayout.CENTER);
		
	}
}
