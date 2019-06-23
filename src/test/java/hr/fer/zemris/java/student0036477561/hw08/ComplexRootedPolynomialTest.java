package hr.fer.zemris.java.student0036477561.hw08;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComplexRootedPolynomialTest {

	@Test
	public void testToPolynomial() {
		ComplexRootedPolynomial polynomial = new ComplexRootedPolynomial(new Complex(1, 2), new Complex(-2, 0), new Complex(2, 0));
		ComplexPolynomial expected = new ComplexPolynomial(new Complex(4, 8),
															new Complex(-4, 0),
															new Complex(-1, -2),
															new Complex(1, 0)); 
		
		assertEquals(expected, polynomial.toComplexPolynom());
		
		ComplexRootedPolynomial polynomial2 = new ComplexRootedPolynomial(new Complex(1, 2), new Complex(-2, 0), new Complex(2, 0), Complex.ZERO, new Complex(1, -2));
		ComplexPolynomial expected2 = new ComplexPolynomial(Complex.ZERO,
															new Complex(-20, 0),
															new Complex(8, 0),
															new Complex(1, 0),
															new Complex(-2, 0),
															new Complex(1, 0)); 
		
		assertEquals(expected2, polynomial2.toComplexPolynom());
	}
	
	
	@Test
	public void testAplly() {
		ComplexRootedPolynomial polynomial = new ComplexRootedPolynomial(new Complex(1, 2), new Complex(-2, 0), new Complex(2, 0));
		
		assertEquals(Complex.ZERO, polynomial.apply(new Complex(1, 2)));
		
		assertEquals(new Complex(-4, 8), polynomial.apply(new Complex(2, 2)));
		
	}
	
	@Test
	public void testIndexOfClosestRoot() {
		ComplexRootedPolynomial polynomial = new ComplexRootedPolynomial(new Complex(1, 2), new Complex(-2, 0), new Complex(2, 0), new Complex(-2, -4));
		
		assertEquals(1, polynomial.indexOfClosestRootFor(new Complex(0.999134, 1.999124), 0.002));
		
		assertEquals(4, polynomial.indexOfClosestRootFor(new Complex(-1.999134, -3.998124), 0.002));
		
		assertEquals(2, polynomial.indexOfClosestRootFor(new Complex(-1.999134, 0.00012412), 0.002));
		
		assertEquals(-1, polynomial.indexOfClosestRootFor(new Complex(2.999134, 1.999124), 0.002));
	}
	
	@Test
	public void testToString() {
		
		ComplexRootedPolynomial polynomial = new ComplexRootedPolynomial(new Complex(1, 2));
		
		assertEquals("(z - (1 + 2i))", polynomial.toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
