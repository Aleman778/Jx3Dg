package jx3d.math;

import java.nio.FloatBuffer;

import jx3d.util.BufferUtils;

/**
 * Represents a 3 by 3 matrix with single precision. The class also
 * contains functions to transform the matrix. The matrix is column-major:
 * <p>
 * <code>
 * 		m00  m10  m20<br>
 * 		m01  m11  m21<br>
 * 		m02  m12  m22<br>
 * </code>
 * </p>
 * @since 1.0
 * @author Aleman778
 */
public final class Matrix33 {

	public float m00, m01, m02;
	public float m10, m11, m12;
	public float m20, m21, m22;
	
	/**
	 * Default Constructor. The matrix is an identity matrix as default.
	 */
	public Matrix33() {
		m00 = 1;
		m11 = 1;
		m22 = 1;
	}
	
	/**
	 * Constructor.
	 * @param m00 matrix entry row 1, column 1
	 * @param m01 matrix entry row 1, column 2
	 * @param m02 matrix entry row 1, column 3
	 * @param m10 matrix entry row 2, column 1
	 * @param m11 matrix entry row 2, column 2
	 * @param m12 matrix entry row 2, column 3
	 * @param m20 matrix entry row 3, column 1
	 * @param m21 matrix entry row 3, column 2
	 * @param m22 matrix entry row 3, column 3
	 */
	public Matrix33(float m00, float m01, float m02,
				    float m10, float m11, float m12,
				    float m20, float m21, float m22) {
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
	}
	
	/**
	 * Constructor used to create a new copy of the provided matrix.
	 * @param copy the matrix to copy from
	 */
	public Matrix33(Matrix33 copy) {
		this.m00 = copy.m00;
		this.m01 = copy.m01;
		this.m02 = copy.m02;
		this.m10 = copy.m10;
		this.m11 = copy.m11;
		this.m12 = copy.m12;
		this.m20 = copy.m20;
		this.m21 = copy.m21;
		this.m22 = copy.m22;
	}
	
	/**
	 * Constructor.
	 * @param entries float array containing at least 9 elements
	 */
	public Matrix33(float[] entries) {
		if (entries.length < 9) {
			throw new IllegalArgumentException("A 3x3 matrix requires at least 9 elements as an argument (Found: " + entries.length + ").");
		}
		
		this.m00 = entries[0];
		this.m01 = entries[1];
		this.m02 = entries[2];
		this.m10 = entries[3];
		this.m11 = entries[4];
		this.m12 = entries[5];
		this.m20 = entries[6];
		this.m21 = entries[7];
		this.m22 = entries[8];
	}
	
	/**
	 * @see Matrix33#rotateXY(float, Matrix33)
	 */
	public final Matrix33 rotateXY(float angle) {
		return rotateXY(angle, this);
	}
	
	/**
	 * Create a transformation matrix that performs a rotation in the XY-plane.
	 * @return a matrix holding the transformation
	 */
	public final Matrix33 rotateXY(float angle, Matrix33 dest) {
		Matrix33 right = new Matrix33();
		right.m00 = (float)  Math.cos(angle);
		right.m01 = (float) -Math.sin(angle);
		right.m10 = (float)  Math.sin(angle);
		right.m11 = (float)  Math.cos(angle);
		return mul(right, dest);
	}
	
	/**
	 * @see Matrix33#rotateXZ(float, Matrix33)
	 */
	public final Matrix33 rotateXZ(float angle) {
		return rotateXZ(angle, this);
	}
	
	/**
	 * Create a transformation matrix that performs a rotation in the XZ-plane.
	 * @return a matrix holding the transformation
	 */
	public final Matrix33 rotateXZ(float angle, Matrix33 dest) {
		Matrix33 right = new Matrix33();
		right.m00 = (float)  Math.cos(angle);
		right.m02 = (float)  Math.sin(angle);
		right.m20 = (float) -Math.sin(angle);
		right.m22 = (float)  Math.cos(angle);
		return mul(right, dest);
	}
	
	/**
	 * @see Matrix33#rotateXZ(float, Matrix33)
	 */
	public final Matrix33 rotateYZ(float angle) {
		return rotateYZ(angle, this);
	}
	
	/**
	 * Create a transformation matrix that performs a rotation in the YZ-plane.
	 * @return a matrix holding the transformation
	 */
	public final Matrix33 rotateYZ(float angle, Matrix33 dest) {
		Matrix33 right = new Matrix33();
		right.m11 = (float)  Math.cos(angle);
		right.m12 = (float) -Math.sin(angle);
		right.m21 = (float)  Math.sin(angle);
		right.m22 = (float)  Math.cos(angle);
		return mul(right, dest);
	}

	/**
	 * Create a transformation matrix that performs a scaling by the given scaling vector.
	 * @param v the scaling vector
	 * @return a matrix holding the transformation
	 */
	public final Matrix33 scale(Vector3D v) {
		return scale(v, this);
	}

	/**
	 * @see Matrix33#scale(float)
	 */
	public final Matrix33 scale(Vector3D v, Matrix33 dest) {
		Matrix33 right = new Matrix33();
		right.m00 = v.x;
		right.m11 = v.y;
		right.m22 = v.z;
		return mul(right, dest);
	}

	/**
	 * @see Matrix33#add(Matrix33, Matrix33)
	 */
	public Matrix33 add(Matrix33 other) {
		return add(other, this);
	}
	
