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
import java.util.Dictionary;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SimulationPanel extends JPanel implements ChangeListener {
	private CellPanel cellPanel;
	private JSlider topSlider;
	private JSlider bottomSlider;
	private JSlider rightSlider;
	private JSlider leftSlider;
	
	private GUIModel guiModel;
	Dictionary sliderLabels;
	
	public SimulationPanel(GUIModel guiModel) {
	    if (guiModel == null) {
	    	throw new IllegalArgumentException("guiModel can't be null.");
	    }
	    
	    this.guiModel = guiModel;
		initialize();	    
    }

	private void initialize() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(400, 400));
		
		sliderLabels = new java.util.Hashtable();
		sliderLabels.put(new Integer(0), new JLabel("0 °C"));
		sliderLabels.put(new Integer(25), new JLabel("25 °C"));
		sliderLabels.put(new Integer(50), new JLabel("50 °C"));
		sliderLabels.put(new Integer(75), new JLabel("75 °C"));
		sliderLabels.put(new Integer(100), new JLabel("100 °C"));
		
		topSlider = new JSlider();
		topSlider.setOrientation(JSlider.HORIZONTAL);
		initSlider(topSlider);
		topSlider.setValue(
				(int) guiModel.getOption().getEdgeTemperature().getTop());
		topSlider.addChangeListener(this);
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
		leftSlider.setValue(
				(int) guiModel.getOption().getEdgeTemperature().getTop());
		leftSlider.addChangeListener(this);
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
		bottomSlider.setValue(
				(int) guiModel.getOption().getEdgeTemperature().getTop());
		bottomSlider.addChangeListener(this);
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
		rightSlider.setValue(
				(int) guiModel.getOption().getEdgeTemperature().getTop());
		rightSlider.addChangeListener(this);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 5, 5);
		add(rightSlider, c);

		cellPanel = new CellPanel(guiModel);
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
	
	public void startSimulation() {
		Runnable computation = new Runnable() {
			public void run() {
				guiModel.getPlate().compute(10);
            }
		};
		
		new Thread(computation).start();
	}
	
	public void stopSimulation() {
		guiModel.getPlate().stop();
	}
	
	public void setControlEnable(boolean enable) {
		topSlider.setEnabled(enable);
		leftSlider.setEnabled(enable);
		bottomSlider.setEnabled(enable);
		rightSlider.setEnabled(enable);
	}
	
	private void initSlider(JSlider slider) {
		slider.setMinimum(0);
		slider.setMaximum(100);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setBackground(Color.WHITE);
		slider.setLabelTable(sliderLabels);
	}

	public void repaintPlate() {
	    cellPanel.repaint();	    
    }

	public void stateChanged(ChangeEvent e) {
	    if (e.getSource().equals(leftSlider)) {
	    	guiModel.getOption().getEdgeTemperature().setLeft(
	    			leftSlider.getValue());
	    } else if (e.getSource().equals(topSlider)) {
	    	guiModel.getOption().getEdgeTemperature().setTop(
	    			topSlider.getValue());
	    } else if (e.getSource().equals(rightSlider)) {
	    	guiModel.getOption().getEdgeTemperature().setRight(
	    			rightSlider.getValue());
	    } else if (e.getSource().equals(bottomSlider)) {
	    	guiModel.getOption().getEdgeTemperature().setBottom(
	    			bottomSlider.getValue());
	    }
	    
	    guiModel.getPlate().setOption(guiModel.getOption());
    }
}
