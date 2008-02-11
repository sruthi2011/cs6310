/**
 * 
 */
package cs6310.proj1.cli;

import java.text.DecimalFormat;

import cs6310.proj1.data.*;

/**
 * @author Nagesh
 *
 */
public class FaPlate extends ArrayPlate {
	
	private Float cells[][];
	private Float newCells[][];
	
	public FaPlate() {
		super();
	}
	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#compute()
	 */
	public boolean compute(long sleepMilliseconds) {
		// TODO Auto-generated method stub
		boolean done;
		float stopPrecision = option.getStopPrecison();
		int dimension = option.getDimension();
		int maxIterations = option.getMaxIterations();
		int iterationCount = 0;
		
		while (iterationCount < maxIterations && false == stopFlag) {
			done = true;
			for (int i = 1; i <= dimension; i++) {
				for (int j = 1; j <= dimension; j++) {
					newCells[i][j] = new Float((cells[i][j - 1].floatValue() + cells[i][j + 1].floatValue() + 
									  cells[i - 1][j].floatValue() + cells[i + 1][j].floatValue()) / 4.0);
					//if (true == done && stopPrecision < (newCells[i][j] - cells[i][j])) { -- can be faster
					if (stopPrecision < (newCells[i][j].floatValue() - cells[i][j].floatValue())) {
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
		Float [][]temp;
		temp = cells;
		cells = newCells;
		newCells = temp;
		/*
		int dimension = option.getDimension();
		for (int i = 1; i <= dimension; i++) {
			for (int j = 1; j <= dimension; j++) {
				cells[i][j] = new Float(newCells[i][j].floatValue());
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
				System.out.print(formatter.format(newCells[i][j].floatValue()) + " ");
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
		// TODO Auto-generated method stub
		Option option = new Option();
		boolean status = option.parseArgs(args);
		if (true == status) {
			FaPlate plate = new FaPlate();
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
		// TODO Auto-generated method stub
		int arrayDimension = option.getDimension() + 2;
		
		cells = new Float[arrayDimension][arrayDimension];
		newCells = new Float[arrayDimension][arrayDimension];
		
		for (int i = 0; i < arrayDimension; i++) {
			for (int j = 0; j < arrayDimension; j++) {
				cells[i][j] = new Float(0.0);
				newCells[i][j] = new Float(0.0);
			}
		}
		
		EdgeTemperature edgeTemperature = option.getEdgeTemperature();
		
		/*
		 * dont have to initialize newCells?
		 */
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[i][0] = new Float(edgeTemperature.getLeft());
			cells[i][arrayDimension - 1] = new Float(edgeTemperature.getRight());
			
			newCells[i][0] = new Float(edgeTemperature.getLeft());
			newCells[i][arrayDimension - 1] = new Float(edgeTemperature.getRight());
		}
		
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[0][i] = new Float(edgeTemperature.getTop());
			cells[arrayDimension - 1][i] = new Float(edgeTemperature.getBottom());
			
			newCells[0][i] = new Float(edgeTemperature.getTop());
			newCells[arrayDimension - 1][i] = new Float(edgeTemperature.getBottom());
		}		
		// TODO Auto-generated method stub
				
	}

}
