package cs6310.proj1.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CellPanel extends JPanel {

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.ORANGE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
	}

}
