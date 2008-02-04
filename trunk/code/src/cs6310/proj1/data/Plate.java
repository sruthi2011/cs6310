/**
 * Plate Class CS6310 Project 1
 * 
 * $Id$
 * $Date$
 */
package cs6310.proj1.data;

/**
 * @author Alex
 *
 */
public abstract class Plate {
	public abstract boolean compute();
	public abstract void stop();
	public abstract void display();
	
	public void setOption(Option option) {

	}
	
	public void addListener(PlateListener listener) {
		
	}
	
	public void removeListener(PlateListener listener) {
		
	}
	
	
	protected void notifyOptionChange() {
	
	}
	
	protected void notifyTemperatureChange() {
		
	}
}
