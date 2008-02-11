/**
 * The main panel which glues everything together.
 * Alex
 * Feb 7, 2008
 */

package cs6310.proj1.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MainPanel extends JPanel {
	ControlPanel controlPanel;
	SimulationPanel simulationPanel;
	StatusPanel	statusPanel;
	
	public MainPanel() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new BorderLayout());
		
		controlPanel = new ControlPanel();
		add(controlPanel, BorderLayout.NORTH);
		
		simulationPanel = new SimulationPanel();
		add(simulationPanel, BorderLayout.CENTER);
		
		statusPanel = new StatusPanel();
		add(statusPanel, BorderLayout.SOUTH);	
	}
	
	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
	        e.printStackTrace();
        }
        
		JFrame testFrame = new JFrame("Test");
		JPanel p = new MainPanel();
		testFrame.setContentPane(p);
		
		testFrame.pack();
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
}
