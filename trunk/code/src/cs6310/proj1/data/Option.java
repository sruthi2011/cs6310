/**
 * Option Class CS6310 Project 1
 * 
 * $Id$
 * $Date$
 */
package cs6310.proj1.data;

/**
 * @author Alex
 * 
 */
public class Option {
	private int dimension = 100;
	private int maxIterations = 5000;
	private EdgeTemperature edgeTemperature = new EdgeTemperature();
	private float stopPrecison = 0.001f;
	
	public Option() {

	}
	
	
	public boolean parseArgs(String[] args) {
		
		boolean success = true;
		String [] mandArgs = {"-d", "-l", "-r", "-t", "-b"};
		String [] optArgs = {"-n", "-p"};
		
		int [] mandCount = new int[mandArgs.length];
		int [] optCount = new int[optArgs.length];

		try {

			String arg;
			String argValue;
			for (int i = 0; i < args.length - 1; i += 2) {
				arg = args[i];
				argValue = args[i + 1];
				
				int j;
				boolean found = false;
				for (j = 0; j < mandArgs.length; j++) {					
					if (arg.equals(mandArgs[j])) {						
						found = true;
						break;
					}				
				}

				if (true == found) {
						if (0 == mandCount[j]) {
						int value = Integer.valueOf(argValue).intValue();
						switch(j) {
						case 0:
							if (1 < value && value <= 2000) {
								dimension = value;	
							}
							else {
								System.out.println("Dimension should be between 2 and 2000 (inclusive)");
								throw new Exception();
							}							 
							break;
						case 1:
							if (0 <= value && 100 >= value) {
								edgeTemperature.setLeft((float)value);	
							}
							else {
								System.out.println("Edge temperature should be between 0 and 100");
								throw new Exception();
							}
							break;
						case 2:
							if (0 <= value && 100 >= value) {
								edgeTemperature.setRight((float)value);								
							}
							else {
								System.out.println("Edge temperature should be between 0 and 100");
								throw new Exception();								
							}

							break;
						case 3:
							if (0 <= value && 100 >= value) {
								edgeTemperature.setTop((float)value);								
							}
							else {
								System.out.println("Edge temperature should be between 0 and 100");
								throw new Exception();								
							}
							break;
						case 4:
							if (0 <= value && 100 >= value) {
								edgeTemperature.setBottom((float)value);								
							}
							else {
								System.out.println("Edge temperature should be between 0 and 100");								
								throw new Exception();								
							}
							break;
						}
						mandCount[j] = 1;
					}
					else {
						throw new Exception();
					}
					continue;
				}
				
				for (j = 0; j < optArgs.length; j++) {
					if (arg.equals(optArgs[j])) {
						found = true;
						break;
					}				
				}

				if (true == found) {
					if (0 == optCount[j]) {
						switch(j) {
						case 0:
							int intValue = Integer.valueOf(argValue).intValue();
							if ( 0 <= intValue) {
								maxIterations = intValue; 								
							}
							else {
								System.out.println("Number of iterations should be greater than or equal to zero");
								throw new Exception();								
							}
							break;
						case 1:
							float floatValue = Float.valueOf(argValue).floatValue();
							if (0 < floatValue) {
								stopPrecison = floatValue;								
							}
							else {
								System.out.println("Precision should be greater than 0");
								throw new Exception();
							}

							break;
						}						
						optCount[j] = 1;
					}
					else {
						throw new Exception();
					}
					continue;
				}
			}

			for (int i = 0; i < mandCount.length; i++) {
				if (0 == mandCount[i]) {
					throw new Exception();
				}
			}

		}
		catch(Exception e) {
			success = false;
		}
		
		return success;
	}


	public int getDimension() {
    	return dimension;
    }


	public void setDimension(int dimension) {
    	this.dimension = dimension;
    }


	public int getMaxIterations() {
    	return maxIterations;
    }


	public void setMaxIterations(int maxIterations) {
    	this.maxIterations = maxIterations;
    }


	public EdgeTemperature getEdgeTemperature() {
    	return edgeTemperature;
    }


	public void setEdgeTemperature(EdgeTemperature edgeTemperature) {
    	this.edgeTemperature = edgeTemperature;
    }


	public float getStopPrecison() {
    	return stopPrecison;
    }


	public void setStopPrecison(float stopPrecison) {
    	this.stopPrecison = stopPrecison;
    }
}
