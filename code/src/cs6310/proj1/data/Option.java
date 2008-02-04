/**
 * Option Class CS6310 Project 1
 * 
 * $Id$
 * $Date$
 */
package cs6310.proj1.data;

/**
 * @author Alex
 * 
 */
public class Option {
	private int Dimension;
	private int maxIterations;
	private EdgeTemperature edgeTemperature;
	private float stopPrecison;
	
	
	public boolean parseArgs(String[] args) {
		return false;
	}


	public int getDimension() {
    	return Dimension;
    }


	public void setDimension(int dimension) {
    	Dimension = dimension;
    }


	public int getMaxIterations() {
    	return maxIterations;
    }


	public void setMaxIterations(int maxIterations) {
    	this.maxIterations = maxIterations;
    }


	public EdgeTemperature getEdgeTemperature() {
    	return edgeTemperature;
    }


	public void setEdgeTemperature(EdgeTemperature edgeTemperature) {
    	this.edgeTemperature = edgeTemperature;
    }


	public float getStopPrecison() {
    	return stopPrecison;
    }


	public void setStopPrecison(float stopPrecison) {
    	this.stopPrecison = stopPrecison;
    }
	

}
