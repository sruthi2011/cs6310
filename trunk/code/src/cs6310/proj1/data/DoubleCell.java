/**
 * 
 */
package cs6310.proj1.data;

import java.util.Iterator;

/**
 * @author Nagesh
 *
 */
public class DoubleCell extends Cell {

	private double temperature;
	/**
	 * 
	 */
	public DoubleCell() {
		super();
		temperature = 0.0;
	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Cell#calculateTemperature()
	 */
	
	/*
	public void calculateTemperature() {
		double neighborSum = 0.0;
		Iterator iterator = neighbors.iterator();
		
		while (iterator.hasNext()) {
			neighborSum += ((DoubleCell)iterator.next()).getTemperature(); 
		}
		temperature = neighborSum / 4.0;
	}
	
	
	public double getNextNeighborsTemp() {
		return ((DoubleCell)iterator.next()).getTemperature();
	}
	*/
	
	public double computeAndReturnTemp() {
		
		double neighborSum = 0.0;
		Iterator iterator = neighbors.iterator();
		
		while (iterator.hasNext()) {
			neighborSum += ((DoubleCell)iterator.next()).getTemperature(); 
		}
		return (neighborSum / 4.0);
		
	}
}
