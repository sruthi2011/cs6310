/**
 * Alex
 * Feb 4, 2008
 */
package cs6310.proj1.cli;

import java.text.DecimalFormat;

import cs6310.proj1.data.*;

/**
 * @author Alex
 *
 */
public class daPlate extends ArrayPlate {
	
	private double cells[][];
	private double newCells[][];
	
	public daPlate() {
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
		
		// TODO Auto-generated method stub
		int dimension = option.getDimension();
		float[][] cellTemperatures = new float[dimension][dimension];
		for (int i = 1; i <= dimension; i++) {
			for (int j = 1; j <= dimension; j++) {
				cellTemperatures[i - 1][j - 1] = (float) cells[i][j]; 
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
		
		while (iterationCount < maxIterations && false == stopFlag) {
			done = true;
			for (int i = 1; i <= dimension; i++) {
				for (int j = 1; j <= dimension; j++) {
					newCells[i][j] = (cells[i][j - 1] + cells[i][j + 1] + 
									  cells[i - 1][j] + cells[i + 1][j]) / 4.0;
					//if (true == done && stopPrecision < (newCells[i][j] - cells[i][j])) { -- can be faster
					if (stopPrecision < (newCells[i][j] - cells[i][j])) {
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
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 
	 */
	private void swap() {
		double temp [][];
		temp = cells;
		cells = newCells;
		newCells = temp;
		/*
		int dimension = option.getDimension();
		for (int i = 1; i <= dimension; i++) {
			for (int j = 1; j <= dimension; j++) {
				cells[i][j] = newCells[i][j];
			}
		}
		*/
		// TODO Auto-generated method stub
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
				System.out.print(formatter.format(newCells[i][j]) + " ");
			}
			System.out.println();
		}

	}

	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#stop()
	 */
	public void stop() {
		// TODO Auto-generated method stub
		stopFlag = true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Option option = new Option();
		boolean status = option.parseArgs(args);
		if (true == status) {
			daPlate plate = new daPlate();
			plate.setOption(option);
			plate.init();
			plate.compute(0);
			plate.display();
		}
		else {
			System.out.println("error parsing arguments.");
			System.out.println("Usage: <program_name> -d # -l # -r # -t # -b #");
		}
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */	
	public void init() {
		int arrayDimension = option.getDimension() + 2;
		
		cells = new double[arrayDimension][arrayDimension];
		newCells = new double[arrayDimension][arrayDimension];
		
		EdgeTemperature edgeTemperature = option.getEdgeTemperature();
		
		/*
		 * dont have to initialize newCells?
		 */
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[i][0] = edgeTemperature.getLeft();
			cells[i][1] = edgeTemperature.getLeft();
			cells[i][arrayDimension - 1] = edgeTemperature.getRight();
			cells[i][arrayDimension - 2] = edgeTemperature.getRight();
			
			newCells[i][0] = edgeTemperature.getLeft();
			newCells[i][1] = edgeTemperature.getLeft();
			newCells[i][arrayDimension - 1] = edgeTemperature.getRight();
			newCells[i][arrayDimension - 2] = edgeTemperature.getRight();
		}
		
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[0][i] = edgeTemperature.getTop();
			cells[1][i] = edgeTemperature.getTop();
			cells[arrayDimension - 1][i] = edgeTemperature.getBottom();
			cells[arrayDimension - 2][i] = edgeTemperature.getBottom();
			
			newCells[0][i] = edgeTemperature.getTop();
			newCells[1][i] = edgeTemperature.getTop();
			newCells[arrayDimension - 1][i] = edgeTemperature.getBottom();
			newCells[arrayDimension - 2][i] = edgeTemperature.getBottom();
		}		
		// TODO Auto-generated method stub
		
	}

}
