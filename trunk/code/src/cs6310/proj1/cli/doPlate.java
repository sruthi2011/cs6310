/**
 * 
 */
package cs6310.proj1.cli;

import java.text.DecimalFormat;
import java.util.Vector;

import cs6310.proj1.data.DoubleCell;
import cs6310.proj1.data.EdgeTemperature;

/**
 * @author Nagesh
 *
 */
public class doPlate extends ObjectPlate {

	private DoubleCell cells[][];
	private DoubleCell newCells[][];

	public doPlate() {
		super();
	}
	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#compute()
	 */
	public boolean compute() {
		// TODO Auto-generated method stub
		
		boolean done;
		double stopPrecision = (double)option.getStopPrecison();
		int dimension = option.getDimension();
		int maxIterations = option.getMaxIterations();
		int iterationCount = 0;
		
		while (iterationCount < maxIterations && false == stopFlag) {
			done = true;
			//double temperature;
			/*
			for (int i = 1; i <= dimension; i++) {
				for (int j = 1; j <= dimension; j++) {
					newCells[i][j] = (cells[i][j - 1] + cells[i][j + 1] + 
									  cells[i - 1][j] + cells[i + 1][j]) / 4.0;
					//if (true == done && stopPrecision > (newCells[i][j] - cells[i][j])) { -- can be faster
					if (stopPrecision > (newCells[i][j] - cells[i][j])) {
						done = false;
					}
				}
			}
			updateCells();
			iterationCount++;
				
			if (true == done) {
				break;
			}
			*/
		}
		// TODO Auto-generated method stub
		return true;

	}

	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#display()
	 */
	public void display() {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("00.000");
		int dimension = option.getDimension();
		
		for (int i = 1; i <= dimension; i++) {
			for (int j = 1; j <= dimension; j++) {
				System.out.print(formatter.format(newCells[i][j].getTemperature()) + " ");
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void init() {
		int arrayDimension = option.getDimension() + 2;
		
		cells = new DoubleCell[arrayDimension][arrayDimension];
		newCells = new DoubleCell[arrayDimension][arrayDimension];
		
		EdgeTemperature edgeTemperature = option.getEdgeTemperature();
		
		/*
		 * dont have to initialize newCells?
		 */
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[i][0].setTemperature((double)edgeTemperature.getLeft());
			cells[i][arrayDimension - 1].setTemperature((double)edgeTemperature.getRight());
			
			newCells[i][0].setTemperature((double)edgeTemperature.getLeft());
			newCells[i][arrayDimension - 1].setTemperature((double)edgeTemperature.getRight());
		}
		
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[0][i].setTemperature((double)edgeTemperature.getTop());
			cells[arrayDimension - 1][i].setTemperature((double)edgeTemperature.getBottom());
			
			newCells[0][i].setTemperature((double)edgeTemperature.getTop());
			newCells[arrayDimension - 1][i].setTemperature((double)edgeTemperature.getBottom());
		}		
		// TODO Auto-generated method stub

		int dimension = option.getDimension();
		for (int i = 1; i <= dimension; i++) {
			for (int j = 1; j <= dimension; j++) {
				
				Vector neighbors = new Vector();
				neighbors.add(cells[i][j - 1]);
				neighbors.add(cells[i][j + 1]);
				neighbors.add(cells[i - 1][j]);
				neighbors.add(cells[i + 1][j]);				
				cells[i][j].setNeighbors(neighbors);
				
				neighbors = new Vector();
				neighbors.add(newCells[i][j - 1]);
				neighbors.add(newCells[i][j + 1]);
				neighbors.add(newCells[i - 1][j]);
				neighbors.add(newCells[i + 1][j]);				
				newCells[i][j].setNeighbors(neighbors);
			}			
		}

		
		// TODO Auto-generated method stub
		
	}

}
