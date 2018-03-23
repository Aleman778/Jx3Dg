package jx3d.math;

import java.nio.FloatBuffer;

import jx3d.util.BufferUtils;

/**
 * Represents a 3 by 3 matrix with single precision. The class also
 * contains functions to transform the matrix. The matrix is column-major:
 * <p>
 * <code>
 * 		m00  m10<br>
 * 		m01  m11<br>
 * </code>
 * </p>
 * @since 1.0
 * @author Aleman778
 */
public final class Matrix2 {

	public float m00, m01;
	public float m10, m11;
	
	/**
	 * Default Constructor. The matrix is an identity matrix as default.
	 */
	public Matrix2() {
		m00 = 1;
		m11 = 1;
	}
	
	/**
	 * Constructor,
	 * @param m00 matrix entry row 1, column 1
	 * @param m01 matrix entry row 1, column 2
	 * @param m10 matrix entry row 2, column 1
	 * @param m11 matrix entry row 2, column 2
	 */
	public Matrix2(float m00, float m01, float m10, float m11) {
		this.m00 = m00;
		this.m01 = m01;
		this.m10 = m10;
		this.m11 = m11;
	}
	
	/**
	 * Constructor used to create a new copy of the provided matrix.
	 * @param copy the matrix to copy from
	 */
	public Matrix2(Matrix2 copy) {
		this.m00 = copy.m00;
		this.m01 = copy.m01;
		this.m10 = copy.m10;
		this.m11 = copy.m11;
	}
	
	/**
	 * Create a transformation matrix that performs a rotation
	 * by the given angle.
	 * @param angle the angle of the rotation (in radians)
	 * @return a new matrix holding the transformation
	 */
	public static final Matrix2 createRotation(float angle) {
		Matrix2 result = new Matrix2();
		result.m00 = (float)  Math.cos(angle);
		result.m01 = (float) -Math.sin(angle);
		result.m10 = (float)  Math.sin(angle);
		result.m11 = (float)  Math.cos(angle);
		return result;
	}
	
	/**
	 * Create a transformation matrix that performs a scaling by the
	 * given values <code>(x, y)</code>. 
	 * @param x the x component of the scaling
	 * @param y the y component of the scaling
	 * @return a new matrix holding the transformation
	 */
	public static final Matrix2 createScale(float x, float y) {
		Matrix2 result = new Matrix2();
		result.m00 = x;
		result.m11 = y;
		return result;
	}
	
	/**
	 * Add this matrix by the given matrix <code>m</code> component-wise.
	 * @param m the matrix to add
	 * @return a new matrix holding the result
	 */
	public Matrix2 add(Matrix2 m) {
		Matrix2 result = new Matrix2();
		result.m00 = m00 + m.m00;
		result.m01 = m01 + m.m01;
		result.m10 = m10 + m.m10;
		result.m11 = m11 + m.m11;
		return result;
	}
	
	/**
	 * Subtract this matrix by the given matrix <code>m</code> component-wise.
	 * @param m
	 * @return
	 */
	public Matrix2 sub(Matrix2 m) {
		Matrix2 result = new Matrix2();
		result.m00 = m00 - m.m00;
		result.m01 = m01 - m.m01;
		result.m10 = m10 - m.m10;
		result.m11 = m11 - m.m11;
		return result;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand.
	 * @param m the matrix to multiply on the right hand
	 * @return a new matrix holding the result
	 */
	public Matrix2 mul(Matrix2 m) {
		Matrix2 result = new Matrix2();
		result.m00 = m00 * m.m00 + m10 * m.m01;
		result.m01 = m01 * m.m00 + m11 * m.m01;
		result.m10 = m00 * m.m10 + m10 * m.m11;
		result.m11 = m01 * m.m10 + m11 * m.m11;
		return result;
	}

	/**
	 * Multiply this matrix by the given vector <code>v</code>.
	 * @param v the vector to multiply
	 * @return a new matrix holding the result
	 */
	public Vector2 mul(Vector2 v) {
		Vector2 result = new Vector2();
		result.x = m00 * v.x + m01 * v.y;
		result.y = m10 * v.x + m11 * v.y;
		return result;
	}

	/**
	 * Multiply this matrix by the given scalar <code>s</code> component-wise.
	 * @param s the scalar to multiply with
	 * @return a new matrix holding the result
	 */
	public Matrix2 mul(float s) {
		Matrix2 result = new Matrix2();
		result.m00 = m00 * s;
		result.m01 = m01 * s;
		result.m10 = m10 * s;
		result.m11 = m11 * s;
		return result;
	}
	
	/**
	 * Get the transpose of this matrix.
	 * @return a new matrix holding the result
	 */
	public Matrix2 transpose() {
		Matrix2 result = new Matrix2();
		result.m00 = m00;
		result.m01 = m10;
		result.m10 = m01;
		result.m11 = m11;
		return result;
	}
	
	/**
	 * Get the determinant of the matrix.
	 * @return a float holding the result
	 */
	public float determinant() {
		return (m00 * m11) - (m01 * m10);
	}
	
	/**
	 * Get the inverse of this matrix.
	 * @return a new matrix holding the result
	 * @throws IllegalStateException if the matrix is singular i.e. the determinant is zero.
	 */
	public Matrix2 inverse() throws IllegalStateException {
		Matrix2 result = new Matrix2();
		float det = determinant();
		if (det == 0f) {
			throw new IllegalStateException("Cannot compute the inverse of a singular matrix.");
		}
		
		float reciprocal = 1.0f / det;
		
		result.m00 =  m11 * reciprocal;
		result.m01 = -m01 * reciprocal;
		result.m10 = -m10 * reciprocal;
		result.m11 =  m00 * reciprocal;
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Matrix2) {
			Matrix2 mat = (Matrix2) obj;
			if (m00 != mat.m00)
				return false;
			if (m01 != mat.m01)
				return false;
			if (m10 != mat.m10)
				return false;
			if (m11 != mat.m11)
				return false;
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the components of this matrix in a float buffer.
	 * @return a new float buffer holding the data
	 */
	public FloatBuffer toFloatBuffer() {
		FloatBuffer result = BufferUtils.createFloatBuffer(m00, m01, m10, m11);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Matrix2: [%.3f %.3f]\n%9s[%.3f %.3f]", m00, m01, "", m10, m11);
	}
}
