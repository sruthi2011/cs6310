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

	public void setOption(Option option) {
		super.setOption(option);
		init();
		
		float [][]guiData = null;
		guiData = getGuiDisplayData();
		notifyTemperatureChange(guiData);
	}
	
	protected float[][] getGuiDisplayData() {
		return cells;		
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
		float [][]guiData;
		
		stopFlag = false;
		
		while (iterationCount < maxIterations && false == stopFlag) {
			done = true;
			
			System.out.println("iteration " + iterationCount);
			display();
			
			for (int i = 1; i <= dimension; i++) {
				for (int j = 1; j <= dimension; j++) {
					newCells[i][j] = (cells[i][j - 1] + cells[i][j + 1] + 
									  cells[i - 1][j] + cells[i + 1][j]) / 4.0f;
					if (true == done && stopPrecision < (newCells[i][j] - cells[i][j])) {
						done = false;
					}
				}
			}
			swap();
			iterationCount++;
			
			if (!listeners.isEmpty()) {
				guiData = getGuiDisplayData();
				notifyTemperatureChange(guiData);
				
				try {
					if (sleepMilliseconds > 0) {
						Thread.sleep(sleepMilliseconds);
					}
				} catch (InterruptedException e) {
				}
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
	 * @see cs6310.proj1.data.Plate#init()
	 */
	public void init() {
		int arrayDimension = option.getDimension() + 2;
		
		cells = new float[arrayDimension][arrayDimension];
		newCells = new float[arrayDimension][arrayDimension];
		
		EdgeTemperature edgeTemperature = option.getEdgeTemperature();
		
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[i][0] = edgeTemperature.getLeft();
			//cells[i][1] = edgeTemperature.getLeft();
			cells[i][arrayDimension - 1] = edgeTemperature.getRight();
			//cells[i][arrayDimension - 2] = edgeTemperature.getRight();
			
			newCells[i][0] = edgeTemperature.getLeft();
			newCells[i][arrayDimension - 1] = edgeTemperature.getRight();
		}
		
		for (int i = 1; i < (arrayDimension - 1); i++) {
			cells[0][i] = edgeTemperature.getTop();
			//cells[1][i] = edgeTemperature.getTop();
			cells[arrayDimension - 1][i] = edgeTemperature.getBottom();
			//cells[arrayDimension - 2][i] = edgeTemperature.getBottom();
						
			newCells[0][i] = edgeTemperature.getTop();
			newCells[arrayDimension - 1][i] = edgeTemperature.getBottom();
		}
		/*
		cells[1][1] = (edgeTemperature.getTop() + edgeTemperature.getLeft()) / 2;
		cells[1][arrayDimension - 2] = (edgeTemperature.getTop() + edgeTemperature.getRight()) / 2;
		cells[arrayDimension - 2][1] = (edgeTemperature.getBottom() + edgeTemperature.getLeft()) / 2;
		cells[arrayDimension - 2][arrayDimension - 2] = (edgeTemperature.getBottom() + edgeTemperature.getRight()) / 2;
		*/
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
			plate.compute(0);
			plate.display();
		}
		else {
			System.out.println("error parsing arguments.");
			System.out.println("Usage: <program_name> -d # -l # -r # -t # -b #");
		}
	}

}
