package cs6310.proj1.gui;

import cs6310.proj1.cli.daPlate;
import cs6310.proj1.data.Option;
import cs6310.proj1.data.Plate;

public class GUIModel {
	private Option option;
	private Plate plate;
	private float[][] temperatures;
	
	public float[][] getTemperatures() {
    	return temperatures;
    }

	public void setTemperatures(float[][] temperatures) {
		if (temperatures == null) {
			throw new IllegalArgumentException("temperatures can't be null.");
		}
		
    	this.temperatures = temperatures;
    }

	public GUIModel() {
		option = new Option();
		plate = new daPlate(); //Default Plate;
	}
	
	public Option getOption() {
    	return option;
    }


	public void setOption(Option option) {
    	if (option == null) {
    		throw new IllegalArgumentException("option can't be null.");
    	}
    	
		this.option = option;
    }


	public Plate getPlate() {
    	return plate;
    }


	public void setPlate(Plate plate) {
		if (plate == null) {
    		throw new IllegalArgumentException("option can't be null.");
    	}
		
    	this.plate = plate;
    }
	
}
