/**
 * $Id$
 * $Date$
 */
package cs6310.proj1.data;

/**
 * @author Alex
 * 
 */
public interface PlateListener {
	public void optionChanged(Option newOption);
	public void temperatureChanged(float[] temperatures);

}
