/**
 * 
 */
package cs6310.proj1.data;

import java.util.Iterator;
import java.util.Vector;
/**
 * @author Nagesh
 *
 */
public abstract class Cell {
	protected Vector neighbors;
	protected Iterator iterator;
	
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
	
	public void initNeighborIterator() {
		iterator = neighbors.iterator();
	}
	
	public boolean hasMoreNeighbors() {
		return iterator.hasNext();
	}
	
	public int getNoOfNeighbors() {
		return neighbors.size();
	}
	
}
