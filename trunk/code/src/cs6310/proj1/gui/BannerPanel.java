package cs6310.proj1.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BannerPanel extends JPanel {
	public static final String TITILE = "Diffusion Simluation";
	public BannerPanel() {
		setPreferredSize(new Dimension(200, 80));
		setBorder(BorderFactory.createEmptyBorder());
	}
	
	
	public void paintComponent(Graphics g) {
		setBackground(Color.WHITE);
		super.paintComponent(g); //Paint the background

		
		Graphics2D g2d = (Graphics2D) g;

	    Rectangle bounds = getBounds();
	    Paint gradientPaint = new GradientPaint(
	    		0, 0, new Color(0, 153, 255),
	    		0, bounds.height, Color.WHITE);
	    g2d.setPaint(gradientPaint);
	    g2d.fillRect(0, 0, bounds.width, bounds.height);
	    
	    
		
		Map map = new Hashtable();
		map.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
		map.put(TextAttribute.SIZE, new Integer(30));
		map.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		
		
		Font font = getFont().deriveFont(map);
		g2d.setFont(font);
		
		FontMetrics metrics = g.getFontMetrics(font);
	    int hgt = metrics.getHeight();
	    int adv = metrics.stringWidth(TITILE);
	    Dimension titleSize = new Dimension(adv+2, hgt+2);
	    
		
		//To center the image.
		float leftOffset = (getWidth() - titleSize.width) / 2;
		float topOffset = (bounds.height - titleSize.height) / 2 + metrics.getAscent();
		
		g2d.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		g2d.setPaint(new Color(0, 0, 0, 100));;
		g2d.drawString(TITILE, leftOffset + 2, topOffset + 2);
		
		g2d.setPaint(Color.WHITE);
		g2d.drawString(TITILE, leftOffset, topOffset);
		
	}
}
