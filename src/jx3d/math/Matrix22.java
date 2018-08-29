package jx3d.math;

import java.nio.FloatBuffer;

import jx3d.util.BufferUtils;

/**
 * Represents a 2 by 2 matrix with single precision. The class also
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
public final class Matrix22 {

	public float m00, m01;
	public float m10, m11;
	
	/**
	 * Default Constructor. The matrix is an identity matrix as default.
	 */
	public Matrix22() {
		m00 = 1;
		m11 = 1;
	}
	
	/**
	 * Constructor,
	 * 
	 * @param m00 matrix entry row 1, column 1
	 * @param m01 matrix entry row 1, column 2
	 * @param m10 matrix entry row 2, column 1
	 * @param m11 matrix entry row 2, column 2
	 */
	public Matrix22(float m00, float m01,
					float m10, float m11) {
		this.m00 = m00;
		this.m01 = m01;
		this.m10 = m10;
		this.m11 = m11;
	}
	
	/**
	 * Constructor used to create a new copy of the provided matrix.
	 * @param copy the matrix to copy from
	 */
	public Matrix22(Matrix22 copy) {
		this.m00 = copy.m00;
		this.m01 = copy.m01;
		this.m10 = copy.m10;
		this.m11 = copy.m11;
	}
	
	/**
	 * Create a transformation matrix that performs a rotation
	 * by the given angle <code>a</code>.
	 * @param angle the angle of the rotation (in radians)
	 * @return a matrix holding the transformation
	 */
	public final Matrix22 rotate(float angle) {
		return rotate(angle, this);
	}
	
	/**
	 * Create a transformation matrix that performs a rotation
	 * by the given angle <code>a</code>.
	 * Then the result is stored in the given <code>dest</code> matrix.
	 * @param angle the angle of the rotation (in radians)
	 * @param dest the destination matrix
	 * @return a matrix holding the transformation
	 */
	public final Matrix22 rotate(float angle, Matrix22 dest) {
		dest.m00 = (float)  Math.cos(angle);
		dest.m01 = (float) -Math.sin(angle);
		dest.m10 = (float)  Math.sin(angle);
		dest.m11 = (float)  Math.cos(angle);
		return dest;
	}
	
	/**
	 * Create a transformation matrix that performs a scaling by the
	 * given values <code>(x, y)</code>. 
	 * @param v the scaling vector
	 * @return a matrix holding the transformation
	 */
	public final Matrix22 scale(Vector2D v) {
		return scale(v, this);
	}
	
	/**
	 * Create a transformation matrix that performs a scaling by the
	 * given values <code>(x, y)</code>. 
	 * Then the result is stored in the given <code>dest</code> matrix.
	 * @param v the scaling vector
	 * @param dest the destination matrix
	 * @return a matrix holding the transformation
	 */
	public final Matrix22 scale(Vector2D v, Matrix22 dest) {
		dest.m00 = v.x;
		dest.m11 = v.y;
		return dest;
	}
	
	/**
	 * Add this matrix by the given matrix <code>other</code> component-wise.
	 * @param other the other matrix to add
	 * @return a matrix holding the result
	 */
	public final Matrix22 add(Matrix22 other) {
		return add(other, this);
	}

	/**
	 * Add this matrix by the given matrix <code>other</code> component-wise
	 * and then the result is stored in the given <code>dest</code> matrix.
	 * @param other the other matrix to add
	 * @param dest the destination matrix
	 * @return a matrix holding the result
	 */
	public final Matrix22 add(Matrix22 other, Matrix22 dest) {
		dest.m00 = m00 + other.m00;
		dest.m01 = m01 + other.m01;
		dest.m10 = m10 + other.m10;
		dest.m11 = m11 + other.m11;
		return dest;
	}
	
	/**
	 * Subtract this matrix by the given matrix <code>m</code> component-wise.
	 * @param other the matrix to subtract
	 * @return a matrix holding the result
	 */
	public final Matrix22 sub(Matrix22 other) {
		return sub(other, this);
	}
	
	/**
	 * Subtract this matrix by the given matrix <code>m</code> component-wise
	 * and then the result is stored in the given <code>dest</code> matrix.
	 * @param other the matrix to subtract
	 * @param dest the destination matrix
	 * @return a matrix holding the result
	 */
	public final Matrix22 sub(Matrix22 other, Matrix22 dest) {
		dest.m00 = m00 - other.m00;
		dest.m01 = m01 - other.m01;
		dest.m10 = m10 - other.m10;
		dest.m11 = m11 - other.m11;
		return dest;
	}

	/**
	 * Multiply this matrix by the given scalar <code>s</code> component-wise.
	 * @param s the scalar to multiply with
	 * @return a matrix holding the result
	 */
	public final Matrix22 mul(float s) {
		return mul(s, this);
	}
	
	/**
	 * Multiply this matrix by the given scalar <code>s</code> component-wise
	 * and then the result is stored in the given <code>dest</code> matrix.
	 * @param s the scalar to multiply with
	 * @param dest the destination matrix
	 * @return a matrix holding the result
	 */
	public final Matrix22 mul(float s, Matrix22 dest) {
		dest.m00 = m00 * s;
		dest.m01 = m01 * s;
		dest.m10 = m10 * s;
		dest.m11 = m11 * s;
		return dest;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand.
	 * @param right the matrix to multiply on the right hand
	 * @return a new matrix holding the result
	 */
	public final Matrix22 mul(Matrix22 right) {
		Matrix22 result = new Matrix22();
		result.m00 = m00 * right.m00 + m10 * right.m01;
		result.m01 = m01 * right.m00 + m11 * right.m01;
		result.m10 = m00 * right.m10 + m10 * right.m11;
		result.m11 = m01 * right.m10 + m11 * right.m11;
		return result;
	}
	
	/**
	 * Get the transpose of this matrix.
	 * @return a matrix holding the result
	 */
	public final Matrix22 transpose() {
		return transpose(this);
	}
	
	/**
	 * Get the transpose of this matrix
	 * and then the result is stored in the given <code>dest</code> matrix.
	 * @return a matrix holding the result
	 */
	public final Matrix22 transpose(Matrix22 dest) {
		dest.m00 = m00;
		dest.m01 = m10;
		dest.m10 = m01;
		dest.m11 = m11;
		return dest;
	}
	
	/**
	 * Get the determinant of the matrix.
	 * @return a float holding the result
	 */
	public final float determinant() {
		return (m00 * m11) - (m01 * m10);
	}
	
	/**
	 * Get the inverse of this matrix.
	 * 
	 * @return a new matrix holding the result
	 * @throws IllegalStateException if the matrix is singular i.e. the determinant is zero.
	 */
	public final Matrix22 inverse(Matrix22 dest) throws IllegalStateException {
		Matrix22 result = new Matrix22();
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
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Matrix22))
			return false;
		
		Matrix22 mat = (Matrix22) obj;
		if (Float.floatToIntBits(m00) != Float.floatToIntBits(mat.m00))
			return false;
		if (Float.floatToIntBits(m01) != Float.floatToIntBits(mat.m01))
			return false;
		if (Float.floatToIntBits(m10) != Float.floatToIntBits(mat.m10))
			return false;
		if (Float.floatToIntBits(m11) != Float.floatToIntBits(mat.m11))
			return false;
		
		return true;
	}
	
	/**
	 * Returns the components of this matrix in a float buffer.
	 * @return a new float buffer holding the data
	 */
	public FloatBuffer toFloatBuffer() {
		FloatBuffer result = BufferUtils.createFloatBuffer(m00, m10, m01, m11);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Matrix2: [%.1f %.1f]\n%9s[%.1f %.1f]", m00, m01, "", m10, m11);
	}
}
