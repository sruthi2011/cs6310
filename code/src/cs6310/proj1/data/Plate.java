/**
 * Plate Class CS6310 Project 1
 * 
 * $Id$
 * $Date$
 */
package cs6310.proj1.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Alex
 *
 */
public abstract class Plate {
	protected Option option;
	protected boolean stopFlag;
	
	protected Collection listeners;
	
	public Plate() {
		stopFlag = false;
		listeners = new HashSet();
	}
	
	public abstract boolean compute(long sleepMilliseconds);
	public void stop() {
		stopFlag = true;
	}
	public abstract void display();
	public abstract void init();
	protected abstract float[][] getGuiDisplayData();
	
	public void setOption(Option option) {
		this.option = option;
	}
	
	public void addListener(PlateListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException("listener can't be null.");
		}
		
		listeners.add(listener);
	}
	
	public void removeListener(PlateListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException("listener can't be null.");
		}
		
		listeners.remove(listener);
	}
	
	
	protected void notifyTemperatureChange(float[][] temperatures) {
		for (Iterator i = listeners.iterator(); i.hasNext();) {
			PlateListener l = (PlateListener) i.next();
			
			l.temperatureChanged(temperatures);
		}
	}
}
