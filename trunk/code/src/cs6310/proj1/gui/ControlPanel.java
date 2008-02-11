/**
 * The panel that sit at the top and provide controls.
 * Alex
 * Feb 7, 2008
 */

package cs6310.proj1.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cs6310.proj1.cli.FaPlate;
import cs6310.proj1.cli.daPlate;
import cs6310.proj1.cli.doPlate;
import cs6310.proj1.data.Plate;


public class ControlPanel extends JPanel
	implements ItemListener, DocumentListener, ActionListener {
	
	private JLabel simModelLabel;
	private JLabel dimensionLabel;
	private JTextField maxIterTextField;
	private JButton controlButton;
	private JTextField dimensionTextField;
	private JComboBox simModelComboBox;
	private JLabel maxIterLabel;
	private ImageIcon startIcon;
	private ImageIcon stopIcon;
	
	private JTextArea messageTextArea;
	
	private GUIModel guiModel;
	public ControlPanel(GUIModel model) {
		if (model == null) {
			throw new IllegalArgumentException("model can not be null.");
		}
		
		guiModel = model;
		
		initialize();
	}

	private void initialize() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		setPreferredSize(new Dimension(560, 88));
		//Type Label
		simModelLabel = new JLabel();
		simModelLabel.setText("Simluation Model:");
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(5, 5, 5, 5);
		add(simModelLabel, c);

		//Type ComboBox
		ComboBoxModel typeComboBoxModel = 
			getSimModelComboModel(guiModel.getPlate());
		simModelComboBox = new JComboBox(typeComboBoxModel);
		simModelComboBox.setPreferredSize(new Dimension(150, 20));
		simModelComboBox.addItemListener(this);
		
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.2;
		c.insets = new Insets(0, 5, 5, 5);
		add(simModelComboBox, c);
		
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
		dimensionTextField = new JTextField();
		dimensionTextField.setPreferredSize(new Dimension(100, 20));
		dimensionTextField.setText(
			String.valueOf(guiModel.getOption().getDimension()));
//		dimensionTextField.addFocusListener(this);
		dimensionTextField.getDocument().addDocumentListener(this);
		
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
		maxIterLabel = new JLabel();
		maxIterLabel.setText("Max Iteration:");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(5, 0, 5, 5);
		add(maxIterLabel, c);
		
		//Step Limit TextField
		maxIterTextField = new JTextField();
		maxIterTextField.setPreferredSize(new Dimension(100, 20));
		maxIterTextField.setText(
			String.valueOf(guiModel.getOption().getMaxIterations()));
		//maxIterTextField.addFocusListener(this);
		maxIterTextField.getDocument().addDocumentListener(this);
		
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.2;
		c.insets = new Insets(0, 0, 5, 5);
		add(maxIterTextField, c);
		
		//Control Button
		controlButton = new JButton();
		controlButton.setPreferredSize(new Dimension(80, 25));
		controlButton.addActionListener(this);
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
		messageTextArea.setEditable(false);
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
	
	private ComboBoxModel getSimModelComboModel(Plate currentPlate) {
		assert currentPlate != null;
		
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement("daPlate");
		model.addElement("doPlate");
		model.addElement("faPlate");
		model.addElement("FaPlate");
		
		if (currentPlate instanceof daPlate) {
			model.setSelectedItem("daPlate");
		} else if (currentPlate instanceof doPlate) {
			model.setSelectedItem("daPlate");
		} else if (currentPlate instanceof FaPlate) {
			model.setSelectedItem("FaPlate");
		}
		
		return model;
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

	public void itemStateChanged(ItemEvent e) {
	    if (e.getSource().equals(simModelComboBox)) {
	    	if (e.getStateChange() == ItemEvent.SELECTED) {
	    		if ("daPlate".equals(e.getItem())) {
	    			guiModel.setPlate(new daPlate());	    			
	    		} else if ("doPlate".equals(e.getItem())) {
	    			guiModel.setPlate(new doPlate());
	    		} else if ("FaPlate".equals(e.getItem())) {
	    			guiModel.setPlate(new FaPlate());
	    		} else if ("faPlate".equals(e.getItem())) {
	    			guiModel.setPlate(new FaPlate());
	    		}
	    		
	    		assert false : "Unknow simluation model";
	    	}
	    }
    }

	
	public void displayErrorMessage(String errorMsg) {
		if (errorMsg == null || errorMsg.isEmpty()) {
			messageTextArea.setText("");
		}
		
		messageTextArea.setForeground(Color.RED);
		messageTextArea.setText(errorMsg);
	}
	

	public void changedUpdate(DocumentEvent e) {
    }

	public void insertUpdate(DocumentEvent e) {
		boolean valid = validateInput();
		controlButton.setEnabled(valid);
		
		if (valid) {
			updateOption();
		}
		
    }

	public void removeUpdate(DocumentEvent e) {
		boolean valid = validateInput();
		controlButton.setEnabled(valid);
		
		if (valid) {
			updateOption();
		}
    }

	
	private boolean validateInput() {
		String errMsg = null;
		
		try {
            int dim = Integer.parseInt(dimensionTextField.getText());
            
            if (dim < 0 || dim > 200) {
            	errMsg = "Dimension must be in range [0, 200].";
            }
        } catch (NumberFormatException e1) {
        	errMsg = "Dimension must be a number in range [0, 200].";
        	
        }

        try {
            int iter = Integer.parseInt(maxIterTextField.getText());
            
            if (iter < 1) {
            	errMsg = "Max Iteration must be greater than 1.";
            }
        } catch (NumberFormatException e1) {
        	errMsg = "Max Iteration must be a number greater than 1.";
        	
        }

        displayErrorMessage(errMsg);
        
		if (errMsg == null || errMsg.isEmpty()) {
			
			return true;
		} else {
			return false;
		}
	}
	
	private void updateOption() {
		guiModel.getOption().setDimension(
				Integer.parseInt(dimensionTextField.getText()));
		guiModel.getOption().setMaxIterations(
				Integer.parseInt(maxIterTextField.getText()));
		
		guiModel.getPlate().setOption(guiModel.getOption());
	}

	public void actionPerformed(ActionEvent e) {
		SwingWorker worker = new SwingWorker() {

			protected Object doInBackground() throws Exception {
	            guiModel.getPlate().compute(10);
	            return null;
            }
			
		};
		
		worker.execute();
    }

}
