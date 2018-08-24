package jx3d.math.tests;

import static org.junit.Assert.*;

import static jx3d.core.Constants.*;

import org.junit.Test;

import jx3d.math.Matrix22;
import jx3d.math.Vector2D;

public class Matrix22Test {
	
	@Test
	public void testAddition() {
		Matrix22 m1 = new Matrix22(3.0f, 2.0f,
								   1.0f, 4.0f);
		Matrix22 m2 = new Matrix22(1.0f, 0.0f,
				   				   0.0f, 1.0f);
		Matrix22 m3 = new Matrix22();
		Matrix22 m4 = new Matrix22(4.0f, 2.0f,
								   1.0f, 5.0f);
		Matrix22 m5 = new Matrix22(5.0f, 2.0f,
								   1.0f, 6.0f);
		
		m1.add(m2);
		m1.add(m2, m3);
		assertEquals(m4, m1);
		assertEquals(m5, m2);
	}
	@Test
	public void testSubtraction() {
		Matrix22 m1 = new Matrix22(5.0f, 5.0f,
								   5.0f, 5.0f);
		Matrix22 m2 = new Matrix22(1.0f, 2.0f,
				   				   2.0f, 1.0f);
		Matrix22 m3 = new Matrix22(4.0f, 3.0f,
								   3.0f, 4.0f);
		Matrix22 m4 = new Matrix22(3.0f, 1.0f,
								   1.0f, 3.0f);
		m1.sub(m2);
		m1.sub(m2, m2);
		assertEquals(m3, m1);
		assertEquals(m4, m2);
		
	}
}
