/**
 * The main class file.
 * Alex
 * Feb 7, 2008
 */
package cs6310.proj1.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * @author Alex
 *
 */
public class GUIPlate {
	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
	        e.printStackTrace();
	        return;
        }
        
		JFrame testFrame = new JFrame("Diffusion Simulation");
		JPanel mainPanel = new MainPanel();
		testFrame.setContentPane(mainPanel);
		testFrame.setMinimumSize(new Dimension(600, 700));
		testFrame.setPreferredSize(new Dimension(600, 700));
		
		
		testFrame.pack();
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
