/**
 * The panel which contains the cell panel and temperature controls.
 * Alex
 * Feb 7, 2008
 */

package cs6310.proj1.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class SimulationPanel extends JPanel {
	private JPanel cellPanel;
	private JSlider topSlider;
	private JSlider bottomSlider;
	private JSlider rightSlider;
	private JSlider leftSlider;
	
	public SimulationPanel() {
		initialize();
	}

	private void initialize() {
		try {
			setLayout(new GridBagLayout());
			this.setPreferredSize(new Dimension(400, 327));
			//START >>  rightSlider
			rightSlider = new JSlider();
			rightSlider.setOrientation(JSlider.VERTICAL);
			this.add(rightSlider, BorderLayout.EAST);
			//END <<  rightSlider
			//START >>  leftSlider
			leftSlider = new JSlider();
			leftSlider.setOrientation(JSlider.VERTICAL);
			this.add(leftSlider, BorderLayout.WEST);
			//END <<  leftSlider
			//START >>  topSlider
			topSlider = new JSlider();
			this.add(topSlider, BorderLayout.NORTH);
			//END <<  topSlider
			//START >>  bottomSlider
			bottomSlider = new JSlider();
			this.add(bottomSlider, BorderLayout.SOUTH);
			//END <<  bottomSlider
			//START >>  cellPanel
			cellPanel = new JPanel();
			this.add(cellPanel, BorderLayout.CENTER);
			//END <<  cellPanel
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
