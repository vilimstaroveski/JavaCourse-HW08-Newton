package hr.fer.zemris.java.student0036477561.hw08;

/**
 * Class represent a complex polynomial. Complex polynomial is polynomial
 * which coefficients can be complex numbers.
 * For example:
 * (1 - 2i)*x^2 + 5*x - 5i
 * 
 * @author Vilim Staroveški
 *
 */
public class ComplexPolynomial {
	
	/**
	 * Coefficients of this polynomial.
	 */
	private Complex[] coef;
	
	/**
	 * Creates new {@link ComplexPolynomial} from given coefficients.
	 * @param factors coefficients for new {@link ComplexPolynomial}. n
	 * factor is coefficient of x^(n-1).
	 */
	public ComplexPolynomial(Complex ...factors) {
		
		coef = new Complex[factors.length];
		int power = 0;
		for(Complex factor : factors) {
			coef[power++] = factor;
		}
	}
	
	/**
	 * Returns order of this polynomial. For example:
	 * for  (7+2i)z^3+2z^2+5z+1 returns 3
	 * 
	 * @return order of this polynomial.
	 */
	public short order() {
        
		for(int i = coef.length-1; i >= 0; i--) {
			if(!coef[i].equals(Complex.ZERO)) {
				return (short) i;
			}
		}
		
		return 0;
	}
	
	/**
	 * Computes a new polynomial this polynomial multiplied by p.
	 * @param p polynomial multiplier.
	 * @return result of multiplication.
	 */
	public ComplexPolynomial multiply(ComplexPolynomial p) {

	    Complex[] zeros = new Complex[this.order()+p.order()+2];
	    for(int k = 0; k < zeros.length; k++) {
	    	zeros[k] = Complex.ZERO;
	    }
		ComplexPolynomial result = new ComplexPolynomial(zeros);
		for(int i = 0; i <= this.order(); i++) {
			for(int j = 0; j <= p.order(); j++) {
				result.coef[i+j] = result.coef[i+j].add(this.coef[i].multiply(p.coef[j]));
			}
		}
		return result;
		
	}
	
	/**
	 * Computes first derivative of this polynomial. For example:
	 * for (7+2i)z^3+2z^2+5z+1 returns (21+6i)z^2+4z+5
	 * @return first derivative of this polynomial.
	 */
	public ComplexPolynomial derive() {

		if (this.order() == 0) {
			return new ComplexPolynomial(Complex.ZERO);
		}
		Complex[] newCoefficients = new Complex[this.order()];
		for(int i = 0; i <= this.order() - 1; i++) {
			newCoefficients[i] = this.coef[i+1].multiply(new Complex(i+1, 0));
		}
		
        return new ComplexPolynomial(newCoefficients);
	}

	/**
	 * Computes polynomial value at given point z
	 * @param z point in which the value is calculated.
	 * @return polynomial value at given point z
	 */
	public Complex apply(Complex z) {

		Complex number = Complex.ZERO;
		for(int i = 0; i <= this.order(); i++) {
			number = number.add(this.coef[i].multiply(z.power(i)));
		}
        return number;
	}
	
	@Override
	public String toString() {

		int order = this.order();
		String string = this.coef[order]+"z^"+order;
		for(int i = order-1; i > 0; i--) {
			
			if(!this.coef[i].equals(Complex.ZERO)) {
				string += " + "+this.coef[i]+"z^"+i;
			}
		}
		if(!this.coef[0].equals(Complex.ZERO)) {
			string += " + "+this.coef[0];
		}
		return string;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(!(other instanceof ComplexPolynomial)) {
			return false;
		}
		ComplexPolynomial otherPolinom = (ComplexPolynomial) other;
		if(order() != otherPolinom.order()) {
			return false;
		}
		int order = order();
		for(int i = order; i >= 0; i--) {
			if(!this.coef[i].equals(otherPolinom.coef[i])) {
				return false;
			}
		}
		return true;
	}
}