	/**
	 * Add this matrix by the given matrix <code>m</code> component-wise.
	 * @param m the matrix to add
	 * @return a matrix holding the result
	 */
	public Matrix33 add(Matrix33 mat, Matrix33 dest) {
		dest.m00 = m00 + mat.m00;
		dest.m01 = m01 + mat.m01;
		dest.m02 = m02 + mat.m02;
		dest.m10 = m10 + mat.m10;
		dest.m11 = m11 + mat.m11;
		dest.m12 = m12 + mat.m12;
		dest.m20 = m20 + mat.m20;
		dest.m21 = m21 + mat.m21;
		dest.m22 = m22 + mat.m22;
		return dest;
	}
	
	/**
	 * Subtract this matrix by the given matrix <code>m</code> component-wise.
	 * @param m the matrix to add
	 * @return a matrix holding the result
	 */
	public Matrix33 sub(Matrix33 mat, Matrix33 dest) {
		dest.m00 = m00 - mat.m00;
		dest.m01 = m01 - mat.m01;
		dest.m02 = m02 - mat.m02;
		dest.m10 = m10 - mat.m10;
		dest.m11 = m11 - mat.m11;
		dest.m12 = m12 - mat.m12;
		dest.m20 = m20 - mat.m20;
		dest.m21 = m21 - mat.m21;
		dest.m22 = m22 - mat.m22;
		return dest;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand.
	 * @param right the matrix to multiply on the right hand
	 * @return a matrix holding the result 
	 */
	public Matrix33 mul(Matrix33 right, Matrix33 dest) {
		dest.m00 = this.m00 * right.m00 + this.m10 * right.m01 + this.m20 * right.m02;
		dest.m01 = this.m01 * right.m00 + this.m11 * right.m01 + this.m21 * right.m02;                                                                            
		dest.m02 = this.m02 * right.m00 + this.m12 * right.m01 + this.m22 * right.m02;
		dest.m10 = this.m00 * right.m10 + this.m10 * right.m11 + this.m20 * right.m12;
		dest.m11 = this.m01 * right.m10 + this.m11 * right.m11 + this.m21 * right.m12;                                                             
		dest.m12 = this.m02 * right.m10 + this.m12 * right.m11 + this.m22 * right.m12;
		dest.m20 = this.m00 * right.m20 + this.m10 * right.m21 + this.m20 * right.m22;
		dest.m21 = this.m01 * right.m20 + this.m11 * right.m21 + this.m21 * right.m22;
		dest.m22 = this.m02 * right.m20 + this.m11 * right.m21 + this.m22 * right.m22;
		return dest;
	}
	
	/**
	 * Get the transpose of this matrix.
	 * @return a matrix holding the result
	 */
	public Matrix33 transpose(Matrix33 dest) {
		dest.m00 = m00;
		dest.m01 = m10;
		dest.m02 = m20;
		dest.m10 = m01;
		dest.m11 = m11;
		dest.m12 = m21;
		dest.m20 = m02;
		dest.m21 = m12;
		dest.m22 = m22;
		return dest;
	}
	
	/**
	 * Get the determinant of the matrix.
	 * @return a float holding the result
	 */
	public float determinant() {
		return m00 * (m11 * m22 - m12 * m21)
			 - m01 * (m10 * m22 - m12 * m20)
			 + m02 * (m10 * m21 - m11 * m20);
	}
	
	/**
	 * Get the inverse of this matrix.
	 * @return a matrix holding the result
	 * @throws IllegalStateException if the matrix is singular i.e. the determinant is zero.
	 */
	public Matrix33 inverse(Matrix33 dest) throws IllegalStateException {
		float det = determinant();
		if (det == 0f)
			throw new IllegalStateException("Cannot compute the inverse of a singular matrix.");
		
		float reciprocal = 1.0f / det;
		float nm00 = (m11 * m22 - m12 * m21) * reciprocal;
		float nm01 = (m02 * m21 - m01 * m22) * reciprocal;
		float nm02 = (m01 * m12 - m02 * m11) * reciprocal;
		float nm10 = (m12 * m20 - m10 * m22) * reciprocal;
		float nm11 = (m00 * m22 - m02 * m20) * reciprocal;
		float nm12 = (m02 * m10 - m00 * m12) * reciprocal;
		float nm20 = (m10 * m21 - m11 * m20) * reciprocal;
		float nm21 = (m01 * m20 - m00 * m21) * reciprocal;
		float nm22 = (m00 * m11 - m01 * m10) * reciprocal;
		dest.m00 = nm00;
		dest.m01 = nm01;
		dest.m02 = nm02;
		dest.m10 = nm10;
		dest.m11 = nm11;
		dest.m12 = nm12;
		dest.m20 = nm20;
		dest.m21 = nm21;
		dest.m22 = nm22;
		return dest;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Matrix33) {
			Matrix33 mat = (Matrix33) obj;
			if (m00 != mat.m00)
				return false;
			if (m01 != mat.m01)
				return false;
			if (m02 != mat.m02)
				return false;
			if (m10 != mat.m10)
				return false;
			if (m11 != mat.m11)
				return false;
			if (m12 != mat.m12)
				return false;
			if (m20 != mat.m20)
				return false;
			if (m21 != mat.m21)
				return false;
			if (m22 != mat.m22)
				return false;
			
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the components of this matrix in a float buffer.
	 * @return a float buffer holding the data
	 */
	public FloatBuffer toFloatBuffer() {
		FloatBuffer result = BufferUtils.createFloatBuffer(m00, m10, m20,
														   m01, m11, m21,
														   m02, m12, m22);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Matrix3: [%.1f %.1f %.1f]\n%9s[%.1f %.1f %.1f]\n%9s[%.1f %.1f %.1f]", m00, m01, m02, "", m10, m11, m12, "", m20, m21, m22);
	}
}
