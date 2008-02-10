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
	protected Option option;
	protected boolean stopFlag;
	
	public Plate() {
		stopFlag = false;
	}
	
	public abstract boolean compute();
	public void stop() {
		stopFlag = true;
	}
	public abstract void display();
	public abstract void init();
	
	public void setOption(Option option) {
		this.option = option;
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
