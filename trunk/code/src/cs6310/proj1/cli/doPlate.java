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
	private float cellTemperatures[][];	

	public doPlate() {
		super();
	}
	
	public void setOption(Option option) {
		super.setOption(option);
		init();
		
		float [][]cellTemperatures = null;
		cellTemperatures = getCellTemperatures();
		notifyTemperatureChange(cellTemperatures);
	}
	
	private float[][] getCellTemperatures() {
		int dimension = option.getDimension();
		for (int i = 1; i <= dimension; i++) {
			for (int j = 1; j <= dimension; j++) {
				cellTemperatures[i - 1][j - 1] = (float)cells[i][j].getTemperature(); 
			}
		}
		return cellTemperatures;		
	}	
	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#compute()
	 */
	public boolean compute(long sleepMilliseconds) {
		boolean done;
		double stopPrecision = (double)option.getStopPrecison();
		int dimension = option.getDimension();
		int maxIterations = option.getMaxIterations();
		int iterationCount = 0;
		float [][]cellTemperatures;		
		double temperature;

		stopFlag = false;
		
		while (iterationCount < maxIterations && false == stopFlag) {
			done = true;			
			for (int i = 1; i <= dimension; i++) {
				for (int j = 1; j <= dimension; j++) {
					
					temperature = cells[i][j].computeAndReturnTemp();			
					newCells[i][j].setTemperature(temperature);
					if (true == done && stopPrecision < (temperature - cells[i][j].getTemperature())) {
					//if (stopPrecision < (newCells[i][j].getTemperature() - cell.getTemperature())) {
						done = false;
					}
				}
			}
			swap();
			iterationCount++;
			
			cellTemperatures = getCellTemperatures();
			notifyTemperatureChange(cellTemperatures);
			
			try {
				if (sleepMilliseconds > 0) { 
					Thread.sleep(sleepMilliseconds);
				}
            } catch (InterruptedException e) {
            }
			
				
			if (true == done) {
				break;
			}
			
		}
		return true;

	}

	private void swap() {
		DoubleCell [][]temp;
		
		temp = cells;
		cells = newCells;
		newCells = temp;
	}
	
	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#display()
	 */
	public void display() {
		DecimalFormat formatter = new DecimalFormat("00.000");
		int dimension = option.getDimension();
		
		for (int i = 1; i <= dimension; i++) {
			for (int j = 1; j <= dimension; j++) {
				System.out.print(formatter.format(cells[i][j].getTemperature()) + " ");
			}
			System.out.println();
		}
	}
	
	public void stop() {
		stopFlag = true;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Option option = new Option();
		boolean status = option.parseArgs(args);
		if (true == status) {
			doPlate plate = new doPlate();
			plate.setOption(option);
			plate.init();
			plate.compute(0);
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
		
		int dimension = option.getDimension();
		cellTemperatures = new float[dimension][dimension];
		
		for (int i = 0; i < arrayDimension; i++) {
			for (int j = 0; j < arrayDimension; j++) {
				cells[i][j] = new DoubleCell();
				newCells[i][j] = new DoubleCell();
			}
		}
		
		EdgeTemperature edgeTemperature = option.getEdgeTemperature();
		
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[i][0].setTemperature((double)edgeTemperature.getLeft());
			cells[i][1].setTemperature((double)edgeTemperature.getLeft());			
			cells[i][arrayDimension - 1].setTemperature((double)edgeTemperature.getRight());
			cells[i][arrayDimension - 2].setTemperature((double)edgeTemperature.getRight());			
			
			newCells[i][0].setTemperature((double)edgeTemperature.getLeft());
			newCells[i][arrayDimension - 1].setTemperature((double)edgeTemperature.getRight());
		}
		
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[0][i].setTemperature((double)edgeTemperature.getTop());
			cells[1][i].setTemperature((double)edgeTemperature.getTop());			
			cells[arrayDimension - 1][i].setTemperature((double)edgeTemperature.getBottom());
			cells[arrayDimension - 2][i].setTemperature((double)edgeTemperature.getBottom());			
			
			newCells[0][i].setTemperature((double)edgeTemperature.getTop());
			newCells[arrayDimension - 1][i].setTemperature((double)edgeTemperature.getBottom());
		}		
		
		cells[1][1].setTemperature((edgeTemperature.getTop() + edgeTemperature.getLeft()) / 2);
		cells[1][arrayDimension - 2].setTemperature((edgeTemperature.getTop() + edgeTemperature.getRight()) / 2);
		cells[arrayDimension - 2][1].setTemperature((edgeTemperature.getBottom() + edgeTemperature.getLeft()) / 2);
		cells[arrayDimension - 2][arrayDimension - 2].setTemperature((edgeTemperature.getBottom() + edgeTemperature.getRight()) / 2);
		

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
	}

}
