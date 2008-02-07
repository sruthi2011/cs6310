/**
 * The panel that sit at the top and provide controls.
 * Alex
 * Feb 7, 2008
 */

package cs6310.proj1.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class ControlPanel extends JPanel {
	private JLabel typeLabel;
	private JLabel dimensionLabel;
	private JTextField stepLimitTextField;
	private JButton controlButton;
	private JTextField dimensionTextField;
	private JComboBox typeComboBox;
	private JLabel stepLimitLabel;
	private ImageIcon startIcon;
	private ImageIcon stopIcon;
	
	private JTextArea messageTextArea;
	
	public ControlPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setPreferredSize(new Dimension(560, 88));
		//Type Label
		typeLabel = new JLabel();
		typeLabel.setText("Type:");
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(typeLabel, c);

		//Type ComboBox
		ComboBoxModel typeComboBoxModel = 
			new DefaultComboBoxModel(
					new String[] { "Item One", "Item Two" });
		typeComboBox = new JComboBox(typeComboBoxModel);
		typeComboBox.setPreferredSize(new Dimension(150, 20));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.2;
		c.insets = new Insets(0, 5, 5, 5);
		add(typeComboBox, c);
		
		//Dimension Label
		dimensionLabel = new JLabel();
		dimensionLabel.setText("Dimension:");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(5, 0, 5, 5);
		add(dimensionLabel, c);

		//Dimension TextField
		dimensionTextField = new JTextField("20");
		dimensionTextField.setPreferredSize(new Dimension(100, 20));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.2;
		c.insets = new Insets(0, 0, 5, 5);
		add(dimensionTextField, c);
		
		//Step Limit Label
		stepLimitLabel = new JLabel();
		stepLimitLabel.setText("Step Limit:");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(5, 0, 5, 5);
		add(stepLimitLabel, c);
		
		//Step Limit TextField
		stepLimitTextField = new JTextField("10000");
		stepLimitTextField.setPreferredSize(new Dimension(100, 20));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.2;
		c.insets = new Insets(0, 0, 5, 5);
		add(stepLimitTextField, c);
		
		//Control Button
		controlButton = new JButton();
		controlButton.setPreferredSize(new Dimension(80, 25));
		URL startUrl =  getClass().getResource("res/start.png");
		startIcon = new ImageIcon(startUrl);
		URL stopUrl = getClass().getResource("res/stop.png");
		stopIcon = new ImageIcon(stopUrl);
		toggleControlBtn();
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.4;
		c.insets = new Insets(0, 5, 5, 5);
		add(controlButton, c);

		//Separator
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.insets = new Insets(0, 5, 5, 5);
		add(new JSeparator(JSeparator.HORIZONTAL), c);
		
		
		//Message Text Area
		messageTextArea = new JTextArea();
		messageTextArea.setBackground(getBackground());
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 0.5;
		c.insets = new Insets(0, 5, 5, 5);
		add(messageTextArea, c);
			
	}

	private void toggleControlBtn() {
		if (controlButton.getText() != "Start") {
			controlButton.setText("Start");
			controlButton.setIcon(startIcon);
		} else {
			controlButton.setText("Stop");
			controlButton.setIcon(stopIcon);
		}
	}
	
	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
	        e.printStackTrace();
        }
        
		JFrame testFrame = new JFrame("Test");
		JPanel p = new ControlPanel();
		testFrame.setContentPane(p);
		
		testFrame.pack();
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	


}
