package hr.fer.zemris.java.student0036477561.hw08;



import hr.fer.zemris.java.fractals.viewer.FractalViewer;

/**
 * Class that shows Mandelbrots fractal on screen.
 * @author Vilim Staroveški
 *
 */
public class MyMandelbrot {

	/**
	 * Method that starts GUI and shows the results.
	 */
	public static void showParallel() {
		Producer producer = new Producer();
		FractalViewer.show(producer);
	}
	
	/**
	 * Method that calculates results needed for screen representation of
	 * Mandelbrots fractal.
	 * @param reMin minimal value of real part.
	 * @param reMax maksimal value of real part.
	 * @param imMin minimal value of imaginary part.
	 * @param imMax maksimal value of imaginary part.
	 * @param width number of pixels in one screen row.
	 * @param height number of pixels in one screen column.
	 * @param m number of iterations in finding fractal value.
	 * @param ymin index of screen row in which method starts calculating.
	 * @param ymax index of screen row in which method finishes calculating.
	 * @param data array in which results will be stored.
	 */
	public static void calculate(double reMin, double reMax, double imMin, double imMax,
			int width, int height, int m, int ymin, int ymax, short[] data) {
		int offset = ymin*width;
		double module = 0;
		for(int y = ymin; y < ymax; y++) {
			for(int x = 0; x < width; x++) {
				
				double cre = x * (reMax - reMin) / (width - 1) + reMin;
				double cim = (height - 1 - y) * (imMax - imMin) / (height - 1) + imMin;
				Complex c = new Complex(cre, cim);
				Complex zN = c;
				int iters = 0;
				do {	
					Complex numerator = Newton.polynomial.apply(zN);
					Complex denominator = Newton.derived.apply(zN);
					Complex fraction = null;
					if(denominator.equals(Complex.ZERO)) {
						fraction = new Complex(Double.MAX_VALUE, Double.MAX_VALUE);
					}
					else {
						fraction = numerator.divide(denominator);
					}
					Complex zNext = zN.sub(fraction);
					module = zNext.sub(zN).module();
					zN = zNext;
					
				} while(iters < m && module > Newton.CONVERGANCE_TRESHOLD);
				
				int index = Newton.rootedPolynomial.indexOfClosestRootFor(zN, Newton.ROOT_TRESHOLD);
				if(index == -1) { 
					data[offset++] = 0; 
				} 
				else { 
					data[offset++] = (short) index; 
				}
			}
		}
	}
	
}
