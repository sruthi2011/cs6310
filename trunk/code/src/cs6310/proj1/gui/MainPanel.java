/**
 * The main panel which glues everything together.
 * Alex
 * Feb 7, 2008
 */

package cs6310.proj1.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import cs6310.proj1.data.PlateListener;

public class MainPanel extends JPanel implements PlateListener {
	ControlPanel controlPanel;
	StatusPanel	statusPanel;
	
	GUIModel guiModel;
	
	public MainPanel() {
		guiModel = new GUIModel();
		initialize();
		
		//Initialize the plate and get the initial temperature change
		guiModel.getPlate().addListener(this);
		guiModel.getPlate().setOption(guiModel.getOption());
	}
	
	private void initialize() {
		setLayout(new BorderLayout());
		
		controlPanel = new ControlPanel(guiModel);
		add(controlPanel, BorderLayout.CENTER);
		
		statusPanel = new StatusPanel();
		add(statusPanel, BorderLayout.SOUTH);	
	}


	public void temperatureChanged(float[][] temperatures) {
	    guiModel.setTemperatures(temperatures);
	    controlPanel.repaintPlate();
    }
	
}
