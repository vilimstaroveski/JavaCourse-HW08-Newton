package hr.fer.zemris.java.student0036477561.hw08;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComplexPolynomialTest {

	@Test
	public void testConstruct() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero);
		Complex number = new Complex(1, 1);
		
		ComplexPolynomial polinom = new ComplexPolynomial(zero, number, zero, number);

		assertEquals("(1 + 1i)z^3 + (1 + 1i)z^1", polinom.toString());
		
		assertEquals(3, polinom.order());
		
		ComplexPolynomial polinom2 = new ComplexPolynomial(zero, number, zero, number, number, zero, number, zero, number, zero, zero);
		assertEquals(8, polinom2.order());
	}
	
	@Test
	public void testMultiply() {
		
		
		Complex number = new Complex(1, 1);
		ComplexPolynomial polinom = new ComplexPolynomial(Complex.ZERO, number, Complex.ZERO, number);
		ComplexPolynomial polinomZero = new ComplexPolynomial(Complex.ZERO);
		
		ComplexPolynomial result = polinom.multiply(polinomZero);
		assertTrue(result.equals(polinomZero));
		
	}
	
	@Test
	public void testMultiply2() {
		
		
		Complex number = new Complex(1, 1);
		Complex number2 = new Complex(0, -1);
		Complex number3 = new Complex(2, 0);
		Complex number4 = new Complex(-5, 2);
		
		ComplexPolynomial polinom = new ComplexPolynomial(number2, 
														number4, 
														Complex.ZERO, 
														number2);
		
		ComplexPolynomial polinom2 = new ComplexPolynomial(number,
															number2,
															Complex.ZERO,
															Complex.ZERO,
															number3);
		
		ComplexPolynomial resultActual = polinom.multiply(polinom2);
		
		ComplexPolynomial resultExpected = new ComplexPolynomial(new Complex(1, -1), 
														new Complex(-8, -3), 
														new Complex(2, 5), 
														new Complex(1, -1), 
														new Complex(-1, -2),
														new Complex(-10, 4),
														Complex.ZERO,
														new Complex(0, -2));
		
		assertEquals(resultExpected, resultActual);

	}
	
	@Test
	public void testAplly() {
		
		ComplexPolynomial polinomToApplyOn = new ComplexPolynomial(new Complex(1, -1), 
																Complex.ZERO, 
																new Complex(2, 5), 
																new Complex(1, -1));
		
		Complex z = new Complex(3, -4);
		
		assertEquals(new Complex(-54, -11), polinomToApplyOn.apply(z));
	}
	
	@Test
	public void testDerivate() {
		
		ComplexPolynomial polinomToDerivate = new ComplexPolynomial(new Complex(1, 0), 
																new Complex(5, 0), 
																new Complex(2, 0), 
																new Complex(7, 2));
		
		ComplexPolynomial resultExpected = new ComplexPolynomial(new Complex(5, 0),
																new Complex(4, 0),
																new Complex(21, 6));
		
		
		assertEquals(resultExpected, polinomToDerivate.derive());
		
		ComplexPolynomial polinomToDerivate2 = new ComplexPolynomial(new Complex(1, 0));
		
		assertEquals(new ComplexPolynomial(Complex.ZERO), polinomToDerivate2.derive());
	}
	
	@Test
	public void testEquals() {
		ComplexPolynomial polinom = new ComplexPolynomial(new Complex(1, 0), 
														new Complex(5, 0), 
														new Complex(2, 0), 
														new Complex(7, 2));
		
		ComplexPolynomial polinom2 = new ComplexPolynomial(new Complex(1, 0));
		
		assertFalse(polinom.equals(new Integer(2)));
		assertFalse(polinom.equals(polinom2));
		
		ComplexPolynomial polinom3 = new ComplexPolynomial(new Complex(1, 0), 
															new Complex(5, 0), 
															new Complex(2, 0), 
															new Complex(7, 2));
		assertTrue(polinom.equals(polinom3));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
