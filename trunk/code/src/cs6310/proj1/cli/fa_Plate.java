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
	private float cellTemperatures[][];
	
	public fa_Plate() {
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
				cellTemperatures[i - 1][j - 1] = cells[i][j]; 
			}
		}
		return cellTemperatures;		
	}	

	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#compute()
	 */
	public boolean compute(long sleepMilliseconds) {
		boolean done;
		double stopPrecision = option.getStopPrecison();
		int dimension = option.getDimension();
		int maxIterations = option.getMaxIterations();
		int iterationCount = 0;
		float [][]cellTemperatures;
		
		stopFlag = false;
		
		while (iterationCount < maxIterations && false == stopFlag) {
			done = true;
			for (int i = 1; i <= dimension; i++) {
				for (int j = 1; j <= dimension; j++) {
					newCells[i][j] = (cells[i][j - 1] + cells[i][j + 1] + 
									  cells[i - 1][j] + cells[i + 1][j]) / 4.0f;
					if (true == done && stopPrecision < (newCells[i][j] - cells[i][j])) {
					//if (stopPrecision < (newCells[i][j] - cells[i][j])) {
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
		float [][]temp;
		
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
				System.out.print(formatter.format(cells[i][j]) + " ");
			}
			System.out.println();
		}
	}
	
	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#stop()
	 */
	public void stop() {
		stopFlag = true;
	}	

	/* (non-Javadoc)
	 * @see cs6310.proj1.data.Plate#init()
	 */
	public void init() {
		int arrayDimension = option.getDimension() + 2;
		
		cells = new float[arrayDimension][arrayDimension];
		newCells = new float[arrayDimension][arrayDimension];
		
		int dimension = option.getDimension();
		cellTemperatures = new float[dimension][dimension];		
		
		EdgeTemperature edgeTemperature = option.getEdgeTemperature();
		
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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
