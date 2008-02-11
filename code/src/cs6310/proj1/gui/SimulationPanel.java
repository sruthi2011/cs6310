/**
 * The panel which contains the cell panel and temperature controls.
 * Alex
 * Feb 7, 2008
 */

package cs6310.proj1.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SimulationPanel extends JPanel {
	private CellPanel cellPanel;
	private JSlider topSlider;
	private JSlider bottomSlider;
	private JSlider rightSlider;
	private JSlider leftSlider;
	
	public SimulationPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
	
		this.setPreferredSize(new Dimension(400, 400));
		

		
		topSlider = new JSlider(0, 100, 50);
		topSlider.setOrientation(JSlider.HORIZONTAL);
		initSlider(topSlider);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(topSlider, c);
		
		leftSlider = new JSlider();
		leftSlider.setOrientation(JSlider.VERTICAL);
		initSlider(leftSlider);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 5, 5, 5);
		add(leftSlider, c);
		
		bottomSlider = new JSlider();
		bottomSlider.setOrientation(JSlider.HORIZONTAL);
		initSlider(bottomSlider);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 5, 5);
		add(bottomSlider, c);

		rightSlider = new JSlider();
		rightSlider.setOrientation(JSlider.VERTICAL);
		initSlider(rightSlider);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 5, 5);
		add(rightSlider, c);

		cellPanel = new CellPanel();
		cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(0, 0, 5, 5);
		add(cellPanel, c);
		
	}
	
	private void initSlider(JSlider slider) {
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
	}
	
}
