package hr.fer.zemris.java.student0036477561.hw08;


import java.security.InvalidParameterException;
import java.text.DecimalFormat;

/**
 * Class representing complex number. Complex number is defined with
 * real part and imaginary part (1+2i, i, -5i, 2+0i).
 * 
 * @author Vilim Staroveški
 *
 */
public class Complex {
	
	/**
	 * Constant representing (0 + 0i)
	 */
	public static final Complex ZERO = new Complex(0,0);
	
	/**
	 * Constant representing (1 + 0i)
	 */
	public static final Complex ONE = new Complex(1,0);
	
	/**
	 * Constant representing (-1 + 0i)
	 */
	public static final Complex ONE_NEG = new Complex(-1,0);
	
	/**
	 * Constant representing (0 + 1i)
	 */
	public static final Complex IM = new Complex(0,1);
	
	/**
	 * Constant representing (0 - 1i)
	 */
	public static final Complex IM_NEG = new Complex(0,-1);
	
	/**
	 * Real part of this {@link Complex}
	 */
	private double real;
	
	/**
	 * Imaginary part of this {@link Complex}.
	 */
	private double imaginary;
	
	/**
	 * Creates new {@link Complex} equal to (0 + i0)
	 */
	public Complex() {
	
		this(0,0);
	}
	
	/**
	 * Creates new {@link Complex} with given real and imaginary part.
	 * @param re real part of new {@link Complex}
	 * @param im imaginary part of new {@link Complex}
	 */
	public Complex(double re, double im) {

		this.real = re;
		this.imaginary = im;
	}
	
	/**
	 * Returns real part of this {@link Complex}
	 * @return real part of this {@link Complex}
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Returns imaginary part of this {@link Complex}
	 * @return imaginary part of this {@link Complex}
	 */
	public double getImaginary() {
		return imaginary;
	}

	/**
	 * Returns module of this complex number.
	 * @return module of this complex number.
	 */
	public double module() {
		
		return Math.sqrt(imaginary*imaginary + real*real);
	}
	
	/**
	 * Returns this {@link Complex} multiplied with c.
	 * @param c {@link Complex} multiplicator.
	 * @return this {@link Complex} multiplied with c.
	 */
	public Complex multiply(Complex c) {
		
		return new Complex(this.real*c.real - this.imaginary*c.imaginary, 
							this.real*c.imaginary + this.imaginary*c.real);
	}
	
	/**
	 * Returns this {@link Complex} divided with c.
	 * @param c {@link Complex} divider. Cannot be {@link Complex#ZERO}.
	 * @return this {@link Complex} divided with c.
	 * @throws UnsupportedOperationException if c is equal to {@link Complex#ZERO}
	 */
	public Complex divide(Complex c) {
		
		if(c.equals(Complex.ZERO)) {
			throw new UnsupportedOperationException("Dividing by zero is not possible.");
		}
		Complex multiplicator = new Complex(c.real, -c.imaginary);
		Complex multiplication = this.multiply(multiplicator);
		
		return new Complex(multiplication.real/Math.pow(c.module(), 2),
							multiplication.imaginary/Math.pow(c.module(), 2));
	}
	
	/**
	 * Returns this {@link Complex} added with c.
	 * @param c {@link Complex} summand.
	 * @return this {@link Complex} added with c.
	 */
	public Complex add(Complex c) {
	
		return new Complex(this.real+c.real, this.imaginary+c.imaginary);
	}
	
	/**
	 * Returns this {@link Complex} subtracted with c.
	 * @param c {@link Complex} number for subtraction.
	 * @return this {@link Complex} subtracted with c.
	 */
	public Complex sub(Complex c) {
		
		return new Complex(this.real-c.real, this.imaginary-c.imaginary);
	}
	
	/**
	 * Returns new {@link Complex} number equal to this multiplied by -1.
	 * @return new {@link Complex} number equal to this multiplied by -1.
	 */
	public Complex negate() {
		
		return new Complex(-real, -imaginary);
	}
	
	@Override
	public String toString() {
		
		DecimalFormat formatReal = new DecimalFormat("0.###");
		DecimalFormat formatImaginary = new DecimalFormat(" + 0.###; - 0.###");

		return "("+formatReal.format(real) + formatImaginary.format(imaginary)
				+ "i)";
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(!(other instanceof Complex)) {
			return false;
		}
		Complex otherCom = (Complex) other;
		return (Math.abs(this.real - otherCom.real) < 0.000001) &&
				(Math.abs(this.imaginary - otherCom.imaginary) < 0.000001);
	}

	/**
	 * Returns new {@link Complex} equal to this risen to n power.
	 * @param n power.
	 * @return new {@link Complex} equal to this risen to n power.
	 */
	public Complex power(int n) {
		
		if (n < 0) {
			throw new InvalidParameterException();
		}
		double magnitude = Math.pow(module(), n);
		double angle = n*Math.atan2(imaginary, real);
		double real = Math.cos(angle) * magnitude;
		double imag = Math.sin(angle) * magnitude;
		return new Complex(real, imag);
	}
}
