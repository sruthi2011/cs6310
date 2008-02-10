/**
 * 
 */
package cs6310.proj1.data;

import java.util.Vector;
/**
 * @author Nagesh
 *
 */
public abstract class Cell {
	protected Vector neighbors;
	
	public Cell() {
		neighbors = new Vector();
	}
	
	public Vector getNeighbors() {
		return neighbors;
	}
	
	public void setNeighbors(Vector neighbors) {
		this.neighbors = neighbors;
	}
	
	public void addNeighbor(Cell neighbor) {
		neighbors.add(neighbor);
	}
	public abstract void calculateTemperature();
	
}
