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

	public double computeTemperature() {
		
		double neighborSum = 0.0;
		Iterator iterator = neighbors.iterator();
		
		while (iterator.hasNext()) {
			neighborSum += ((DoubleCell)iterator.next()).getTemperature(); 
		}
		return (neighborSum / 4.0);
	}
}
