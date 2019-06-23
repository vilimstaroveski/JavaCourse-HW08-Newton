package hr.fer.zemris.java.student0036477561.hw08;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class represents a complex polynomial created from its roots. For example:
 * roots (1 - 2i) and (1 + 2i) creates polynomial (z - (1 - 2i))*(z - (1 + 2i)).
 * 
 * @author Vilim Staroveški
 *
 */
public class ComplexRootedPolynomial {
	
	/**
	 * Roots of this polynomial.
	 */
	private Map<Complex, Integer> allRoots;
	
	/**
	 * Creates new {@link ComplexRootedPolynomial}.
	 * @param roots roots of new polynomial.
	 */
	public ComplexRootedPolynomial(Complex ...roots) {
		
		allRoots = new HashMap<Complex, Integer>();
		int index = 1;
		for(Complex root : roots) {
			allRoots.put(root, index++);
		}
	}
	
	/**
	 * Computes polynomial value at given point z.
	 * @param z point in which value is calculated.
	 * @return value in point z.
	 */
	public Complex apply(Complex z) {
		
		return allRoots.containsKey(z) ? Complex.ZERO : toComplexPolynom().apply(z);
	}
	
	/**
	 * Converts this representation to {@link ComplexPolynomial} type.
	 * @return new {@link ComplexPolynomial} equal to this {@link ComplexRootedPolynomial}.
	 */
	public ComplexPolynomial toComplexPolynom() {

		ComplexPolynomial result = new ComplexPolynomial(Complex.ONE);
		Set<Complex> keySet = allRoots.keySet();
		for(Complex key : keySet) {
			ComplexPolynomial factor = new ComplexPolynomial(key.negate(), Complex.ONE);
			result = result.multiply(factor);
		}
		return result;
	}
	
	@Override
	public String toString() {

		String string = "";
		Set<Complex> keySet = allRoots.keySet();
		for(Complex key : keySet) {
			string += "(z - "+key.toString()+")";
		}
		return string;
	}
	
	/**
	 * Finds index of closest root for given complex number z that is within 
	 * treshold if there is no such root, returns -1
	 * @param z number that is being tested.
	 * @param treshold max value for which z can be distanced from a root to 
	 * return roots index.
	 * @return index of closest root for given complex number z or -1 if no root is
	 * within treshold.
	 */
	public int indexOfClosestRootFor(Complex z, double treshold) {

		Set<Complex> keySet = allRoots.keySet();
		for(Complex key : keySet) {
			if(Math.abs(key.getReal() - z.getReal()) < treshold && 
				Math.abs(key.getImaginary() - z.getImaginary()) < treshold) {
				
				return allRoots.get(key);
			}
		}
		return -1;
	}
}
