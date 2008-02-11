/**
 * 
 */
package cs6310.proj1.cli;

import java.text.DecimalFormat;
import java.util.Vector;

import cs6310.proj1.data.DoubleCell;
import cs6310.proj1.data.EdgeTemperature;
import cs6310.proj1.data.ObjectPlate;
import cs6310.proj1.data.Option;

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
		double temperature;
		DoubleCell cell;
		
		while (iterationCount < maxIterations && false == stopFlag) {
			done = true;
			
			for (int i = 1; i <= dimension; i++) {
				for (int j = 1; j <= dimension; j++) {
					temperature = 0.0;
					cell = cells[i][j];
					
					cell.initNeighborIterator();					
					while (cell.hasMoreNeighbors()) {
						temperature += cell.getNextNeighborsTemp();
					}
					
					newCells[i][j].setTemperature(temperature / cell.getNoOfNeighbors());
					//if (true == done && stopPrecision < (newCells[i][j] - cells[i][j])) { -- can be faster
					if (stopPrecision < (newCells[i][j].getTemperature() - cell.getTemperature())) {
						done = false;
					}
				}
			}
			swap();
			iterationCount++;
				
			if (true == done) {
				break;
			}
			
		}
		// TODO Auto-generated method stub
		return true;

	}

	private void swap() {
		// TODO Auto-generated method stub
		DoubleCell [][]temp;
		
		temp = cells;
		cells = newCells;
		newCells = temp;
		
		/*
		int dimension = option.getDimension();
		for (int i = 1; i <= dimension; i++) {
			for (int j = 1; j <= dimension; j++) {
				cells[i][j].setTemperature(newCells[i][j].getTemperature());
			}
		}
		*/		
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
		Option option = new Option();
		boolean status = option.parseArgs(args);
		if (true == status) {
			doPlate plate = new doPlate();
			plate.setOption(option);
			plate.init();
			plate.compute();
			plate.display();
		}
		else {
			System.out.println("error parsing arguments.");
			System.out.println("Usage: <program_name> -d # -l # -r # -t # -b #");
		}		

	}

	public void init() {
		int arrayDimension = option.getDimension() + 2;
		
		cells = new DoubleCell[arrayDimension][arrayDimension];
		newCells = new DoubleCell[arrayDimension][arrayDimension];
		
		for (int i = 0; i < arrayDimension; i++) {
			for (int j = 0; j < arrayDimension; j++) {
				cells[i][j] = new DoubleCell();
				newCells[i][j] = new DoubleCell();
			}
		}
		
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
