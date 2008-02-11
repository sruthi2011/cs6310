package cs6310.proj1.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StatusPanel extends JPanel {
	JTextArea messageArea;
	public StatusPanel() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new FlowLayout());
		messageArea = new JTextArea();
		add(messageArea);
		
	}
}
