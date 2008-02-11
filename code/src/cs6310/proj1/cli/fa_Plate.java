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
public class fa_Plate extends ArrayPlate {

	private float cells[][];
	private float newCells[][];
	
	public fa_Plate() {
		super();
	}


	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#compute()
	 */
	public boolean compute(long sleepMilliseconds) {
		// TODO Auto-generated method stub
		boolean done;
		double stopPrecision = option.getStopPrecison();
		int dimension = option.getDimension();
		int maxIterations = option.getMaxIterations();
		int iterationCount = 0;
		
		while (iterationCount < maxIterations && false == stopFlag) {
			done = true;
			for (int i = 1; i <= dimension; i++) {
				for (int j = 1; j <= dimension; j++) {
					newCells[i][j] = (cells[i][j - 1] + cells[i][j + 1] + 
									  cells[i - 1][j] + cells[i + 1][j]) / 4.0f;
					//if (true == done && stopPrecision < (newCells[i][j] - cells[i][j])) { -- can be faster
					if (stopPrecision < (newCells[i][j] - cells[i][j])) {
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
		float [][]temp;
		
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

	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#init()
	 */
	public void init() {
		// TODO Auto-generated method stub
		int arrayDimension = option.getDimension() + 2;
		
		cells = new float[arrayDimension][arrayDimension];
		newCells = new float[arrayDimension][arrayDimension];
		
		EdgeTemperature edgeTemperature = option.getEdgeTemperature();
		
		/*
		 * dont have to initialize newCells?
		 */
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[i][0] = edgeTemperature.getLeft();
			cells[i][arrayDimension - 1] = edgeTemperature.getRight();
			
			newCells[i][0] = edgeTemperature.getLeft();
			newCells[i][arrayDimension - 1] = edgeTemperature.getRight();
		}
		
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[0][i] = edgeTemperature.getTop();
			cells[arrayDimension - 1][i] = edgeTemperature.getBottom();
			
			newCells[0][i] = edgeTemperature.getTop();
			newCells[arrayDimension - 1][i] = edgeTemperature.getBottom();
		}		
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Option option = new Option();
		boolean status = option.parseArgs(args);
		if (true == status) {
			fa_Plate plate = new fa_Plate();
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

}
