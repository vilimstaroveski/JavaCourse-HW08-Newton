package hr.fer.zemris.java.student0036477561.hw08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.student0036477561.hw08.Complex;

/**
 * Program that draws a screen representation of Mandelbrots fractal. User is 
 * asked to input complex roots and {@code done} when finished. Root inputs 
 * should be defined like this:
 * 
 * (+|-)(double)(+|-)(i)(double)
 * 
 * First group is not necessary. Spaces between groups are allowed.
 * Sequence is not changeable.
 * 
 * @author Vilim Staroveški
 *
 */
public class Newton {

	/**
	 * Convergance treshold.
	 */
	public static final double CONVERGANCE_TRESHOLD = 0.002;
	
	/**
	 * Root distance treshold.
	 */
	public static final double ROOT_TRESHOLD = 0.001;
	
	/**
	 * Number of iterations for finding aproximate root.
	 */
	public static final int ITERATIONS = 16;
	
	/**
	 * Polynomial created from roots user has defined.
	 */
	public static ComplexPolynomial polynomial;
	
	/**
	 * Derivation of polynomial.
	 */
	public static ComplexPolynomial derived;
	
	/**
	 * Polynomial form containing roots.
	 */
	public static ComplexRootedPolynomial rootedPolynomial;
	
	/**
	 * Method called on program start.
	 * @param args
	 */
	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer. "
				+ "Please enter at least two roots, one root per line. Enter 'done' when done.");
		int index = 0;
		List<Complex> listOfRoots = new ArrayList<Complex>();
		
		while(true) {

			System.out.print("Root "+index+"> ");
			String line  = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(line == null) {
				System.err.println("You have entered an empty line!");
				continue;
			}
			line = line.trim();
			
			if(line.equalsIgnoreCase("done")) {
				if(listOfRoots.size() == 0) {
					System.err.println("Not any root was given. Exiting the program.");
					System.exit(1);
				}
				System.out.println("Image of fractal will appear shortly. Thank you.");
				Complex[] arrayOfComplex = new Complex[listOfRoots.size()];
				int i = 0;
				for(Complex c : listOfRoots) {
					arrayOfComplex[i++] = c;
				}
				rootedPolynomial = new ComplexRootedPolynomial(arrayOfComplex);
				polynomial = rootedPolynomial.toComplexPolynom();
				derived = polynomial.derive();
				MyMandelbrot.showParallel();
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			if(isInputValid(line)) {
				listOfRoots.add(parseToComplex(line));
				System.out.println(listOfRoots.get(index).toString());
				index++;
			}
			else {
				System.err.println("\nYou have entered invalid input!\n");
				continue;
			}
		}
	}

	/**
	 * Parses a {@link String} into a {@link Complex} number.
	 * @param line {@link String} input.
	 * @return {@link Complex} number represented by {@link String} input.
	 */ 
	private static Complex parseToComplex(String line) {
		String complex = line.replaceAll("\\s+", "");
		double real = 0;
		double imag = 0;
		
		if(complex.contains("i")) {
			
			String[] firstSplit = complex.split("[+,-]");
			String[] secondSplit = complex.split("i");
			
			switch(firstSplit.length) {
			case 1:					if(firstSplit[0].length() > 1) {
										imag = Double.parseDouble(firstSplit[0].substring(1));
										return new Complex(0, imag);
									}
									else {
										return new Complex(0, 1);
									}
			
			
			case 2:					if(secondSplit.length == 1) {
										char operatorImag = secondSplit[0].charAt(0);
										switch(operatorImag) {
										case '+':				return new Complex(0, 1);
										case '-':				return new Complex(0, -1);
										default:				throw new InvalidParameterException();
										}
									}
									else if(secondSplit.length == 2) {
										if(firstSplit[0].isEmpty()) {
											char operatorImag = secondSplit[0].charAt(0);
											switch(operatorImag) {
											case '+':				return new Complex(0, Double.parseDouble(secondSplit[1]));
											case '-':				return new Complex(0, -Double.parseDouble(secondSplit[1]));
											default:				throw new InvalidParameterException();
											}
										}
										else {
											real = Double.parseDouble(firstSplit[0]);
											char operatorImag = secondSplit[0].charAt(secondSplit[0].length()-1);
											switch(operatorImag) {
											case '+':				return new Complex(real, Double.parseDouble(secondSplit[1]));
											case '-':				return new Complex(real, -Double.parseDouble(secondSplit[1]));
											default:				throw new InvalidParameterException();
											}
										}
									}
									
			
			case 3:					char operatorReal = secondSplit[0].charAt(0);
									switch(operatorReal) {
									case '+':				real = Double.parseDouble(firstSplit[1]);
															break;
									case '-':				real = -Double.parseDouble(firstSplit[1]);
															break;
									default:				throw new InvalidParameterException();
									}
									char operatorImag = secondSplit[0].charAt(secondSplit[0].length() -1);
									switch(operatorImag) {
									case '+':				imag = Double.parseDouble(secondSplit[1]);
															break;
									case '-':				imag = -Double.parseDouble(secondSplit[1]);
															break;
									default:				throw new InvalidParameterException();
									}
									return new Complex(real, imag);
			}
		}
		else {
			
			real = Double.parseDouble(complex);
			return new Complex(real, 0);
		}
		return null;
	}
		
	/**
	 * Returns true if given input is parsable to {@link Complex} number. False otherwise.
	 * @param line {@link String} input.
	 * @return true if given input is parsable to {@link Complex} number. False otherwise.
	 */
	private static boolean isInputValid(String line) {

		try {
			
			parseToComplex(line);
			
		} catch(NumberFormatException | InvalidParameterException e ) {
			return false;
		}
		return true;
	}

}
