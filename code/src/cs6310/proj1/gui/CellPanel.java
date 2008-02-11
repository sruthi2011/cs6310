package cs6310.proj1.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CellPanel extends JPanel {
	private GUIModel guiModel;
	
	public CellPanel(GUIModel guiModel) {
	    if (guiModel == null) {
	    	throw new IllegalArgumentException("guiModel can't be null.");
	    }
	    
	    this.guiModel = guiModel;
    }
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		float temps[][] = guiModel.getTemperatures();
		if (temps == null) {
			return;
		}
		
		int dimension = guiModel.getOption().getDimension();
		int cellWidth = getWidth() / dimension;
		int cellHeight = getHeight() / dimension;
		
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				g2d.setColor(tempToColor(temps[y][x]));

				g2d.fillRect(
					x * cellWidth, y * cellHeight, cellWidth, cellHeight);
			}
		}		
		
	}
	
	
	private Color tempToColor(float temperature) {
		int red = (int) temperature * 255 / 100;
		Color c = new Color(red, 0, 0);
		return c;
	}

}
