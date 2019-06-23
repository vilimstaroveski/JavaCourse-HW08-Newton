package hr.fer.zemris.java.student0036477561.hw08;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

public class ComplexTest {

	@Test
	public void testConstruct() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero);
		
		Complex zero2 = new Complex(0, -0);
		assertEquals(Complex.ZERO, zero2);
		
		Complex zero3 = new Complex(-0, -0);
		assertEquals(Complex.ZERO, zero3);
	}

	@Test
	public void testModule() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero);
		assertEquals(0, zero.module(), 0.000001);
		
		Complex number = new Complex(1, 1);
		assertEquals(Math.sqrt(2), number.module(), 0.000001);
		
		Complex number2 = new Complex(-1, -1);
		assertEquals(Math.sqrt(2), number2.module(), 0.000001);
		
		Complex number3 = new Complex(3, 4);
		assertEquals(5, number3.module(), 0.000001);
	}
	
	@Test
	public void testMultiply() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero);
		
		Complex number = new Complex(1, 1);
		assertEquals(Complex.ZERO, number.multiply(zero));
		
		Complex number2 = new Complex(-1, -1);
		assertEquals(new Complex(0, -2), number2.multiply(number));
		
		Complex number3 = new Complex(3, 4);
		assertEquals(new Complex(1, -7), number3.multiply(number2));
		
		Complex number4 = new Complex(3, -4);
		assertEquals(new Complex(25, 0), number4.multiply(number3));
		
		Complex number5 = new Complex(0, -5);
		assertEquals(new Complex(-20, -15), number5.multiply(number4));
		
		assertEquals(new Complex(-20, -15), Complex.ZERO.add(number5.multiply(number4)));
	}
	
	@Test
	public void testDivide() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero);
		
		Complex number = new Complex(1, 1);
		assertEquals(Complex.ZERO, zero.divide(number));
		
		Complex number2 = new Complex(-1, -1);
		assertEquals(new Complex(-1, 0), number.divide(number2));
		
		Complex number3 = new Complex(3, 4);
		assertEquals(new Complex(-3.5, -0.5), number3.divide(number2));
		
		Complex number4 = new Complex(2.45, -4.22);
		assertEquals(new Complex(-0.3812, -0.8984), number4.divide(number3));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testDivideException() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero);
		
		Complex number = new Complex(1, 1);
		number.divide(zero);
	}
		
	@Test
	public void testAdd() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero);
		
		Complex number = new Complex(1, 1);
		assertEquals(number, zero.add(number));
		
		Complex number2 = new Complex(-1, -1);
		assertEquals(Complex.ZERO, number.add(number2));
		
		Complex number3 = new Complex(3, 4);
		assertEquals(new Complex(2, 3), number3.add(number2));
		
		Complex number4 = new Complex(2.45, -4.22);
		assertEquals(new Complex(5.45, -0.22), number4.add(number3));
	}
	
	@Test
	public void testSub() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero);
		
		Complex number = new Complex(1, 1);
		assertEquals(new Complex(-1, -1), zero.sub(number));
		
		Complex number2 = new Complex(-1, -1);
		assertEquals(new Complex(2, 2), number.sub(number2));
		
		Complex number3 = new Complex(3, 4);
		assertEquals(new Complex(4, 5), number3.sub(number2));
		
		Complex number4 = new Complex(2.45, -4.22);
		assertEquals(new Complex(-0.55, -8.22), number4.sub(number3));
	}
	
	@Test
	public void testNegate() {
		
		Complex zero = new Complex();
		assertEquals(Complex.ZERO, zero.negate());
		
		Complex number = new Complex(1, 1);
		assertEquals(new Complex(-1, -1), number.negate());
		
		Complex number2 = new Complex(-1, -1);
		assertEquals(new Complex(1, 1), number2.negate());
		
		Complex number3 = new Complex(3, 4);
		assertEquals(new Complex(-3, -4), number3.negate());
		
		Complex number4 = new Complex(2.45, -4.22);
		assertEquals(new Complex(-2.45, 4.22), number4.negate());
	}
	
	@Test
	public void testToString() {
		
		Complex zero = new Complex();
		assertEquals("(0 + 0i)", zero.toString());
		
		Complex number = new Complex(1, 1);
		assertEquals("(1 + 1i)", number.toString());
		
		Complex number2 = new Complex(-1, -1);
		assertEquals("(-1 - 1i)", number2.toString());
		
		Complex number3 = new Complex(3, 4);
		assertEquals("(3 + 4i)", number3.toString());
		
		Complex number4 = new Complex(2.45231, -4.229999);
		assertEquals("(2.452 - 4.23i)", number4.toString());
	}
	
	@Test
	public void testEquals() {
		
		Complex zero = new Complex();
		assertTrue(zero.equals(Complex.ZERO));
		
		Complex number = new Complex(1, -1);
		assertTrue(number.equals(new Complex(1, -1)));
		assertFalse(number.equals(new Complex(1, 1)));
		
		Complex number2 = new Complex(2.45231, -4.229999);
		assertTrue(number2.equals(new Complex(2.45231, -4.229999)));
		assertFalse(number2.equals(new Complex(2.45, -4.223)));
		
		assertFalse(number.equals(new Integer(2)));
	}
	
	@Test
	public void testPower() {
		
		Complex number = new Complex(1, -1);
		Complex pow = number.power(2);
		assertEquals(new Complex(0, -2), pow);
		
		Complex number2 = new Complex(5, 0);
		Complex pow2 = number2.power(3);
		assertEquals(new Complex(125, 0), pow2);
		
		Complex number3 = new Complex(0, -2);
		Complex pow3 = number3.power(3);
		assertEquals(new Complex(0, 8), pow3);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testPowerException() {
		
		Complex number = new Complex(1, -1);
		@SuppressWarnings("unused")
		Complex pow = number.power(-2);
	}
	
	
}
